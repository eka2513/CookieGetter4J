package jp.nicovideo.eka2513.cookiegetter4j.cookie;

import jp.nicovideo.eka2513.cookiegetter4j.constants.NicoCookieConstants;
import jp.nicovideo.eka2513.cookiegetter4j.util.PropertyUtil;

/**
 * NicoCookieManagerFactory
 * @author eka2513
 * NicoCookieManagerを取得します。
 */
public class NicoCookieManagerFactory {

	/**
	 * コンストラクタ
	 * @param browser
	 * @return
	 */
	public static NicoCookieManager getInstance(String browser) {
		if (PropertyUtil.isMac()) {
			return getMacInstance(browser);
		} else if (PropertyUtil.isWindows()) {
			return getWinInstance(browser);
		}
		return null;
	}

	/**
	 * Mac用のNicoCookieManager取得
	 * @param browser
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static NicoCookieManager getMacInstance(String browser) {
		try {
			if (browser.equals(NicoCookieConstants.BROWSER_CHROME))
				return NicoCookieManagerMacChrome.class.newInstance();
			if (browser.equals(NicoCookieConstants.BROWSER_SAFARI))
				return NicoCookieManagerMacSafari51.class.newInstance();
			if (browser.equals(NicoCookieConstants.BROWSER_SAFARI_50_UNDER))
				return NicoCookieManagerMacSafari.class.newInstance();
			if (browser.equals(NicoCookieConstants.BROWSER_FIREFOX))
				return NicoCookieManagerMacFirefox.class.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return null;
	}

	/**
	 * Mac用のNicoCookieManager取得
	 * @param browser
	 * @return
	 */
	private static NicoCookieManager getWinInstance(String browser) {
		try {
			if (browser.equals(NicoCookieConstants.BROWSER_CHROME))
				return NicoCookieManagerWinChrome.class.newInstance();
			if (browser.equals(NicoCookieConstants.BROWSER_SAFARI))
				return NicoCookieManagerWinSafari.class.newInstance();
			if (browser.equals(NicoCookieConstants.BROWSER_FIREFOX))
				return NicoCookieManagerWinFirefox.class.newInstance();
			if (browser.equals(NicoCookieConstants.BROWSER_IE))
				return NicoCookieManagerWinIE.class.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return null;
	}
}
