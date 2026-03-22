<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink"><a
	href="Catalog.action?viewProduct=&productId=${actionBean.product.productId}">
	Return to ${actionBean.product.productId}
</a></div>

<div id="Catalog">

<table>
	<tr>
		<td>${actionBean.product.description}</td>
	</tr>
	<tr>
		<td><b> ${actionBean.item.itemId} </b></td>
	</tr>
	<tr>
		<td><b><font size="4"> ${actionBean.item.attribute1}
		${actionBean.item.attribute2} ${actionBean.item.attribute3}
		${actionBean.item.attribute4} ${actionBean.item.attribute5}
		${actionBean.product.name} </font></b></td>
	</tr>
	<tr>
		<td>${actionBean.product.name}</td>
	</tr>
	<tr>
		<td><c:if test="${actionBean.item.quantity <= 0}">
        Back ordered.
      </c:if> <c:if test="${actionBean.item.quantity > 0}">
      	${actionBean.item.quantity} in stock.
	  </c:if></td>
	</tr>
	<tr>
		<td><fmt:formatNumber value="${actionBean.item.listPrice}"
			pattern="$#,##0.00" /></td>
	</tr>

	<tr>
		<td><a class="Button"
			href="Cart.action?addItemToCart=&workingItemId=${actionBean.item.itemId}">
       	Add to Cart
       </a></td>
	</tr>
</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>
