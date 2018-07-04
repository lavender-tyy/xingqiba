package cn.kgc.dao;

import java.util.List;
import java.util.Map;

import cn.kgc.entity.BookInfo;

public class BookDAO extends BaseDAO<BookInfo> {
	/**
	 * 组合查询
	 * @param bookInfo
	 * @return
	 */
	public List<Map<String, Object>> getBooks(BookInfo bookInfo){
		//编写sql语句
		String sql = "SELECT * from book_info bi"
			+" LEFT JOIN book_type bt on bi.book_type=bt.id"
			+" where 1=1";
		int size = 0;
		if(bookInfo.getBook_name()!=null){
			sql+=" AND bi.book_name LIKE CONCAT('%',?,'%')";
			size++;
		}
		if(bookInfo.getBook_type()!=null){
			sql+=" AND bi.book_type = ?";
			size++;
		}
		if(bookInfo.getIs_borrow()!=null){
			sql+=" AND bi.is_borrow = ?";
			size++;
		}
		//设置参数
		Object[] obj = new Object[size];
		size = 0;
		if(bookInfo.getBook_name()!=null){
			obj[size] = bookInfo.getBook_name();
			size++;
		}
		if(bookInfo.getBook_type()!=null){
			obj[size] = bookInfo.getBook_type();
			size++;
		}
		if(bookInfo.getIs_borrow()!=null){
			obj[size] = bookInfo.getIs_borrow();
			size++;
		}
		return super.queryListMap(sql, obj);
	}

	/**
	 * 组合查询+分页（页码,每页显示的条数）
	 * @param bookInfo
	 * @param pageSize
	 * @param index
	 * @return
	 */
	public List<Map<String, Object>> getBooksByPage(BookInfo bookInfo,int pageSize,int index){
		String sql = "SELECT * from book_info bi"
			+" LEFT JOIN book_type bt on bi.book_type=bt.id"
			+" where 1=1";
		int size = 0;
		if(bookInfo.getBook_name()!=null){
			sql+=" AND bi.book_name LIKE CONCAT('%',?,'%')";
			size++;
		}
		if(bookInfo.getBook_type()!=null){
			sql+=" AND bi.book_type = ?";
			size++;
		}
		if(bookInfo.getIs_borrow()!=null){
			sql+=" AND bi.is_borrow = ?";
			size++;
		}
		sql+=" limit "+(index-1)*pageSize+","+pageSize;
		Object[] obj = new Object[size];
		size = 0;
		if(bookInfo.getBook_name()!=null){
			obj[size] = bookInfo.getBook_name();
			size++;
		}
		if(bookInfo.getBook_type()!=null){
			obj[size] = bookInfo.getBook_type();
			size++;
		}
		if(bookInfo.getIs_borrow()!=null){
			obj[size] = bookInfo.getIs_borrow();
			size++;
		}
		return super.queryListMap(sql, obj);
	}

	public Integer getCount(BookInfo bookInfo){
		String sql = "select count(1) count from book_info bi"
			+" where 1=1";
		int size = 0;
		if(bookInfo.getBook_name()!=null){
			sql+=" AND bi.book_name LIKE CONCAT('%',?,'%')";
			size++;
		}
		if(bookInfo.getBook_type()!=null){
			sql+=" AND bi.book_type = ?";
			size++;
		}
		if(bookInfo.getIs_borrow()!=null){
			sql+=" AND bi.is_borrow = ?";
			size++;
		}
		Object[] obj = new Object[size];
		size = 0;
		if(bookInfo.getBook_name()!=null){
			obj[size] = bookInfo.getBook_name();
			size++;
		}
		if(bookInfo.getBook_type()!=null){
			obj[size] = bookInfo.getBook_type();
			size++;
		}
		if(bookInfo.getIs_borrow()!=null){
			obj[size] = bookInfo.getIs_borrow();
			size++;
		}
		return Integer.valueOf(super.queryListMap(sql, obj).get(0).get("count").toString());
	}



	public static void main(String[] args) {
		BookDAO bookDAO = new BookDAO();
		BookInfo bookInfo = new BookInfo();
				bookInfo.setBook_name("薰衣草");
//		bookInfo.setBook_type(2);
		List<Map<String, Object>> books = bookDAO.getBooksByPage(bookInfo, 5, 1);
		Integer count = bookDAO.getCount(bookInfo);
		System.out.println(books);
		System.out.println(count);
	}


}
