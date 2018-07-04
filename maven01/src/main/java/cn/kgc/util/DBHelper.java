package cn.kgc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {
		
		static String driver="com.mysql.jdbc.Driver";
		static String url="jdbc:mysql://127.0.0.1:3306/book";
		static String username="root";
		static String password="212647";
		
		//静态代码块里面加载驱动
		static{
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		//获取数据库连接
		public static Connection getConn(){
			Connection con= null;
			try {
				con=DriverManager.getConnection(url,username,password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
		//关闭
		public static void closeAll(ResultSet rs,PreparedStatement pstm,Connection con){
			try {
				if(rs!=null){
					rs.close();
				}
				if(pstm!=null){
					pstm.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}

