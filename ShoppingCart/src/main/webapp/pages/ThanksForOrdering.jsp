<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Iwowwow商城</title>
</head>
<body style="background: #EBFFEB;">
	<c:set var="funcName" value="END" scope="session" />
	<CENTER>
		<h1>
			親愛的會員 您好： <BR> <FONT COLOR='RED'>Iwowwow商城</FONT><BR>
			感謝您的訂購<BR> 期待您再度光臨
		</h1>
		<BR>
	</CENTER>




	<TABLE align='center'>
		<TR>
			<TD width="260" align='center'><A href="OrderList.jsp">查看歷史訂單</A></TD>
		</TR>
	</TABLE>

</body>
</html>