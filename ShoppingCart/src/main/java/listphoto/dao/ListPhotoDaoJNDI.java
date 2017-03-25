package listphoto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import listphoto.entity.ListPhoto;

public class ListPhotoDaoJNDI implements ListPhotoDAO_interface {

	public DataSource ds;
	public ListPhotoDaoJNDI() {

		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/Iwowwow");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private String SELECT = "SELECT * FROM PHOTO";
	private String SELECT_BY_ID = "SELECT * FROM PHOTO WHERE id=?";
	private String DELETE = "DELETE from PHOTO where id=?";
	private String UPDATE = "UPDATE PHOTO set name=?,assort=?,dateUpLoad=?,visibility=?,price=?,file_photo=? where id=?";
	private String INSERT = "INSERT INTO photo (name , assort ,dateUpLoad ,visibility  ,price , file_photo) values (?,?,?,?,?,?)";

	public Collection<ListPhoto> findAll() {
		Collection<ListPhoto> photos = new ArrayList<ListPhoto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SELECT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ListPhoto selectphoto = new ListPhoto();
				selectphoto.setId(rs.getInt("id"));
				selectphoto.setName(rs.getString("name"));
				selectphoto.setAssort(rs.getInt("assort"));
				Timestamp date = rs.getTimestamp("dateUpLoad");
				Instant instant = date.toInstant();
				LocalDateTime datetime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
				selectphoto.setDateUpLoad(datetime);
				selectphoto.setPrice(rs.getDouble("price"));
				selectphoto.setFile_photo(rs.getBlob("file_photo"));
				photos.add(selectphoto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {

				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return photos;

	}

	// 查詢-----------------------------------------------------------------------------
	public ListPhoto findById(int id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ListPhoto photo = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SELECT_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				photo = new ListPhoto();
				photo.setId(rs.getInt("id"));
				photo.setName(rs.getString("name"));
				photo.setAssort(rs.getInt("assort"));
				Timestamp date = rs.getTimestamp("dateUpLoad");
				Instant instant = date.toInstant();
				LocalDateTime datetime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
				photo.setDateUpLoad(datetime);
				photo.setVisibility(rs.getBoolean("visibility"));
				photo.setPrice(rs.getDouble("price"));
				photo.setFile_photo(rs.getBlob("file_photo"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {

				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return photo;
	}

	// 刪除-----------------------------------------------------------------------------
	public boolean delete(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			int num = pstmt.executeUpdate();
			if (num == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	// 新增-----------------------------------------------------------------------------
	public int insert(ListPhoto insert) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, insert.getName());
			pstmt.setInt(2, insert.getAssort());
			pstmt.setObject(3, insert.getDateUpLoad());
			pstmt.setBoolean(4, insert.getVisibility());
			pstmt.setDouble(5, insert.getPrice());
			pstmt.setBlob(6, insert.getFile_photo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return 0;

	}

	// 更新-----------------------------------------------------------------------------
	public int update(ListPhoto update) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, update.getName());
			pstmt.setInt(2, update.getAssort());
			pstmt.setObject(3, update.getDateUpLoad());
			pstmt.setBoolean(4, update.getVisibility());
			pstmt.setDouble(5, update.getPrice());
			pstmt.setBlob(6, update.getFile_photo());
			pstmt.setInt(7, update.getId());
			int num = pstmt.executeUpdate();
			if (num == 1) {
				return num;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return 0;
	}
}
