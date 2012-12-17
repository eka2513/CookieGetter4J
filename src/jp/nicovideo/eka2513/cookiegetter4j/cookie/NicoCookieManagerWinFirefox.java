package jp.nicovideo.eka2513.cookiegetter4j.cookie;

import jp.nicovideo.eka2513.cookiegetter4j.util.PropertyUtil;

/**
 * NicoCookieManagerWinFirefox
 * @author eka2513
 * windows firefox用のCookie取得クラス
 */
public class NicoCookieManagerWinFirefox extends NicoCookieManagerMacFirefox {

	/**
	 * コンストラクタ
	 */
	public NicoCookieManagerWinFirefox() {
		super();
		setBasedir(PropertyUtil.parseWindowsEnv("%APPDATA%\\Mozilla\\Firefox\\"));
		setProfileIni(basedir + "profiles.ini");
		setCookieFile("\\cookies.sqlite");
	}
}
