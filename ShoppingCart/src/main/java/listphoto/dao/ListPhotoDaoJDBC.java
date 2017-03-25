package listphoto.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
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
import org.apache.commons.io.IOUtils;
import listphoto.entity.ListPhoto;

public class ListPhotoDaoJDBC {

	private String URL = "jdbc:sqlserver://localhost:1433;database=IWOW";
	private String USERNAME = "sa";
	private String PASSWORD = "passw0rd";
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
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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

	// 測試-----------------------------------------------------------------------------
	public static void main(String[] args) {
		// select
		ListPhotoDaoJDBC photoDao = new ListPhotoDaoJDBC();
		// Collection<ListPhoto> photo2 = photoDao.findAll();
		// Iterator<ListPhoto> itr = photo2.iterator();
		// while (itr.hasNext()) {
		// ListPhoto photo = itr.next();
		// System.out.println(photo);
		// }

		// System.out.println(photoDao.findById(1));

		// 刪除
		// boolean delete=photoDao.delete(2);
		// System.out.println("delete="+delete);

		// 新增
//		ListPhoto insertPhoto = new ListPhoto();
//		insertPhoto.setName("柴犬");
//		insertPhoto.setAssort(100);
//		insertPhoto.setDateUpLoad(LocalDateTime.now());
//		insertPhoto.setVisibility(true);
//		insertPhoto.setPrice((double) 200);
//		try {
//			File file = new File("C:\\Special topic\\img\\01.jpg");
//			FileInputStream fis = new FileInputStream(file);
//			byte[] photopic = IOUtils.toByteArray(fis);
//			Blob blob = new javax.sql.rowset.serial.SerialBlob(photopic);
//			insertPhoto.setFile_photo(blob);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		photoDao.insert(insertPhoto);
//		System.out.println("insert=" + insertPhoto);

		// 更新
		 ListPhoto updatPhoto = new ListPhoto();
		 updatPhoto.setName("瑪爾濟斯");
		 updatPhoto.setAssort(3);
		 updatPhoto.setDateUpLoad(LocalDateTime.now());
		 updatPhoto.setVisibility(true);
		 updatPhoto.setPrice((double) 80);
		 try {
		 File file = new File("C:\\Users\\IrisChiu\\Desktop\\03.jpg");
		 FileInputStream fis = new FileInputStream(file);
		 byte[] photopic = IOUtils.toByteArray(fis);
		 Blob blob = new javax.sql.rowset.serial.SerialBlob(photopic);
		 updatPhoto.setFile_photo(blob);
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		 updatPhoto.setId(4);
		 photoDao.update(updatPhoto);
		 System.out.println("update=" + updatPhoto);
	}
}
