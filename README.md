CookieGetter4J
==============

get cookie for several browsers

###Usage###
>String browser = NicoCookieConstants.BROWSER_CHROME;  
>NicoCookieManager manager = NicoCookieManagerFactory.getInstance(browser);  
>NicoCookie cookie = manager.getSessionCookie();  
>System.out.println(cookie.toCookieString());  
