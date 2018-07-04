package cn.kgc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.entity.BookInfo;
import cn.kgc.entity.BookType;
import cn.kgc.service.BookService;
import cn.kgc.util.PageUtil;

public class BookController{
	public void getBookTypes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookService service = new BookService();
		List<BookType> bookTypes = service.getBookTypes();
		response.getWriter().write(JSON.toJSONString(bookTypes));
}	
	public void getBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.从页面获取数据
		String indexStr = request.getParameter("index");
		Integer index = 1;
		if(indexStr!=null){
			index = Integer.valueOf(indexStr);
		}
		
		String bookType = request.getParameter("bookType");
		String bookName = request.getParameter("bookName");
		String isBorrow = request.getParameter("isBorrow");
		System.err.println(bookType+"---"+bookName+"---"+isBorrow);
		
		//2.调用服务层方法
		BookService service = new BookService();
		BookInfo bookinfo = new BookInfo();
		if(bookType!=null&&!"-1".equals(bookType)){
			bookinfo.setBook_type(Integer.valueOf(bookType));
		}
		bookinfo.setBook_name(bookName);
		if(isBorrow!=null&&!"-1".equals(isBorrow)){
			bookinfo.setIs_borrow(Integer.valueOf(isBorrow));
		}
		PageUtil pageUtil = service.findBooks(bookinfo, index, 5);
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("pageUtil", pageUtil);
		
		//将java对象转成json数据格式
		String json=JSON.toJSONString(map);
		System.err.println(json);
		//将json格式的数据写到页面
		response.getWriter().write(json);
		
		
		//转发重定向
//		request.setAttribute("bookTypes", bookTypes);
//		request.setAttribute("pageList", pageUtil);
//		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}
}
