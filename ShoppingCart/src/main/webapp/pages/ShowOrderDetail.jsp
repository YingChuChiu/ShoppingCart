<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<title>個人訂單明細</title>

</head>
<body>

	<p />
	<TABLE border="2">
		<tr height='50'>
			<th align="center" colspan="8"><h3>訂單明細</h3></th>
		</tr>
		<tr height='36'>
			<td colspan="7">
				<table border='0'>
					<tr>

						<td align="center" width="500px">訂購日期:${OrderBean.update}</td>
						<td align="right" width="150px">訂單編號:${OrderBean.id}</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height='36'>
			<th width="ˇ350px" align="center">圖片</th>
			<th width="1200px" align="center">圖片名稱</th>
			<th width="100px" align="center">單價</th>

		</tr>
		<c:set var="subtotal" value="0" />
		<c:forEach var="aBean" varStatus="stat"
			items="${OrderBean.orderDetails}">
			<c:choose>
				<c:when test="${ stat.count % 2 == 0 }">
					<c:set var="aColor" value="#E6FFA0" />
				</c:when>
				<c:otherwise>
					<c:set var="aColor" value="#EBFFEB" />
				</c:otherwise>
			</c:choose>


			<tr bgColor="${aColor}" height='30'>

					<td align="center"><img height='120' width='120'
						src='${pageContext.servletContext.contextPath}/GetImageFormServlet?id=${aBean.picId}&type=photo'></TD>

				<td align="center">${aBean.description}&nbsp;</td>
				<td align="center"><fmt:formatNumber value="${aBean.price}"
						pattern="#,###,###" />元</td>


				<c:set var="subtotal" value="${subtotal+aBean.price}" />
			</tr>


		</c:forEach>
		<c:forEach varStatus="vs" var="anEntry"
			items="${ShoppingCart.content}">
		</c:forEach>
		<tr height='30'>
			<TD width="300px" colspan="2" align="center">合 計</TD>
			<TD width="300px" align="center"><fmt:formatNumber
					value="${subtotal}" pattern="#,###,###" />元</TD>
		</tr>
	</TABLE>
	<p />

	<center>
		<a href="<c:url value='/pages/OrderList.jsp' />">回上一頁</a>&nbsp;&nbsp;<a
			href="<c:url value='/pages/index.jsp' />">回首頁</a>
	</center>
</body>
</html>



