package jp.nicovideo.eka2513.cookiegetter4j.cookie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import jp.nicovideo.eka2513.cookiegetter4j.exception.NicoCookieException;
import jp.nicovideo.eka2513.cookiegetter4j.util.PropertyUtil;

/**
 * NicoCookieManagerWinIE
 * @author eka2513
 * <pre>
 * windows ie用のCookie取得クラス。
 * IE9とIE10だけテスト。
 * </pre>
 */
public class NicoCookieManagerWinIE implements NicoCookieManager {

	private static final String COOKIE_DIR = PropertyUtil.parseWindowsEnv("%APPDATA%\\Microsoft\\Windows\\Cookies\\");
	private static final String COOKIE_DIR_LOW = PropertyUtil.parseWindowsEnv("%APPDATA%\\Microsoft\\Windows\\Cookies\\Low\\");

	/**
	 * セッションクッキーを取得します。
	 */
	@Override
	public NicoCookie getSessionCookie() {
		// list files
		File dir = new File(COOKIE_DIR);
		File[] files = dir.listFiles();
		String value = null;
		for (File f : files) {
			value = getCookieValue(f);
			if (value != null)
				break;
		}
		if (value == null) {
			dir = new File(COOKIE_DIR_LOW);
			files = dir.listFiles();
			for (File f : files) {
				value = getCookieValue(f);
				if (value != null)
					break;
			}
		}
		return new NicoCookie(".nicovideo.jp", "/", "user_session", value, "", "");
	}

	/**
	 * Cookie取得メソッドの実体
	 * @param f
	 * @return
	 */
	private String getCookieValue(File f) {
		if (!f.isFile())
			return null;
		String result = null;
		BufferedReader reader = null;
		try {

			reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));

			String line;
			int index = 0;
			boolean flg = false;
			while ((line = reader.readLine()) != null) {
				if (index == 2) {
					if (line.indexOf("nicovideo.jp/") < 0) {
						break;
					}
				}
				if (line.equals("user_session")) {
					flg = true;
				} else if (flg) {
					result = line;
					break;
				}

				index++;
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
		return result;
	}

}
