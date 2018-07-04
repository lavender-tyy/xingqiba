package cn.kgc.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BaseController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//2.利用反射()XXX.do--访问--XXX()
		String url = request.getRequestURI();
		System.out.println(url);
		String method = url.substring(0,url.lastIndexOf("/"));
		method = method.substring(method.lastIndexOf("/")+1);
		String action = url.substring(url.lastIndexOf("/")+1, url.lastIndexOf("."));
		System.out.println(method);
		String clazzName=method.substring(0,1).toUpperCase()+method.substring(1)+"Controller";
		System.out.println(clazzName);
		/**反射**/
		try {
			Class clazz = Class.forName("cn.kgc.controller."+clazzName);
			Object obj = clazz.newInstance();
			Method md= clazz.getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
			md.invoke(obj, request,response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/maven01/error.html");
		} 
	}

	
}
