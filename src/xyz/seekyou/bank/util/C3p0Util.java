package xyz.seekyou.bank.util;//package xyz.seekyou.bank.util;
//
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import javax.sql.DataSource;
//import com.mchange.v2.c3p0.ComboPooledDataSource;
//
///**
// * @author Adam
// * 获取连接对象
// */
//public class C3p0Util {
//	private static DataSource ds;
//	static {
//		ds=new ComboPooledDataSource();
//	}
//
//	 public static Connection getConn()  {
//		 try {
//			return ds.getConnection();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 return null;
//	 }
//public static void close(Connection conn,PreparedStatement ps,ResultSet rs) {
//
//		 if(rs!=null) {
//			 try {
//				rs.close();
//				rs=null;
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		 }
//		 else if(ps!=null) {
//			 try {
//				ps.close();
//				ps=null;
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		 }
//		 else if(conn!=null) {
//			 try {
//				conn.close();
//				conn=null;
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		 }
//	 }
//}
//
