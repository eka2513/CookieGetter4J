package jp.nicovideo.eka2513.cookiegetter4j.cookie;

import jp.nicovideo.eka2513.cookiegetter4j.util.PropertyUtil;

/**
 * NicoCookieManagerWinChrome
 * @author eka2513
 * windows chrome用のCookie取得クラス
 */
public class NicoCookieManagerWinChrome extends NicoCookieManagerMacChrome {

	/**
	 * コンストラクタ
	 */
	public NicoCookieManagerWinChrome() {
		super();
		setSqlitePath(PropertyUtil.parseWindowsEnv("%LOCALAPPDATA%\\Google\\Chrome\\User Data\\Default\\Cookies"));
	}

}
