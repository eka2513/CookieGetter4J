package jp.nicovideo.eka2513.cookiegetter4j.cookie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jp.nicovideo.eka2513.cookiegetter4j.constants.NicoCookieConstants;
import jp.nicovideo.eka2513.cookiegetter4j.exception.NicoCookieException;
import jp.nicovideo.eka2513.cookiegetter4j.util.PropertyUtil;

/**
 * NicoCookieManagerMacFirefox
 * @author eka2513
 * mac firefox用のCookie取得クラス
 */
public class NicoCookieManagerMacFirefox implements NicoCookieManager {

	protected String basedir;
	protected String profileIni;
	protected String cookieFile;

	/**
	 * コンストラクタ
	 */
	public NicoCookieManagerMacFirefox() {
		super();
		setBasedir( PropertyUtil.getUserDir() + "/Library/Application Support/Firefox/" );
		setProfileIni(basedir + "profiles.ini");
		setCookieFile("/cookies.sqlite");
	}


	/**
	 * セッションクッキーを取得します。
	 */
	@Override
	public NicoCookie getSessionCookie() {
		List<String> dirlist = parseIni();
		String target = null;
		if (dirlist.size() == 0)
			throw new RuntimeException("cannot find firefox profile");
		if (dirlist.size() == 1) {
			target = basedir + dirlist.get(0) + cookieFile;
		} else {
			long maxlastmodified = 0L;
			int index = -1;
			for (int i=0; i<dirlist.size(); i++) {
				File f = new File(basedir + dirlist.get(i) + cookieFile);
				if (maxlastmodified < f.lastModified()) {
					index = i;
					maxlastmodified = f.lastModified();
				}
			}
			target = basedir + dirlist.get(index) + cookieFile;
		}
		return getCookieFromSqlite(target);
	}

	/**
	 * Cookie取得メソッドの実体
	 * @param filename
	 * @return
	 */
	private NicoCookie getCookieFromSqlite(String filename) {
		NicoCookie result = null;
		try {
			Class.forName(NicoCookieConstants.SQLITE_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + filename);
			Statement stat = conn.createStatement();
			conn.setAutoCommit(false);

			String sql = "select * from moz_cookies where host = '.nicovideo.jp' and name='user_session'";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				result = new NicoCookie(
					 rs.getString("host")
					,rs.getString("path")
					,rs.getString("name")
					,rs.getString("value")
					,rs.getString("creationTime")
					,rs.getString("expiry")
				);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
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
	 * iniファイルをパースします
	 * @return
	 */
	private List<String> parseIni() {
		List<String> list = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(profileIni), "UTF-8"));

			String line;
			while ((line = reader.readLine()) != null) {
				if (line.indexOf("Path=") >= 0) {
					list.add(line.split("=")[1]);
				}
			}
		} catch (IOException e) {
			throw new NicoCookieException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new NicoCookieException(e);
				}
			}
		}
		return list;
	}

	/**
	 * basedirを設定します。
	 * @param basedir basedir
	 */
	public void setBasedir(String basedir) {
	    this.basedir = basedir;
	}


	/**
	 * profileIniを設定します。
	 * @param profileIni profileIni
	 */
	public void setProfileIni(String profileIni) {
	    this.profileIni = profileIni;
	}


	/**
	 * cookieFileを設定します。
	 * @param cookieFile cookieFile
	 */
	public void setCookieFile(String cookieFile) {
	    this.cookieFile = cookieFile;
	}
}
