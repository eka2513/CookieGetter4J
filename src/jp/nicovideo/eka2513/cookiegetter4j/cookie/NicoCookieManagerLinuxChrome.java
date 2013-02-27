package jp.nicovideo.eka2513.cookiegetter4j.cookie;


/**
 * NicoCookieManagerMacChrome
 * @author eka2513
 * linux chrome用のcookie取得クラス
 */
public class NicoCookieManagerLinuxChrome extends NicoCookieManagerMacChrome {

	/**
	 * コンストラクタ
	 */
	public NicoCookieManagerLinuxChrome() {
		super();
		setSqlitePath(System.getProperty("user.home") + "/.config/google-chrome/Default/Cookies");
	}
}
