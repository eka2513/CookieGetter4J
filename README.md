CookieGetter4J
==============

ニコ生のCookieをブラウザから取得します。

一応Windows, MacOSX, Linuxに適当に対応しています。

###Usage###
>String browser = NicoCookieConstants.BROWSER_CHROME;  
>NicoCookieManager manager = NicoCookieManagerFactory.getInstance(browser);  
>NicoCookie cookie = manager.getSessionCookie();  
>System.out.println(cookie.toCookieString()); 

###Available Browser###
	public static final String[] BROWSERS_WIN = {
		BROWSER_IE, BROWSER_CHROME, BROWSER_FIREFOX, BROWSER_SAFARI, BROWSER_SAFARI_50_UNDER
	};
	public static final String[] BROWSERS_MAC = {
		BROWSER_CHROME, BROWSER_FIREFOX, BROWSER_SAFARI, BROWSER_SAFARI_50_UNDER
	};
	public static final String[] BROWSERS_LINUX = {
		BROWSER_CHROME, BROWSER_FIREFOX
	};
 
