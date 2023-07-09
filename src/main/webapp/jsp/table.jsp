<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
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
					<button type="button" class="btn btn-primary"
						data-mdb-toggle="modal" data-mdb-target="#exampleModal"
						style="float: right;">Add Data</button>
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
						class="pt-3 pb-4 text-center font-bold font-up deep-purple-text">${tableName}</h2>
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


				<%-- Check if the userList is empty --%>


				<thead>
					<tr>



						<%
						List<String> columnNames = (List<String>)request.getAttribute("columnNames");
						if (!columnNames.isEmpty()) {
							for (String columnName : columnNames) {
						%>
						<th><%=columnName%></th>
						<%
						}
						}
						
						%>
                        <th>Action</th>
                        <th>Action</th>

					</tr>
				</thead>
				<!--Table head-->
				<!--Table body-->
				<%
			List<Map<String, Object>> dataList = (List<Map<String, Object>>) request.getAttribute("dataList");
			if (dataList != null && !dataList.isEmpty()) {
			%>
				<tbody>

					<%
					for (Map<String, Object> row : dataList) {
					%>
					<tr>
						<%
						boolean isFirstColumn = true;
			            Object primaryKeyValue = null;
						for (Map.Entry<String, Object> entry : row.entrySet()) {
							if (isFirstColumn) {
			                    primaryKeyValue = entry.getValue();
			                    isFirstColumn = false;
						%>
						<td><%=entry.getValue()%></td>
						 <%
                } else {
                    %>
                    <td><%= entry.getValue() %></td>
						<%
						}
						}
						%>
						
						<!-- Update Action -->
						<td>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updateModal<%= primaryKeyValue %>">Update</button>
        </td>
        
        	<!-- Update Modal -->
	  
    <div class="modal fade" id="updateModal<%= primaryKeyValue %>" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel<%= primaryKeyValue %>" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="updateModalLabel<%= primaryKeyValue %>">Update Values</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="updateForm<%= primaryKeyValue %>">
                        <input type="hidden" id="tableName" name="tableName" value="${tableName}" />
                        <input type="hidden" name="primarykey" value="<%= primaryKeyValue %>">
                        <%
                        for (String columnName : columnNames) {
                            %>
                            <div class="form-outline mb-4">
							<input type="username" name="UpdatefieldValue" id="<%=columnName%>"
								class="form-control"  required /> <label class="form-label"
								for="loginName"><%=columnName%></label>
						</div>
                            
                            
                           
                        <%
                        }
                        %>
                        <button type="submit" class="btn btn-primary">Update</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- end update modal -->
        
						<!--  End update -->
						
						<!-- Delete form -->
						<td><form id="deletedata">
						<input type="hidden"  id="tableName" name="tableName" value="${tableName}" /> 
						 <input type="hidden" name="primarykey" value="<%= primaryKeyValue %>">
						 <input type="hidden" name="columnName" value="<%=columnNames.get(0) %>">
						 <button type="submit" class="btn btn-danger">Delete
							</button></form></td>
							<!-- end delete form -->
					</tr>
					<%
					}
					%>
				</tbody>
				<%
			} else {
			%>
			<p>No data found Insert Some values</p>
			<%
			}
			%>
			</table>
			
			
			<!--Table-->
		</div>
	</div>

	
	
	
	

	
	



	<!-- script -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.0/js/bootstrap.min.js"></script>

	<!-- MDB JS -->
	<script src="js/mdb.min.js"></script>

	<!-- dynamic input field -->
	

	<!--  Ajax to submit Insert Action form -->
	<script>
		$(document).ready(function() {
			$("#tableform").submit(function(event) {
				// Prevent form submission
				event.preventDefault();

				// Get form data
				var formData = $(this).serialize();
				

				// Send AJAX request to the servlet
				$.ajax({
					type : "POST",
					url : "table",
					data : formData,
					success : function(response) {
						// Handle the response from the servlet
						if (response === "success") {
							alert('success');
							location.reload();
						} else {
							alert("Error occured ,Primary Key Duplicate or Db Error");
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
	
	
	
		<!--  Ajax to submit Delete Action form -->
	<script>
		$(document).ready(function() {
			$("#deletedata").submit(function(event) {
				// Prevent form submission
				event.preventDefault();

				// Get form data
				var formData = $(this).serialize();
				

				// Send AJAX request to the servlet
				$.ajax({
					type : "POST",
					url : "deletedata",
					data : formData,
					success : function(response) {
						// Handle the response from the servlet
						if (response === "success") {
							alert('Successfully Deleted');
							location.reload();
						} else {
							alert("Error occured ,Unable to delete");
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
	
	
			<!--  Ajax to Update Action form -->
	
	
	
	
	
	
	
</body>
</html>