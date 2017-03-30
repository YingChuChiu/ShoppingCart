<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align='left'>
		<c:choose>
			<c:when test="${ShoppingCart.itemNumber > 0}">
				<c:set var="cartContent" value="購物車內有${ShoppingCart.itemNumber}項商品" />
			</c:when>
			<c:otherwise>
				<c:set var="cartContent" value="您尚未購買任何商品" />
			</c:otherwise>
		</c:choose>

		<div id='content'>
			<TABLE>
				<TR height='10'>
					<TD width="270" align='left'><FONT color='red'>${cartContent} </FONT></TD>
				</TR>

				<TR>
					<TD width="270" align='left'><FONT color='red'> 金額小計:<c:out value="${ShoppingCart.subtotal}" default="0" /> 元
					</FONT></TD>
				</TR>
				<TR>
					<TD><a href="ShowCartContent.jsp"><input type="submit" value="結帳"></a></TD>
				</TR>
			</TABLE>
		</div>
	</div>


	<table style="border: 3px #cccccc solid" cellpadding="10" border='1'>
		<thead>
			<tr>
				<th>id</th>
				<th>圖片</th>
				<th>名稱</th>
				<th>類別</th>
				<th>預售價格</th>
				<th>加入購物車</th>
			</tr>
		</thead>
		
		<c:forEach var="list" items="${listphoto}">
			<tr>
				<td>${list.id}</td>

				<td><img height='120' width='120'
					src='${pageContext.servletContext.contextPath}/GetImageFormServlet?id=${list.id}&type=photo'>
				</td>

				<td>${list.name}</td>
				<td>${list.assort}</td>
				<td>${list.price}</td>
				<td>
					<FORM action="<c:url value='BuyPhotoServlet' />" method="POST">
						<Input type='hidden' name='id' value='${list.id}'>
						<Input type='hidden' name='name' value='${list.name}'>
						<Input type='hidden' name='price' value='${list.price}'>
						<Input type='submit' value='加入購物車'>
					</FORM>
				</td>
		</c:forEach>
	</table>


	<form action="ListPhotoServlet" method="post">
		<input type="submit" value="ListPhoto">
	</form>

</body>
</html>