package jp.nicovideo.eka2513.cookiegetter4j.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import jp.nicovideo.eka2513.cookiegetter4j.constants.NicoCookieConstants;

/**
 * PropertyUtil
 * @author eka2513
 * 環境変数周りのユーティリティクラス
 */
public class PropertyUtil {

	/**
	 * linuxかどうかを返します
	 * @return
	 */
	public static boolean isLinux() {
		return (getOsName().toLowerCase().indexOf("linux") >= 0);
	}
	
	/**
	 * macosxかどうかを返します
	 * @return
	 */
	public static boolean isMac() {
		return (getOsName().indexOf("Mac OS X") >= 0) || (getOsName().indexOf("Darwin") >= 0);
	}

	/**
	 * windowsかどうかを返します
	 * @return
	 */
	public static boolean isWindows() {
		return (getOsName().indexOf("Windows") >= 0);
	}

	/**
	 * os名を返します
	 * @return
	 */
	public static String getOsName() {
		return System.getProperty("os.name");
	}

	/**
	 * ユーザのホームディレクトリを返します
	 * @return
	 */
	public static String getUserDir() {
		return System.getProperty("user.home");
	}

	/**
	 * propertiesを読み込みます
	 * @param filename
	 * @return
	 */
	public static Map<String, String> loadProp(String filename) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			InputStream ins = new FileInputStream(new File(filename));
			Properties config = new Properties();
			config.loadFromXML(ins);
			Iterator<Object> itr = config.keySet().iterator();
			while (itr.hasNext()) {
				String key = itr.next().toString();
				String val = config.getProperty(key);
				if (isWindows())
					val = parseWindowsEnv(val);
				if (isMac())
					val = parseMacHomedir(val);
				map.put(key, val);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return map;
	}

	/**
	 * 環境変数を取得します
	 * @param key
	 * @return
	 */
	public static String getEnv(String key) {
		return System.getenv().get(key);
	}

	/**
	 * windowsの環境変数をパースして実際の値にします
	 * @param path
	 * @return
	 */
	public static String parseWindowsEnv(String path) {
		//convert env to string
		String envVal = null;
		String regex = "(%[^%]+%)";
		String[] envs = StringUtil.groupMatch(regex, path);
		for (int i=0; i<envs.length; i++) {
			envVal = getEnv(envs[i].replaceAll("%", "")).replaceAll("\\\\", "\\\\\\\\");
			path = path.replaceAll(
					"\\Q" + envs[i] + "\\E",
					StringUtil.null2Val(envVal));
		}

		return path;
	}

	/**
	 * チルダをホームディレクトリに変換します
	 * @param val
	 * @return
	 */
	private static String parseMacHomedir(String val) {
		return val.replaceAll("~", System.getProperty("user.home"));
	}

	/**
	 * コンボボックス用に各osのブラウザを返します
	 * @return
	 */
	public static String[] getSupportBrowsers() {
		if (isMac())
			return NicoCookieConstants.BROWSERS_MAC;
		if (isWindows())
			return NicoCookieConstants.BROWSERS_WIN;
		return null;
	}
}
