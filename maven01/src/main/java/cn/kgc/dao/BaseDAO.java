package cn.kgc.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.kgc.util.DBHelper;

/**
 * 通用baseDAO（单表,多表,增删改）
 * @author Administrator
 * @param <T>
 */
public class BaseDAO<T> {
	
	public Connection con;
	public ResultSet rs;
	public PreparedStatement pstm;
	/**
	 * 通用查询方法（单表）
	 * @param sql
	 * @param objs
	 * @param clazz
	 * @return
	 */
							//String类   sql对象                  类的更上一层  clazz类
	public List<T> queryList(String sql,Object[] objs,Class<T> clazz){
		if(con==null){
			con = DBHelper.getConn();
		}
		List<T> list = new ArrayList<T>();
		try {
			pstm = con.prepareStatement(sql);
			//设置参数，下标从1开始
			for (int i = 0; i < objs.length; i++) {
				pstm.setObject(i+1, objs[i]);
			}
			
			rs = pstm.executeQuery();
			//获取列信息
			ResultSetMetaData metaData = rs.getMetaData();
			//获取列数
			int columns = metaData.getColumnCount();
			while (rs.next()) {
				//利用反射填充实体类的set方法
				//new    student stu
				Object obj = clazz.newInstance();
				for (int i = 1; i <= columns; i++) {
					//获得列名（属性名）
					String columnName = metaData.getColumnName(i);
					//获取属性信息
					Field field = clazz.getDeclaredField(columnName);
					//设置要调用的方法名和参数类型
					Method method = clazz.getDeclaredMethod("set"+columnName.substring(0,1).toUpperCase()+
							columnName.substring(1),field.getType());
					//调用方法
					method.invoke(obj, rs.getObject(i));
				}
				list.add((T)obj);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBHelper.closeAll(rs, pstm, null);
		}
		return list;
	}
	
	/**
	 * 通用增删改
	 * @param sql
	 * @param obj
	 * @return
	 */
	public int update(String sql,Object[] objs){
		if(con==null){
			con=DBHelper.getConn();
		}
		int result = 0;
		try {
			
			pstm = con.prepareStatement(sql);
			//设置参数
			for (int i = 0; i < objs.length; i++) {
				pstm.setObject(i+1, objs[i]);
			}
			System.out.println(pstm);
			result = pstm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBHelper.closeAll(rs, pstm, null);
		}
		return result;
	}
	/**
	 * 多表查询 返回list<map>
	 * @param sql
	 * @param objs
	 * @return
	 */
	public List<Map<String, Object>> queryListMap(String sql,Object[] objs){
		if(con==null){
			con = DBHelper.getConn();
		}
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			pstm = con.prepareStatement(sql);
			//设置参数
			for (int i = 0; i < objs.length; i++) {
				pstm.setObject(i+1, objs[i]);
			}
			rs = pstm.executeQuery();
			//获取列信息
			ResultSetMetaData metaData = rs.getMetaData();
			//获取列的数量
			int columns = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= columns; i++) {
					//获取列名
					String columnName = metaData.getColumnName(i);
					map.put(columnName, rs.getObject(columnName));
					//System.out.println(map);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBHelper.closeAll(rs, pstm, null);
		}
		return list;	
	}
	
}
