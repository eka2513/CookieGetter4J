package jp.nicovideo.eka2513.cookiegetter4j.cookie;

import jp.nicovideo.eka2513.cookiegetter4j.util.PropertyUtil;

/**
 * NicoCookieManagerMacFirefox
 * @author eka2513
 * linux firefox用のCookie取得クラス
 */
public class NicoCookieManagerLinuxFirefox extends NicoCookieManagerMacFirefox {

	/**
	 * コンストラクタ
	 */
	public NicoCookieManagerLinuxFirefox() {
		super();
		setBasedir( PropertyUtil.getUserDir() + "/.mozilla/firefox/" );
		setProfileIni(basedir + "profiles.ini");
		setCookieFile("/cookies.sqlite");
	}
}
