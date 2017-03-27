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
	<form action="ListPhotoServlet" method="post">

		<table style="border: 3px #cccccc solid" cellpadding="10" border='1'>
			<thead>
				<tr>
					<th>id</th>
					<th>圖片</th>
					<th>名稱</th>
					<th>類別</th>
					<th>上傳日期</th>
					<th>公開 /私密</th>
					<th>預售價格</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach var="list" items="${listphoto}">
					<tr>
						<td>${list.id}</td>

						<td><img height='120' width='120'
							src='${pageContext.servletContext.contextPath}/GetImageFormServlet?id=${list.id}&type=photo'>
						</td>

						<td>${list.name}</td>
						<td>${list.assort}</td>
						<td>${list.dateUpLoad}</td>
						<td>${list.visibility}</td>
						<td>${list.price}</td>
 						<td rowspan='1' width='180'align='center'>
							<input type="image" src="../images/direct_purchase_btn.PNG" onClick="document.formname.submit();"  style="width:120px;"><P/>
               				<input type="image" src="../images/add_to_cart_btn.PNG" onClick="document.formname.submit();"  style="width:120px;">
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="submit" value="ListPhoto">
	</form>


</body>
</html>