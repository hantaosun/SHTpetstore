<h3>Account Information</h3>

<table>
	<tr>
		<td>First name:</td>
		<td><input type="text" name="firstName" /></td>
	</tr>
	<tr>
		<td>Last name:</td>
		<td><input type="text" name="lastName" /></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><input type="text" size="40" name="email" /></td>
	</tr>
	<tr>
		<td>Phone:</td>
		<td><input type="text" name="phone" /></td>
	</tr>
	<tr>
		<td>Address 1:</td>
		<td><input type="text" size="40" name="address1" /></td>
	</tr>
	<tr>
		<td>Address 2:</td>
		<td><input type="text" size="40" name="address2" /></td>
	</tr>
	<tr>
		<td>City:</td>
		<td><input type="text" name="city" /></td>
	</tr>
	<tr>
		<td>State:</td>
		<td><input type="text" size="4" name="state" /></td>
	</tr>
	<tr>
		<td>Zip:</td>
		<td><input type="text" size="10" name="zip" /></td>
	</tr>
	<tr>
		<td>Country:</td>
		<td><input type="text" size="15" name="country" /></td>
	</tr>
</table>

<h3>Profile Information</h3>

<table>
	<tr>
		<td>Language Preference:</td>
		<td><select name="languagePreference">
			<c:forEach var="item" items="${languages}">
				<option value="${item}">${item}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>Favourite Category:</td>
		<td><select name="favouriteCategoryId">
			<c:forEach var="item" items="${categories}">
				<option value="${item}">${item}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>Enable MyList</td>
		<td><input type="checkbox" name="listOption" /></td>
	</tr>
	<tr>
		<td>Enable MyBanner</td>
		<td><input type="checkbox" name="bannerOption" /></td>
	</tr>

</table>
