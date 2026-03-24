<%@ include file="../common/IncludeTop.jsp"%>

<div id="Welcome">
<div id="WelcomeContent">
	<c:if test="${authenticated == true}">
		Welcome ${account.firstName}!
	</c:if>
</div>
</div>

<div id="Main">
<div id="Sidebar">
<div id="SidebarContent"><a
	href="Catalog.action?viewCategory=&categoryId=FISH">
	<img src="<c:url value='/images/fish_icon.gif'/>" />
</a> <br />
Saltwater, Freshwater <br />
<a
	href="Catalog.action?viewCategory=&categoryId=DOGS">
	<img src="<c:url value='/images/dogs_icon.gif'/>" />
</a> <br />
Various Breeds <br />
<a
	href="Catalog.action?viewCategory=&categoryId=CATS">
	<img src="<c:url value='/images/cats_icon.gif'/>" />
</a> <br />
Various Breeds, Exotic Varieties <br />
<a
	href="Catalog.action?viewCategory=&categoryId=REPTILES">
	<img src="<c:url value='/images/reptiles_icon.gif'/>" />
</a> <br />
Lizards, Turtles, Snakes <br />
<a
	href="Catalog.action?viewCategory=&categoryId=BIRDS">
	<img src="<c:url value='/images/birds_icon.gif'/>" />
</a> <br />
Exotic Varieties</div>
</div>

<div id="MainImage">
<div id="MainImageContent">
  <map name="estoremap">
	<area alt="Birds" coords="72,2,280,250"
		href="Catalog.action?viewCategory=&categoryId=BIRDS" shape="RECT" />
	<area alt="Fish" coords="2,180,72,250"
		href="Catalog.action?viewCategory=&categoryId=FISH" shape="RECT" />
	<area alt="Dogs" coords="60,250,130,320"
		href="Catalog.action?viewCategory=&categoryId=DOGS" shape="RECT" />
	<area alt="Reptiles" coords="140,270,210,340"
		href="Catalog.action?viewCategory=&categoryId=REPTILES" shape="RECT" />
	<area alt="Cats" coords="225,240,295,310"
		href="Catalog.action?viewCategory=&categoryId=CATS" shape="RECT" />
	<area alt="Birds" coords="280,180,350,250"
		href="Catalog.action?viewCategory=&categoryId=BIRDS" shape="RECT" />
  </map> 
  <img height="355" src="<c:url value='/images/splash.gif'/>" align="middle"
	usemap="#estoremap" width="350" /></div>
</div>

<div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
