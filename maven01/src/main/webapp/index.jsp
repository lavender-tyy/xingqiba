<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript">
		$(function(){
			initType()
			initData(1);
		})
		function initType(){
			$.ajax({
				type:"post",
				data:{
					
				},
				dataType:"json",
				url:"/maven01/book/getBookTypes.do",
				success:function(data){					
					var bookTypes=$("select[name=bookType]");
					//循环遍历,绑定下拉框
					$.each(data,function(i,v){
						bookTypes.append("<option value="+v["id"]+">"+v["type_name"]+"</option>");
					})				
					console.log(data);
				},
				error:function(){
					alert("请求失败");
				}
			});
		}
		function initData(i){
			var bookType=$("select[name=bookType]");
			var bookName=$("input[name=bookName]");
			var isBorrow=$("select[name=isBorrow]");
			$.ajax({
				type:"post",
				data:{
					index:i,
					bookName:bookName.val(),
					bookType:bookType.val(),
					isBorrow:isBorrow.val()
				},
				dataType:"json",
				url:"/maven01/book/getBooks.do",
				success:function(data){					
					//循环绑定table
					var tbody=$("table tbody");
					tbody.html("");
					$.each(data["pageUtil"]["list"],function(i,v){
						var tr="<tr>"
								+"<td>"+v["book_id"]+"</td>"
								+"<td>"+v["type_name"]+"</td>"
								+"<td>"+v["book_name"]+"</td>"
								+"<td>"+v["book_author"]+"</td>"
								+"<td>"+v["publish_press"]+"</td>"
								+"<td>"+(v["is_borrow"]==0?"<a href='#'>申请借阅</a>":"已借阅")+"</td>"	
							   +"</tr>"
						tbody.append(tr);
					})
					var prePage=data["pageUtil"]["index"]-1;
					var nextPage=data["pageUtil"]["index"]+1;
					if(prePage<=0){
						prePage=1;
					}
					if(nextPage>data["pageUtil"]["totalPage"]){
						nextPage=data["pageUtil"]["totalPage"];
					}
					var page="<tr><td colspan=6>"
						+"<a href='javascript:void(0)' onclick='initData(1)'>首页</a>"
						+"<a href='javascript:void(0)' onclick='initData("+prePage+")'>上一页</a>"
						+"<a href='javascript:void(0)' onclick='initData("+nextPage+")'>下一页</a>"
						+"<a href='javascript:void(0)' onclick='initData("+data["pageUtil"]["totalPage"]+")'>末页</a>"
					tbody.append(page);
					console.log(data);
				},
				error:function(){
					alert("请求失败");
				}
			})
		}	
	</script>
  </head>
  
  <body>
    <div>
    	<h1>图书借阅系统</h1>
    	<p>
    		图书分类：<select name="bookType">
    					<option value="-1">-请选择-</option>
    					
    				</select>
    		图书名称：<input name="bookName"/>
    		
    		是否借阅：<select name="isBorrow">
    					<option value="-1">-请选择-</option>
    					<option value="1">是</option>
    					<option value="0">否</option>
    				</select>
    				<input type="button" onclick="initData(1)" value="查询"/>
    	</p>
    	<p>
    		<span>当前用户:${user.user_code }</span>
    		<a href="#">退出</a>
    	</p>
    	<table>
    		<thead>
    			<tr>
    				<th>图书编号</th>
    				<th>图书分类</th>
    				<th>图书名称</th>
    				<th>作者</th>
    				<th>出版社</th>
    				<th>操作</th>
    			</tr>
    		</thead>
    		<tbody>
    			
    		</tbody>
    	</table>
    </div>
  </body>
</html>
