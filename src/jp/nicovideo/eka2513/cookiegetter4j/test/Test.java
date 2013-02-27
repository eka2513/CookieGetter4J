package jp.nicovideo.eka2513.cookiegetter4j.test;

import jp.nicovideo.eka2513.cookiegetter4j.constants.NicoCookieConstants;
import jp.nicovideo.eka2513.cookiegetter4j.cookie.NicoCookieManagerFactory;
import jp.nicovideo.eka2513.cookiegetter4j.util.PropertyUtil;

public class Test {
	public static void main(String[] args) {
		String[] browsers = null;
		if (PropertyUtil.isWindows()) {
			System.out.println("detected os is windows");
			browsers = NicoCookieConstants.BROWSERS_WIN;
		} else if (PropertyUtil.isMac()){
			System.out.println("detected os is mac or darwin");
			browsers = NicoCookieConstants.BROWSERS_MAC;
		} else if (PropertyUtil.isLinux()) {
			System.out.println("detected os is linux");
			browsers = NicoCookieConstants.BROWSERS_LINUX;
		} else {
			throw new RuntimeException("unknown os: " + PropertyUtil.getOsName());
		}
		for (String browser : browsers) {
				try {
				System.out.println("browser:" + browser);
				System.out.println("Cookie: " + NicoCookieManagerFactory.getInstance(browser).getSessionCookie().toCookieString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
