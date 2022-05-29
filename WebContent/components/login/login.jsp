<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <div class="container">
      <div class="row">
        <div class="col-md-6 align-self-center">
          <div class="brand-graphic">
            <img src="assets/images/undraw_events_re_98ue.svg" alt="Infographic" />
          </div>
        </div>
        <div class="col-md-6">
          <div class="branding mt-3">
            <img src="assets/images/planner-sm.png" alt="Brand image" />
          </div>
 <c:if test="${not empty toast}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
  				${toast}
  		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
		</div>
		</c:if>
          <div class="form-login" id="login">
            <h3>Sign In</h3>
            <form action="<%=request.getContextPath()%>/login" method="post">
            <input type="hidden" name="pageName" value="login"/>
              <div class="form-group">
                <label for="exampleInputEmail1">Email address</label>
                <input
                  type="email"
                  class="form-control"
                  id="username"
                  name="username"
                  aria-describedby="emailHelp"
                />
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input
                  type="password"
                  class="form-control"
                  id="password"
                  name="password"
                />
              </div>
              <button type="submit" class="btn btn-primary">Login</button>
              <p id="loginHelp" class="form-text text-muted">
                Don't have an account with Planner?
                <button type="button" class="log-in-toggle" onclick="toggleLoginSignup()">Create an account</button>
              </p>
            </form>
          </div>
          <div class="form-register" id="register" style="display: none;">
            <h3>Sign Up</h3>
            <form action="<%=request.getContextPath()%>/login" method="post">
            <input type="hidden" name="pageName" value="register"/>
              <div class="form-group">
                <label for="firstname">First Name</label>
                <input type="text" class="form-control" id="fname" name="fname" required/>
              </div>
              <div class="form-group">
                <label for="lastname">Last Name</label>
                <input type="text" class="form-control" id="lname" name="lname" required />
              </div>
              <div class="form-group">
                <label for="email">Email address</label>
                <input type="email" class="form-control" id="email" name="email" required/>
              </div>
              <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required/>
              </div>
              <button type="submit" class="btn btn-primary">Register</button>
              <p id="loginHelp" class="form-text text-muted">
                Already have an account?
                <button type="button" class="log-in-toggle" onclick="toggleLoginSignup()">Log in</button>
              </p>
            </form>
          </div>
        </div>
      </div>
    </div>