<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server 
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance 
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale" 
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

function confirmDelete(n) {
	if (confirm("確定刪除此項商品 ? ") ) {
		document.forms[0].action="<c:url value='/pages/DeleteCartServlet?cmd=DEL&photoID=" + n +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
	
	}
}

function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57)){
      return false;
   }
   return true;
}
function Checkout(qty) {
	if (qty == 0)  {
		alert("無購買任何商品，不需結帳");
		return false;
	}
	if (confirm("再次確認訂單內容 ? ") ) {
		return true;
	} else {
		return false;
	}
}
function Abort() {
	if (confirm("確定放棄購物 ? ") ) {
		return true;
	} else {
		return false;
	}
}

</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>購物清單</title>
</head>
<body>
	<c:choose>
		<c:when test="${ShoppingCart.subtotal > 0}">
			<c:set var="subtotalMessage" value="金額小計:${ShoppingCart.subtotal} 元" />
			<c:set var="subtotal" value="${ShoppingCart.subtotal}" />
		</c:when>
		<c:otherwise>
			<c:set var="subtotalMessage" value="金額小計:  0 元" />
			<c:set var="subtotal" value="0" />
		</c:otherwise>
	</c:choose>
	<c:set var="funcName" value="CHE" scope="session" />
	<c:choose>
		<c:when test="${ShoppingCart.itemNumber > 0}">
			<c:set var="cartContent" value="購物車內有${ShoppingCart.itemNumber}項商品" />
		</c:when>
		<c:otherwise>
			<c:set var="cartContent" value="您尚未購買任何商品" />
		</c:otherwise>
	</c:choose>

	<center>
		<p />


		<TABLE border='2' width="820">
			<TR>
				<TD colspan='4'>
					<TABLE width="820">
						<TR height='40'>
							<TD width="270">&nbsp;</TD>
							<TD width="280" align='center'><FONT size='+2'>${AppName}</FONT></TD>
							<TD width="270" align='right'></TD>
						</TR>

						<TR height='18'>
							<TD width="270">&nbsp;</TD>
							<TD width="280" align='center'><FONT size='+2'>購 物 清單</FONT></TD>
							<TD width="270" align='right'></TD>
						</TR>
					</TABLE>
				</TD>
			</TR>

			<TR>
				<TD><font size='-1' face='標楷體, Arial'>
						<TABLE border='1'>
							<TR>
								<TH width="280">圖片名稱</TH>
								<TH width="70">圖片價格</TH>
								<TH width="110">小計</TH>
								<TH width="110">修改</TH>
							</TR>
							<c:forEach varStatus="vs" var="anEntry"
								items="${ShoppingCart.content}">
								<TR height='16'>
									<TD>${anEntry.value.name}</TD>
									<TD style="text-align: center;">${fn:substring(anEntry.value.price, 0, 3)}</TD>
									<TD style="text-align: right;"><fmt:formatNumber
											value="${anEntry.value.price}" pattern="#,###" />元</TD>
									<TD><Input type="button" name="delete" value="刪除"
										onClick="confirmDelete(${anEntry.key})"></TD>
								</TR>
							</c:forEach>
							<TR height='16'>
								<TD colspan='2' align='right'>合計金額：</TD>
								<TD align='right'><fmt:formatNumber value="${subtotal}"
										pattern="#,###,###" />元</TD>
							</TR>
						</TABLE>
				</font>
			</TR>
			<TR height='80'>
				<TD>
					<TABLE border='1'>
						<TR>
							<TD width="260" align='center'><A
								href="<c:url value='../_03_listBooks/DisplayPageProducts?pageNo=1' />">繼續購物</A>
							</TD>
							<TD width="260" align='center'><A
								href="<c:url value='CheckoutCartServlet' />"
								onClick="return Checkout(${subtotal});">再次確認</A></TD>
							<TD width="260" align='center'><A
								href="<c:url value='GiveupCartServlet' />"
								onClick="return Abort();">放棄購物</A></TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
		</TABLE>
	</center>
	<form>
		<input type="hidden" name="a" />
	</form>
</body>
</html>