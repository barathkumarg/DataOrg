<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="com.DataOrg.Bean.UserTableMeta" %>
	    <%
   
    if (session.getAttribute("email") == null) {
        // Session exists and user is logged in
        response.sendRedirect("/DataOrg/");
    }
%> 
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>
.container {
	margin-top: 10px;
}

.dynamic-input {
	margin-bottom: 10px;
	padding: 5px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.dynamic-label {
	/* Add your custom styles for the label here */
	color: #333;
	font-weight: bold;
	/* Add any other styles you want */
}
</style>
<title>DataOrg Home</title>
<!--  ajax -->
<base href="${pageContext.request.contextPath}/">


<!-- MDB icon
    <link rel="icon" href="img/mdb-favicon.ico" type="image/x-icon" />  -->
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
<!-- Google Fonts Roboto -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap" />
<!-- MDB -->
<link rel="stylesheet" href="css/mdb.min.css" />

<!-- Modal -->


</head>
<body>
	<!-- Navbar -->
	<jsp:include page="/jsp/includes/nav.jsp" />
	<!-- Navbar -->
	<!--  Main content -->
	<div class="card mb-4">
		<div class="card-body">
			<div class="row">
	<div class="container">
		<button type="button" class="btn btn-primary" data-mdb-toggle="modal"
			data-mdb-target="#exampleModal" style="float: right;">
			Launch demo modal</button>
	</div>
	</div>
	</div>
	</div>


	<!-- Main content -->
	<div class="card mb-4">
		<div class="card-body">
			<div class="row">
				<!-- Grid column -->
				<div class="col-md-12">
					<h2
						class="pt-3 pb-4 text-center font-bold font-up deep-purple-text">Data Org user Table</h2>
					<div class="input-group md-form form-sm form-2 pl-0">
						<input class="form-control my-0 py-1 pl-3 purple-border"
							type="text" placeholder="Search something here..."
							aria-label="Search"> <span
							class="input-group-addon waves-effect purple lighten-2"
							id="basic-addon1"><a><i
								class="fa fa-search white-text" aria-hidden="true"></i></a></span>
					</div>
				</div>
				<!-- Grid column -->
			</div>
			<!-- Grid row -->
			<!--Table-->
			<table class="table table-striped">
				<!--Table head-->
				  <% ArrayList<UserTableMeta> userList = (ArrayList<UserTableMeta>) request.getAttribute("userList"); %>
				  
				  <%-- Check if the userList is empty --%>
				  <% if (userList.isEmpty()) { %>
				    <p>No data found.</p>
				  <% } else { %>
				<thead>
					<tr>
						<th>#</th>
						<th>Table Name</th>
						<th>DataOrg Name</th>
						<th>Time Created</th>
						<th>Action</th>
					</tr>
				</thead>
				<!--Table head-->
				<!--Table body-->
				<tbody>
					 <% for (int i = 0; i < userList.size(); i++) { %>
            <tr>
                <td><%= i + 1 %></td>
                <td><%= userList.get(i).getUserTableName() %></td>
                <td><%= userList.get(i).getDbName() %></td>
                <td><%= userList.get(i).getDate() %></td>
                <td><a href="${pageContext.request.contextPath}/table/<%= userList.get(i).getUserTableName() %>">View Table</a></td>
                
                <% } %>
      </tbody>
    </table>
  <% } %>
			<!--Table-->
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form id="tableform">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
						<button type="button" class="btn-close" data-mdb-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">

						<div class="form-outline mb-4">
							<input type="username" name="tablename" id="tablename"
								class="form-control" required /> <label class="form-label"
								for="loginName">Table name</label>
						</div>
						<div class="form-outline mb-4">
							<input type="username" name="primarykey" id="primarykey"
								class="form-control" required /> <label class="form-label"
								for="primaryName">Primary Key</label>
						</div>

						<div id="container">
							<!-- Initially no input box -->
						</div>
						<button type="button" class="btn btn-success" onclick="addInput()">Add
							column</button>


					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-mdb-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save
							changes</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- Modal -->



	<!-- script -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.0/js/bootstrap.min.js"></script>

	<!-- MDB JS -->
	<script src="js/mdb.min.js"></script>

	<!-- dynamic input field -->
	<script>
		function addInput() {
			var container = document.getElementById("container");

			var newDiv = document.createElement("div");
			newDiv.className = "form-outline mb-4"; // Apply CSS class to div

			var input = document.createElement("input");
			input.type = "text";
			input.name = "columnnames";
			input.className = "form-control "; // Apply CSS class to input

			var label = document.createElement("label"); // Create label element
			label.textContent = "Dynamic Input: "; // Set label text
			label.className = "form-label";
			label.htmlFor = "tablename";

			newDiv.appendChild(input);
			newDiv.appendChild(label);
			container.appendChild(newDiv);
		}
	</script>

	<!--  Ajax to submit form -->
	<script>
		$(document).ready(function() {
			$("#tableform").submit(function(event) {
				// Prevent form submission
				event.preventDefault();

				// Get form data
				var formData = $(this).serialize();
				var url = "${pageContext.request.contextPath}/register";

				// Send AJAX request to the servlet
				$.ajax({
					type : "POST",
					url : "home",
					data : formData,
					success : function(response) {
						// Handle the response from the servlet
						if (response === "success") {
							alert('success');
							window.location.href = '/DataOrg/home';
						} else {
							alert("Data not updated");
						}
					},
					error : function(xhr, status, error) {
						// Handle AJAX call errors
						alert("AJAX call failed");
					}
				});
			});
		});
	</script>
</body>
</html>