<%@ include file="../common/IncludeTop.jsp"%>

<jsp:useBean id="catalog"
	class="org.mybatis.jpetstore.web.actions.CatalogActionBean" />

<div id="BackLink"><a
	href="Catalog.action?viewCategory=&categoryId=${actionBean.product.categoryId}">
	Return to ${actionBean.product.categoryId}
</a></div>

<div id="Catalog">

<h2>${actionBean.product.name}</h2>

<table>
	<tr>
		<th>Item ID</th>
		<th>Product ID</th>
		<th>Description</th>
		<th>List Price</th>
		<th>&nbsp;</th>
	</tr>
	<c:forEach var="item" items="${actionBean.itemList}">
		<tr>
			<td><a href="Catalog.action?viewItem=&itemId=${item.itemId}">
				${item.itemId}
			</a></td>
			<td>${item.product.productId}</td>
			<td>${item.attribute1} ${item.attribute2} ${item.attribute3}
			${item.attribute4} ${item.attribute5} ${actionBean.product.name}</td>
			<td><fmt:formatNumber value="${item.listPrice}"
				pattern="$#,##0.00" /></td>
			<td><a class="Button"
				href="Cart.action?addItemToCart=&workingItemId=${item.itemId}">
        	Add to Cart
        </a></td>
		</tr>
	</c:forEach>
	<tr>
		<td>
		</td>
	</tr>
</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>
