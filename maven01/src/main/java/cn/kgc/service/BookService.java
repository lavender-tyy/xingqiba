package cn.kgc.service;

import java.util.List;
import java.util.Map;

import cn.kgc.dao.BookDAO;
import cn.kgc.dao.BookTypeDAO;
import cn.kgc.entity.BookInfo;
import cn.kgc.entity.BookType;
import cn.kgc.util.PageUtil;

public class BookService {
	private BookDAO bookDAO=new BookDAO();
	private BookTypeDAO bookTypeDAO=new BookTypeDAO();
		
	public List<BookType> getBookTypes(){		
		return bookTypeDAO.findAllType();
	}
	/*
	 * 分页信息
	 */
	public PageUtil findBooks(BookInfo bookInfo,int index,int pageSize){
		List<Map<String,Object>> books = bookDAO.getBooksByPage(bookInfo, pageSize, index);
		Integer count=bookDAO.getCount(bookInfo);
		PageUtil pageUtil=new PageUtil();
		pageUtil.index=index;
		pageUtil.pageSize=pageSize;
		pageUtil.totalCount=count;
	//  pageUtil.totalPage=count%pageSize!=0?count/pageSize+1;count/pageSize;
		pageUtil.totalPage=(count-1)/pageSize+1;
		/**
		 * count	size	page
		 * 10		5		2
		 * 9		5		2
		 * 11		5		3
		 * 1		5		1
		 */
		pageUtil.list=books;
		return pageUtil;
	}
	 
}
