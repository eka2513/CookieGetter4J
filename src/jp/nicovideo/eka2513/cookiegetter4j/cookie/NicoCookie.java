package jp.nicovideo.eka2513.cookiegetter4j.cookie;

import java.io.Serializable;

/**
 * ブラウザから取得したCookieを取得します
 * @author eka2513
 */
public class NicoCookie implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1606504884590035725L;

	@SuppressWarnings("unused")
	private NicoCookie() {
	}

	/**
	 * コンストラクタ
	 * @param host
	 * @param path
	 * @param name
	 * @param value
	 * @param created
	 * @param expires
	 */
	public NicoCookie(String host, String path, String name, String value, String created, String expires) {
		this.host = host;
		this.path = path;
		this.name = name;
		this.value = value;
		this.created = created;
		this.expires = expires;
	}

	/**
	 * httpheaderに書くための書式にします
	 * @return
	 */
	public String toCookieString() {
		return String.format("%s=%s", name, value);
	}

	private String host;
	private String path;
	private String name;
	private String value;
	private String created;
	private String expires;

	/**
	 * hostを取得します。
	 * @return host
	 */
	public String getHost() {
	    return host;
	}

	/**
	 * hostを設定します。
	 * @param host host
	 */
	public void setHost(String host) {
	    this.host = host;
	}

	/**
	 * pathを取得します。
	 * @return path
	 */
	public String getPath() {
	    return path;
	}

	/**
	 * pathを設定します。
	 * @param path path
	 */
	public void setPath(String path) {
	    this.path = path;
	}

	/**
	 * nameを取得します。
	 * @return name
	 */
	public String getName() {
	    return name;
	}

	/**
	 * nameを設定します。
	 * @param name name
	 */
	public void setName(String name) {
	    this.name = name;
	}

	/**
	 * valueを取得します。
	 * @return value
	 */
	public String getValue() {
	    return value;
	}

	/**
	 * valueを設定します。
	 * @param value value
	 */
	public void setValue(String value) {
	    this.value = value;
	}

	/**
	 * createdを取得します。
	 * @return created
	 */
	public String getCreated() {
	    return created;
	}

	/**
	 * createdを設定します。
	 * @param created created
	 */
	public void setCreated(String created) {
	    this.created = created;
	}

	/**
	 * expiresを取得します。
	 * @return expires
	 */
	public String getExpires() {
	    return expires;
	}

	/**
	 * expiresを設定します。
	 * @param expires expires
	 */
	public void setExpires(String expires) {
	    this.expires = expires;
	}
}
