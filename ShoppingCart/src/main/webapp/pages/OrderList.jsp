<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:useBean id="orderBeans" class="ShoppingCart.dao.OrdersDAO"
	scope="page" />
<title>訂單列表</title>
</head>
<body>
	<c:set var="funcName" value="ORD" scope="session" />
	<center>
		<table border="2" bordercolor="blue" style="background: #EFEFFB;">

			<tr height='50'>
				<th colspan="4" align="center">訂購紀錄</th>
			</tr>
			<tr height='36'>
				<th>訂單編號</th>
				<th>訂購日期</th>

			</tr>
			<c:forEach var="anOrderBean" varStatus="stat" items="${orderBeans.allOrders}">
				<TR height='30'>
					<TD width="86" align="center"><a
						href='<c:url value='/OrderDetailServlet?id=${anOrderBean.id}' />'>
							${anOrderBean.id} </a></TD>
					<TD width="200" align="center">${anOrderBean.update}</TD>
				</TR>
			

			</c:forEach>

		
			<tr height='36'>
				<td align="center" colspan="4"><a
					href="index.jsp">回首頁</a></td>
			</tr>
		</TABLE>
	</center>
</body>
</html>