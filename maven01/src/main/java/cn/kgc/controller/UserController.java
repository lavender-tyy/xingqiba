package cn.kgc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.entity.Users;
import cn.kgc.service.UserService;

public class UserController {
	
	/**
	 * 注册方法
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void register(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		//1.接受页面数据
		String userCode = request.getParameter("userCode");
		String password =request.getParameter("password");
		String sex =request.getParameter("sex");
		String email =request.getParameter("email");
		//2.调用方法
		UserService service = new UserService();
		Users user = new Users();
		user.setUser_code(userCode);
		user.setPassword(password);
		user.setGender(sex);
		user.setEmail(email);
		int result = service.register(user);
		//3.控制跳转
		if(result>0){
			response.sendRedirect("/maven01/login.jsp");
		}else{
			request.setAttribute("msg", "注册失败");
			request.getRequestDispatcher("/maven01/register.jsp").forward(request, response);
		}
	}

	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userCode = request.getParameter("userCode");
		String password = request.getParameter("password");
		UserService userService = new UserService();
		try {
			Users user = userService.login(userCode, password);
			request.getSession().setAttribute("user", user);
			response.sendRedirect("/maven01/index.jsp");
			//this.getBooks(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/maven01/login.jsp").forward(request, response);
		}
	}
}
