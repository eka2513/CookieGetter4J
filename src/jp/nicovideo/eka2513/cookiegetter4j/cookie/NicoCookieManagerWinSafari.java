package jp.nicovideo.eka2513.cookiegetter4j.cookie;

import jp.nicovideo.eka2513.cookiegetter4j.util.PropertyUtil;

/**
 * NicoCookieManagerWinSafari
 * @author eka2513
 * <pre>
 * windows safari 5.1未満用のクラス。plistから取得します。
 * 自分の端末にインストールできていないため、未テスト。
 * </pre>
 */
@SuppressWarnings("deprecation")
public class NicoCookieManagerWinSafari extends NicoCookieManagerMacSafari {

	public NicoCookieManagerWinSafari() {
		super();
		setFilePath(PropertyUtil.parseWindowsEnv("%APPDATA%\\Apple Computer\\Safari\\Cookies\\Cookies.plist"));
	}
}
