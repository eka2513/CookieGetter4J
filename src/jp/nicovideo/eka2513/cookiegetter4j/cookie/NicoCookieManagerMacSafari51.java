package jp.nicovideo.eka2513.cookiegetter4j.cookie;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import jp.nicovideo.eka2513.cookiegetter4j.exception.NicoCookieException;
import jp.nicovideo.eka2513.cookiegetter4j.util.PropertyUtil;
import jp.nicovideo.eka2513.cookiegetter4j.util.StringUtil;

/**
 * NicoCookieManagerMacSafari51
 * @author eka2513
 * <pre>
 * mac safari5.1以降のcookie取得クラス。
 * safari5.1以降はクッキーがplistからバイナリに変更になったためこのクラスを使う
 * </pre>
 */
public class NicoCookieManagerMacSafari51 implements NicoCookieManager {

	/**
	 * バイナリクッキーの場所
	 */
	protected String filePath;

	/**
	 * コンストラクタ
	 */
	public NicoCookieManagerMacSafari51() {
		super();
		setFilePath(PropertyUtil.getUserDir() + "/Library/Cookies/Cookies.binarycookies");
	}

	/**
	 * cookie取得メソッドの実体
	 * @param filename
	 * @return
	 */
	private String getSessionCookieValue(String filename) {
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "latin1"));

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
		String regex = "user_session\0(user_session[a-zA-Z0-9_]+)\0\\.nicovideo\\.jp\0";
		return StringUtil.groupMatchFirst(regex, sb.toString());
	}

	/**
	 * セッションクッキーを取得します
	 */
	@Override
	public NicoCookie getSessionCookie() {
		String value = getSessionCookieValue(filePath);
		return new NicoCookie(".nicovideo.jp", "/", "user_session", value, "", "");
	}

	/**
	 * filePathを設定します。
	 * @param filePath filePath
	 */
	public void setFilePath(String filePath) {
	    this.filePath = filePath;
	}
}
