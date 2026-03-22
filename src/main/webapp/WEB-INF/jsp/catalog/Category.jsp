<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink"><a href="Catalog.action">Return to Main Menu</a></div>

<div id="Catalog">

<h2>${actionBean.category.name}</h2>

<table>
	<tr>
		<th>Product ID</th>
		<th>Name</th>
	</tr>
	<c:forEach var="product" items="${actionBean.productList}">
		<tr>
			<td><a href="Catalog.action?viewProduct=&productId=${product.productId}">
				${product.productId}
			</a></td>
			<td>${product.name}</td>
		</tr>
	</c:forEach>
</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>

