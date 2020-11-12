<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>
<div class="row">
		<div class="container">
			<c var="result" items="${result}">
				<c:out value="${result}" />
			</c>
			<br><br>
			<h3 class="text-center">List of Products</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>" class="btn btn-success">Add
					New Product</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Name</th>
						<th>Barcode</th>
						<th>Color</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="product" items="${listProduct}">

						<tr>
							<td><c:out value="${product.name}" /></td>
							<td><c:out value="${product.barcode}" /></td>
							<td><c:out value="${product.color}" /></td>
							<td><c:out value="${product.description}" /></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>

</body>
</html>