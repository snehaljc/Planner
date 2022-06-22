<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<!-- Bootstrap styles -->
	<jsp:include page="/components/common/header.jsp"></jsp:include>
  <body>
  
	<jsp:include page="/components/common/navbar.jsp"></jsp:include>
	    <div class="container mt-3">
      <!-- Add new event header  -->
    <div class="row">
      <div class="col my-auto">
        <h2>Add New Event</h2>
      </div>
    </div>
    <hr />
    <!-- Form for new event -->
    <form action="<%=request.getContextPath()%>/events" method="post" enctype='multipart/form-data'>
      <div class="row">
        <div class="col-6" style="padding-right:20px; border-right: 1px solid #ccc;">
          <!-- Event name -->
          <div class="form-group row">
            <label for="eventname" class="col-sm-3 col-form-label">Event Name</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" id="eventname" name="eventname" required>
            </div>
          </div>
          <!-- Event location -->
          <div class="form-group row">
            <label for="cityname" class="col-sm-3 col-form-label">City</label>
            <div class="col-sm-8">
              <select id="cityname" name="eventcity" class="form-control">
              <c:forEach var="event" items="${neweventdata.locations}">
              	<option value=${event.key}>${event.value}</option>
              </c:forEach>
              </select>
            </div>
          </div>
          <!-- Address -->
          <div class="form-group row">
            <label for="eventaddress" class="col-sm-3 col-form-label">Address</label>
            <div class="col-sm-8">
              <textarea type="textarea" class="form-control" id="eventaddress" name="eventaddress" required
                rows="3"></textarea>
            </div>
          </div>
          <!-- Date -->
          <div class="form-group row">
            <label for="eventdate" class="col-sm-3 col-form-label">Date</label>
            <div class="col-sm-8">
              <input type="datetime-local" class="form-control" id="eventdate" name="eventdate" required>
            </div>
          </div>
          <!-- Upload Image -->
          <div class="form-group row">
            <label for="eventimage" class="col-sm-3 col-form-label">Cover Image</label>
            <div class="custom-file col-sm-8">
              <input type="file" class="custom-file-input" id="eventimage" name="eventimage">
              <label class="custom-file-label" for="eventimage">Choose file</label>
            </div>
          </div>
        </div>
        <div class="col-6">
          <!-- Description of event -->
          <div class="form-group row">
            <label for="eventdesc" class="col-sm-3 col-form-label">Description</label>
            <div class="col-sm-8">
              <textarea type="textarea" class="form-control" id="eventdesc" name="eventdesc" required
                rows="3"></textarea>
            </div>
          </div>
          <!-- Event fee -->
          <div class="form-group row">
            <label for="eventfee" class="col-sm-3 col-form-label">Event Fee</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" id="eventfee" name="eventfee" required>
            </div>
          </div>
          <!-- Event type -->
          <div class="form-group row">
            <label for="publictype" class="col-sm-3 col-form-label">Event Type</label>
            <div class="col-sm-8">
              <div class="custom-control custom-radio">
                <input type="radio" id="publictype" name="eventtype" value="publictype" class="custom-control-input" checked>
                <label class="custom-control-label" for="publictype">This is a public event</label>
              </div>
              <div class="custom-control custom-radio">
                <input type="radio" id="privatetype" name="eventtype" value="privatetype" class="custom-control-input">
                <label class="custom-control-label" for="privatetype">This is a private event</label>
              </div>
            </div>
          </div>
          <!-- Invite users -->
          <div class="form-group row">
            <label for="eventinvite" class="col-sm-3 col-form-label">Invite users</label>
            <div class="col-sm-8">
              <div class="input-group mb-3">
                        <input type="text" placeholder="Search users to invite" class="form-control flexdatalist invite_user_input" id="eventinvite" name="eventinvite" data-min-length='1'
                        list='userlist' name='userlist' multiple='multiple'>
                      </div>
                  <datalist id="userlist">
				<c:forEach var="event" items="${neweventdata.users}">
              	<option value=${event.key}>${event.value}</option>
              </c:forEach>
                </datalist>
            </div>
          </div>
        </div>
      </div>
      <button type="submit" class="btn btn-primary float-right">Submit</button>
    </form>
    </div>
	<!-- Bootstrap scripts -->
	<jsp:include page="/components/common/footer.jsp"></jsp:include>
  </body>
</html>
