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
		} else if (PropertyUtil.isLinux()) {
			return getLinuxInstance(browser);
		}
		throw new RuntimeException("Unknown OS:" + PropertyUtil.getOsName());
	}

	/**
	 * Linux用のNicoCookieManager取得
	 * @param browser
	 * @return
	 */
	private static NicoCookieManager getLinuxInstance(String browser) {
		try {
			if (browser.equals(NicoCookieConstants.BROWSER_CHROME))
				return NicoCookieManagerLinuxChrome.class.newInstance();
			if (browser.equals(NicoCookieConstants.BROWSER_FIREFOX))
				return NicoCookieManagerLinuxFirefox.class.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		throw new RuntimeException("browser:" + browser + " is not supported.");
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
			throw new RuntimeException(e);
		}
		throw new RuntimeException("browser:" + browser + " is not supported.");
	}

	/**
	 * Win用のNicoCookieManager取得
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
			throw new RuntimeException(e);
		}
		throw new RuntimeException("browser:" + browser + " is not supported.");
	}
}
