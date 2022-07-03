<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
        <h2>Edit Event</h2>
      </div>
    </div>
    <hr />
    <!-- Form for new event -->
    <form action="<%=request.getContextPath()%>/events?action=editevent&evidedit=${evidedit}" method="post" enctype='multipart/form-data'>
      <div class="row">
        <div class="col-6" style="padding-right:20px; border-right: 1px solid #ccc;">
          <!-- Event name -->
          <div class="form-group row">
            <label for="eventname" class="col-sm-3 col-form-label">Event Name</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" id="eventname" name="eventname" value="${event.name}">
            </div>
          </div>
          <!-- Event location -->
          <div class="form-group row">
            <label for="cityname" class="col-sm-3 col-form-label">City</label>
            <div class="col-sm-8">
              <select id="cityname" name="eventcity" class="form-control" disabled="disabled">
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
              <textarea type="textarea" class="form-control" id="eventaddress" name="eventaddress"
                rows="3">${event.address}</textarea>
            </div>
          </div>
          <!-- Date -->
          <c:set var = "datevar" value ="${event.datetime}"/>
          <div class="form-group row">
            <label for="eventdate" class="col-sm-3 col-form-label">Date</label>
            <div class="col-sm-8">
              <input type="datetime-local" class="form-control" id="eventdate" name="eventdate" value=${fn:replace(datevar, ' ', 'T')}>
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
              <textarea type="textarea" class="form-control" id="eventdesc" name="eventdesc"
                rows="3">${event.description}</textarea>
            </div>
          </div>
          <!-- Event fee -->
          <div class="form-group row">
            <label for="eventfee" class="col-sm-3 col-form-label">Event Fee</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" id="eventfee" name="eventfee" value="${event.fee}">
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
                        <input type="text" placeholder="Search users to invite" class="form-control flexdatalist invite_user_input"
                              id="eventinvite" name="eventinvite" data-min-length='1' data-search-in='user' multiple="multiple"
                              data-visible-properties='["user","email"]'
                              data-selection-required='true'
                              data-value-property='sid'
                              value=${eveusersedit}
                              data-data= '<%=request.getContextPath()%>/getallusersjson'
                              >
               </div>
            </div>
          </div>
        </div>
      </div>
      <button type="submit" class="btn btn-primary float-right">Update</button>
    </form>
    </div>
	<!-- Bootstrap scripts -->
	<jsp:include page="/components/common/footer.jsp"></jsp:include>
  </body>
</html>
