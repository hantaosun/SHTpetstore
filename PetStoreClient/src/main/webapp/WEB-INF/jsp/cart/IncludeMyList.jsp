<c:if test="${!empty myList}">
	<p>Pet Favorites <br />
	Shop for more of your favorite pets here.</p>
	<ul>
		<c:forEach var="product" items="${myList}">
			<li><a
				href="Catalog.action?viewProduct=&productId=${product.productId}">
			${product.name}
		</a> (${product.productId})</li>
		</c:forEach>
	</ul>

</c:if>
