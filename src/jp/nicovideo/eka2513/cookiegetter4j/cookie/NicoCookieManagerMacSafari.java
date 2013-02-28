package jp.nicovideo.eka2513.cookiegetter4j.cookie;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import jp.nicovideo.eka2513.cookiegetter4j.exception.NicoCookieException;
import jp.nicovideo.eka2513.cookiegetter4j.util.PropertyUtil;
import jp.nicovideo.eka2513.cookiegetter4j.util.StringUtil;

/**
 * NicoCookieManagerMacSafari
 * @author eka2513
 * <pre>
 * mac safari 5.1未満用のクラス。plistから取得します。
 * 自分の端末にインストールできていないため、未テスト。
 * </pre>
 */
@Deprecated
public class NicoCookieManagerMacSafari implements NicoCookieManager {

	/**
	 * Cookies.plistの場所
	 */
	protected String filePath;

	/**
	 * コンストラクタ
	 */
	public NicoCookieManagerMacSafari() {
		super();
		setFilePath(PropertyUtil.getUserDir() + "/Library/Cookies/Cookies.plist");
	}

	/**
	 * セッションクッキーを取得します
	 */
	@Override
	public NicoCookie getSessionCookie() {
		NicoCookie result = null;
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));

			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			throw new NicoCookieException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new NicoCookieException(e);
				}
			}
		}
		String regex = "<dict>(.*?)</dict>";
		String[] m = StringUtil.groupMatch(regex, sb.toString());
		for (String s : m) {
			if (s.indexOf("<string>.nicovideo.jp</string>") >= 0
					&& s.indexOf("<string>user_session</string>") >= 0) {
				regex = "<[^>]+>([^<]+)</[^>]+>";
				String[] cookies = StringUtil.groupMatch(regex, s);
				String k = null;
				String v = null;
				Map<String, String> c = new HashMap<String, String>();
				for (int i = 0; i < cookies.length / 2; i++) {
					k = cookies[i * 2];
					v = cookies[i * 2 + 1];
					if (k.equals("Created")) {
						c.put("created", v);
					} else if (k.equals("Domain")) {
						c.put("host", v);
					} else if (k.equals("Expires")) {
						c.put("expires", v);
					} else if (k.equals("Name")) {
						c.put("name", v);
					} else if (k.equals("Path")) {
						c.put("path", v);
					} else if (k.equals("Value")) {
						c.put("value", v);
					}
				}
				result = new NicoCookie(c.get("host"), c.get("path"),
						c.get("name"), c.get("value"), c.get("created"),
						c.get("expires"));
				break;
			}
		}
		return result;
	}

	/**
	 * filePathを設定します。
	 * @param filePath filePath
	 */
	public void setFilePath(String filePath) {
	    this.filePath = filePath;
	}
}
