<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link rel="StyleSheet" href="<c:url value='/css/jpetstore.css'/>" type="text/css"
	media="screen" />

<meta name="generator"
	content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
<title>JPetStore Demo</title>
<meta content="text/html; charset=windows-1252"
	http-equiv="Content-Type" />
<meta http-equiv="Cache-Control" content="max-age=0" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="Pragma" content="no-cache" />
</head>

<body>

<div id="Header">

<div id="Logo">
	<div id="LogoContent">
		<a href="Catalog.action">
			<img src="<c:url value='/images/logo-topbar.gif'/>" />
		</a>
	</div>
</div>

<div id="Menu">
<div id="MenuContent">
	<a href="Cart.action">
		<img align="middle" name="img_cart" src="<c:url value='/images/cart.gif'/>" />
	</a>
	<img align="middle" src="<c:url value='/images/separator.gif'/>" /> <c:if
	test="${sessionScope.accountBean == null}">
	<a href="Account.action?signonForm=">
          Sign In
	</a>
</c:if> <c:if test="${sessionScope.accountBean != null}">
	<c:if test="${!sessionScope.accountBean.authenticated}">
		<a href="Account.action?signonForm=">
            Sign In
		</a>
	</c:if>
</c:if> <c:if test="${sessionScope.accountBean != null}">
	<c:if test="${sessionScope.accountBean.authenticated}">
		<a href="Account.action?signoff=">
            Sign Out
		</a>
		<img align="middle" src="<c:url value='/images/separator.gif'/>" />
		<a href="Account.action?editAccountForm=">
		My Account
		</a>
	</c:if>
</c:if> <img align="middle" src="<c:url value='/images/separator.gif'/>" /> <a
	href="/help.html">?</a></div>
</div>

<div id="Search">
	<div id="SearchContent">
		<form action="Catalog.action" method="get">
			<input type="text"  name="keyword" size="14"/>
			<input type="submit" name="searchProducts" value="Search"/>
		</form>
	</div>
</div>

<div id="QuickLinks">
	<a href="Catalog.action?viewCategory=&categoryId=FISH">
		<img src="<c:url value='/images/sm_fish.gif'/>" />
	</a>
	<img src="<c:url value='/images/separator.gif'/>" />
	<a href="Catalog.action?viewCategory=&categoryId=DOGS">
		<img src="<c:url value='/images/sm_dogs.gif'/>" />
	</a>
	<img src="<c:url value='/images/separator.gif'/>" />
	<a href="Catalog.action?viewCategory=&categoryId=REPTILES">
		<img src="<c:url value='/images/sm_reptiles.gif'/>" />
	</a>
	<img src="<c:url value='/images/separator.gif'/>" />
	<a href="Catalog.action?viewCategory=&categoryId=CATS">
		<img src="<c:url value='/images/sm_cats.gif'/>" />
	</a>
	<img src="<c:url value='/images/separator.gif'/>" />
	<a href="Catalog.action?viewCategory=&categoryId=BIRDS">
		<img src="<c:url value='/images/sm_birds.gif'/>" />
	</a>
</div>

</div>

<div id="Content">
