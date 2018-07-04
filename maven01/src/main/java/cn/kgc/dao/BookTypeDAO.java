package cn.kgc.dao;

import java.util.List;

import cn.kgc.entity.BookType;

public class BookTypeDAO extends BaseDAO<BookType>{
	public List<BookType> findAllType(){
		String sql="select * from book_type";
		return super.queryList(sql, new Object[]{},BookType.class);
	}
}
