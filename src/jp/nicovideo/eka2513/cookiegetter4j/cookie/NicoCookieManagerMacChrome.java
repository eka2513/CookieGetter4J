package jp.nicovideo.eka2513.cookiegetter4j.cookie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jp.nicovideo.eka2513.cookiegetter4j.exception.NicoCookieException;

/**
 * NicoCookieManagerMacChrome
 * @author eka2513
 * mac chrome用のcookie取得クラス
 */
public class NicoCookieManagerMacChrome implements NicoCookieManager {

	/** sqliteからcookieを取得するためのsql */
	private static final String SQL = "select * from cookies where host_key = '.nicovideo.jp' and name='user_session';";

	/** sqliteがあるpath */
	protected String sqlitePath;

	/**
	 * コンストラクタ
	 */
	public NicoCookieManagerMacChrome() {
		super();
		setSqlitePath(System.getProperty("user.home") + "/Library/Application Support/Google/Chrome/Default/Cookies");
	}

	/**
	 * セッションクッキーを取得します
	 */
	@Override
	public NicoCookie getSessionCookie() {
		NicoCookie result = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + sqlitePath);
			Statement stat = conn.createStatement();
			conn.setAutoCommit(false);

			rs = stat.executeQuery(SQL);
			while (rs.next()) {
				result = new NicoCookie(
					 rs.getString("host_key")
					,rs.getString("path")
					,rs.getString("name")
					,rs.getString("value")
					,rs.getString("creation_utc")
					,rs.getString("expires_utc")
				);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NicoCookieException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if (result == null)
			throw new NicoCookieException("セッションクッキーが取得できませんでした");
		return result;
	}


	/**
	 * sqlitePathを設定します。
	 * @param sqlitePath sqlitePath
	 */
	public void setSqlitePath(String sqlitePath) {
	    this.sqlitePath = sqlitePath;
	}
}
