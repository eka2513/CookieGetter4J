package jp.nicovideo.eka2513.cookiegetter4j.exception;

/**
 * NicoCookieException
 * @author eka2513
 * Cookie取得時のException
 */
public class NicoCookieException extends RuntimeException {

	public NicoCookieException(String message) {
		super(message);
	}

	public NicoCookieException(Exception e) {
		super(e);
	}

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8211321414281819954L;
}
