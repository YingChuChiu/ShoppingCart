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
		<form action="GetImageFormServlet" method="get">

			<table style="border: 3px #cccccc solid" cellpadding="10" border='1'>
				<thead>
					<tr>
						<th>id</th>
						<th>file_photo</th>
						<th>name</th>
						<th>assort</th>
						<th>dateUpLoad</th>
						<th>visibility</th>
						<th>price</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach var="list" items="${listphoto}">
						<tr>
							<td>${list.id}</td>

							<td><input type="hidden" name="id" value="${list.id}">
								<img height='100' width='80'
								src='${pageContext.servletContext.contextPath}/pages/ListPhotoServle?id=${list.id}&type=photo'>
							</td>

							<td>${list.name}</td>
							<td>${list.assort}</td>
							<td>${list.dateUpLoad}</td>
							<td>${list.visibility}</td>
							<td>${list.price}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input type="submit" value="ListPhoto">
		</form>

	</form>
</body>
</html>