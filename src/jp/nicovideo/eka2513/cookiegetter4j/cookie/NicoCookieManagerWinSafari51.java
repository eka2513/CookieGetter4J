package jp.nicovideo.eka2513.cookiegetter4j.cookie;

import jp.nicovideo.eka2513.cookiegetter4j.util.PropertyUtil;

/**
 * NicoCookieManagerWinSafari51
 * @author eka2513
 * <pre>
 * windows safari5.1以降のcookie取得クラス。
 * safari5.1以降はクッキーがplistからバイナリに変更になったためこのクラスを使う
 * </pre>
 */
public class NicoCookieManagerWinSafari51 extends NicoCookieManagerMacSafari51 {

	protected static final String FILE_PATH = PropertyUtil.parseWindowsEnv("%APPDATA%\\Apple Computer\\Safari\\Cookies\\Cookies.binarycookies");

}
