package cn.kgc.service;

import cn.kgc.dao.UserDAO;
import cn.kgc.entity.Users;

public class UserService {
	private UserDAO userDAO =new UserDAO();
	/*
	 * ��½����
	 */
	public Users login(String userCode,String password) throws Exception{
		Users user=userDAO.findByCode(userCode);
		if(user==null){
			throw new Exception("账号不存在");
		}else{
			if(!user.getPassword().equals(password)){
				throw new Exception("密码错误");
			}
		}
		System.out.println("登陆成功");
		return user;
	}
	/*
	 * ע�Ṧ��
	 */
	public int register(Users user){
		return userDAO.addUser(user);
	}
	public static void main(String[] args) throws Exception {
		UserService us = new UserService();
		us.login("bella", "123");
	}

	
}
