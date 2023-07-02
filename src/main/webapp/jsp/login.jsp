<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="javax.servlet.http.HttpSession" %>
 <!-- Session check -->
<%
   
    if (session!= null && session.getAttribute("email") != null) {
        // Session exists and user is logged in
        response.sendRedirect("/DataOrg/home");
    }
%> 


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<title>DataOrg</title>
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
</head>
<body>
	<div class="d-flex justify-content-center align-items-center"
		style="min-height: 100vh;">
		<div class="cointainer">
			<!-- Pills navs -->
			<ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
				<li class="nav-item" role="presentation"><a
					class="nav-link active" id="tab-login" data-mdb-toggle="pill"
					href="#pills-login" role="tab" aria-controls="pills-login"
					aria-selected="true">Login</a></li>
				<li class="nav-item" role="presentation"><a class="nav-link"
					id="tab-register" data-mdb-toggle="pill" href="#pills-register"
					role="tab" aria-controls="pills-register" aria-selected="false">Register</a>
				</li>
			</ul>
			<!-- Pills navs -->

			<!-- Pills content -->

			<div class="tab-content">
				<div class="tab-pane fade show active" id="pills-login"
					role="tabpanel" aria-labelledby="tab-login">
					<form action="${pageContext.request.contextPath}/login"
						, method="post">

						<!-- Email input -->
						<div class="form-outline mb-4">
							<input type="email" name="email" id="loginName"
								class="form-control" /> <label class="form-label"
								for="loginName">Email or username</label>
						</div>

						<!-- Password input -->
						<div class="form-outline mb-4">
							<input type="password" name="password" id="loginPassword"
								class="form-control" /> <label class="form-label"
								for="loginPassword">Password</label>
						</div>

						<!-- 2 column grid layout -->
						<div class="row mb-4">
							<div class="col-md-6 d-flex justify-content-center">
								<!-- Checkbox -->
								<div class="form-check mb-3 mb-md-0">
									<input class="form-check-input" type="checkbox" value=""
										id="loginCheck" checked /> <label class="form-check-label"
										for="loginCheck"> Remember me </label>
								</div>
							</div>

							<div class="col-md-6 d-flex justify-content-center">
								<!-- Simple link -->
								<a href="#!">Forgot password?</a>
							</div>
						</div>

						<!-- Submit button -->
						<button type="submit" class="btn btn-primary btn-block mb-4">Sign
							in</button>

						<!-- Register buttons -->

					</form>
				</div>
				<div class="tab-pane fade" id="pills-register" role="tabpanel"
					aria-labelledby="tab-register">
					
					
					
					<form action="${pageContext.request.contextPath}/register"
						, method="post">
						<!-- Name input -->


						<!-- Username input -->
						<div class="form-outline mb-4">
							<input type="text" name="username" id="registerUsername"
								class="form-control" /> <label class="form-label"
								for="registerUsername">Username</label>
						</div>

						<!-- Email input -->
						<div class="form-outline mb-4">
							<input type="email" name="email" id="registerEmail"
								class="form-control" /> <label class="form-label"
								for="registerEmail">Email</label>
						</div>

						<!-- Password input -->
						<span id="message1"></span>
						<div class="form-outline mb-4">
							<input type="password" name="password" id="registerPassword"
								class="form-control" onkeyup="validatePassword();" /> <label
								class="form-label" for="registerPassword">Password</label> 
						</div>

						<!-- Repeat Password input -->
						<span id="message2"></span>
						<div class="form-outline mb-4">
							<input type="password" id="registerRepeatPassword"
								class="form-control" onkeyup="validatePassword();" /> <label
								class="form-label" for="registerRepeatPassword">Repeat
								password</label> 
								
								
						</div>
						<!-- Checkbox -->


						<!-- Submit button -->
						<button type="submit" id="submitButton"
							class="btn btn-primary btn-block mb-3">Sign up</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Pills content -->


	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.0/js/bootstrap.min.js"></script>

	<!-- MDB JS -->
	<script src="js/mdb.min.js"></script>

	<!--  Password validation -->
	<script>
		function validatePassword() {
			var pattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[a-zA-Z!@#$%^&*]{6,}$/;
			var password = document.getElementById("registerPassword").value;
			var repeatPassword = document
					.getElementById("registerRepeatPassword").value;
			var submitButton = document.getElementById("submitButton");

			if (!pattern.test(password)) {
				submitButton.disabled = true;
				document.getElementById('message1').style.color = 'red';
				document.getElementById("message1").innerHTML = 'Atleast 6 Characters with 1 Special character needed';
			} else {
				submitButton.disabled = false;
				document.getElementById('message1').style.color = 'green';
				document.getElementById("message1").innerHTML = 'Valid password';
			}

			if (password !== repeatPassword) {
				submitButton.disabled = true;
				document.getElementById('message2').style.color = 'red';
				document.getElementById("message2").innerHTML = 'Password Not Matched';
			} else {
				submitButton.disabled = false;
				document.getElementById('message2').style.color = 'green';
				document.getElementById('message2').innerHTML = 'Password Matched';
			}
		}
	</script>
	
	
	<!--  Password eye icon logic -->
	<script>
	function togglePasswordVisibility(inputId) {
	    const passwordInput = document.querySelector(inputId);
	    const icon = document.querySelector(`span[onclick="togglePasswordVisibility('${inputId}')]`);
	    if (passwordInput.type === "password") {
	        passwordInput.type = "text";
	        icon.classList.remove("fa-eye");
	        icon.classList.add("fa-eye-slash");
	    } else {
	        passwordInput.type = "password";
	        icon.classList.remove("fa-eye-slash");
	        icon.classList.add("fa-eye");
	    }
	}
	</script>






	<!--  Login credentials check -->
	<%
	String message = (String) request.getAttribute("login");

	if (message != null) {
		if (message.equals("valid")) {
			out.println("<script>alert('User exists')</script>");

		} else if (message.equals("invalid")) {
			out.println("<script>alert('Invalid credentials');window.location.href = '/DataOrg/';</script>");
		}
	}
	%>

	<!-- Register credentials check -->
	<%
	String reg_message = (String) request.getAttribute("register");

	if (reg_message != null) {
		if (reg_message.equals("valid")) {
			out.println(
			"<script>alert('User records inserted successfully can login');window.location.href = '/DataOrg/';console.log('success')</script>");

		} else if (reg_message.equals("invalid")) {
			out.println("<script>alert('user alreadt exists');window.location.href = '/DataOrg/';</script>");
		}
	}
	%>


</body>
</html>