package jp.nicovideo.eka2513.cookiegetter4j.constants;

/**
 * NicoCookieConstants
 * @author eka2513
 * 定数
 */
public interface NicoCookieConstants {

	/** BROWSER_CHROME */
	public static final String BROWSER_CHROME  = "Chrome";
	/** BROWSER_SAFARI_50_UNDER */
	public static final String BROWSER_SAFARI_50_UNDER= "Old Safari(Under5.0)";
	/** BROWSER_SAFARI */
	public static final String BROWSER_SAFARI  = "Safari";
	/** BROWSER_FIREFOX */
	public static final String BROWSER_FIREFOX = "Firefox";
	/** BROWSER_IE */
	public static final String BROWSER_IE      = "InternetExplorer";
	/** BROWSER_OPERA */
	public static final String BROWSER_OPERA   = "Opera";
	/** BROWSER_IRON */
	public static final String BROWSER_IRON    = "Iron";

	/** sqliteのドライバ */
	public static final String SQLITE_DRIVER = "org.sqlite.JDBC";
	
	/**
	 * Windows用のブラウザ
	 */
	public static final String[] BROWSERS_WIN = {
		BROWSER_IE, BROWSER_CHROME, BROWSER_FIREFOX, BROWSER_SAFARI, BROWSER_SAFARI_50_UNDER
	};

	/**
	 * Mac用のブラウザ
	 */
	public static final String[] BROWSERS_MAC = {
		BROWSER_CHROME, BROWSER_FIREFOX, BROWSER_SAFARI, BROWSER_SAFARI_50_UNDER
	};
	/**
	 * Linux用のブラウザ
	 */
	public static final String[] BROWSERS_LINUX = {
		BROWSER_CHROME, BROWSER_FIREFOX
	};
}
