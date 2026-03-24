<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog"><form action="Order.action" method="post">

	<table>
		<tr>
			<th colspan=2>Payment Details</th>
		</tr>
		<tr>
			<td>Card Type:</td>
			<td><select name="cardType">
				<c:forEach var="item" items="${creditCardTypes}">
					<option value="${item}">${item}</option>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>Card Number:</td>
			<td><input type="text" name="creditCard" /> * Use a fake
			number!</td>
		</tr>
		<tr>
			<td>Expiry Date (MM/YYYY):</td>
			<td><input type="text" name="expiryDate" /></td>
		</tr>
		<tr>
			<th colspan=2>Billing Address</th>
		</tr>

		<tr>
			<td>First name:</td>
			<td><input type="text" name="billToFirstName" /></td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td><input type="text" name="billToLastName" /></td>
		</tr>
		<tr>
			<td>Address 1:</td>
			<td><input type="text" size="40" name="billAddress1" /></td>
		</tr>
		<tr>
			<td>Address 2:</td>
			<td><input type="text" size="40" name="billAddress2" /></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><input type="text" name="billCity" /></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><input type="text" size="4" name="billState" /></td>
		</tr>
		<tr>
			<td>Zip:</td>
			<td><input type="text" size="10" name="billZip" /></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td><input type="text" size="15" name="billCountry" /></td>
		</tr>

		<tr>
			<td colspan=2><input type="checkbox" name="shippingAddressRequired" />
			Ship to different address...</td>
		</tr>

	</table>

	<input type="submit" name="newOrder" value="Continue" />

</form></div>

<%@ include file="../common/IncludeBottom.jsp"%>
