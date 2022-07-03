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
        <h2>Event Details</h2>
      </div>
    </div>
    <hr />
    <!--Event Info-->
   	    <nav>
      <div class="nav nav-tabs" id="nav-tab" role="tablist">
        <a class="nav-link active" id="nav-basic-tab" data-toggle="tab" href="#nav-basic" role="tab" aria-controls="nav-basic">Event Details</a>
        <a class="nav-link" id="nav-users-tab" data-toggle="tab" href="#nav-users" role="tab" aria-controls="nav-users">Invitations</a>
      </div>
    </nav>
    <!-- Form for new event -->
      <div class="tab-content mt-5" id="nav-tabContent">
        <div class="tab-pane fade show active" id="nav-basic" role="tabpanel" aria-labelledby="nav-basic-tab">
          <div class="row">
            <div class="col-6" style="padding-right:20px; border-right: 1px solid #ccc;">
              <!-- Event name -->
              <div class="form-group row">
                <label for="eventname" class="col-sm-3"><b>Event Name</b></label>
                <div class="col-sm-8 ">
                    <p class="view-event-lable-value" id="eventname" name="eventname">${event.name}</p>
                </div>
              </div>
              <!-- Event location -->
              <div class="form-group row">
                <label for="cityname" class="col-sm-3"><b>City</b></label>
                <div class="col-sm-8">
                    <p class="my-auto view-event-lable-value" id="cityname" name="cityname">${event.location}</p>
                </div>
              </div>
              <!-- Address -->
              <div class="form-group row">
                <label for="eventaddress" class="col-sm-3"><b>Address</b></label>
                <div class="col-sm-8">
                    <p class="my-auto view-event-lable-value" id="eventaddress" name="eventaddress">${event.address}</p>
                </div>
              </div>
              <!-- Date -->
              <div class="form-group row">
                <label for="eventdate" class="col-sm-3"><b>Date</b></label>
                <div class="col-sm-8">
                  <p class="my-auto view-event-lable-value" id="eventdate" name="eventdate">${event.datetime}</p>
                </div>
              </div>
              <!-- Description of event -->
              <div class="form-group row">
                <label for="eventdesc" class="col-sm-3"><b>Description</b></label>
                <div class="col-sm-8">
                    <p class="my-auto view-event-lable-value" id="eventdesc" name="eventdesc">${event.description}</p>
                </div>
              </div>
              <!-- Event fee -->
              <div class="form-group row">
                <label for="eventfee" class="col-sm-3"><b>Event Fee</b></label>
                <div class="col-sm-8">
                  <p class="my-auto view-event-lable-value" id="eventfee" name="eventfee">${event.fee}</p>
                </div>
              </div>
              <!-- Event type -->
              <div class="form-group row">
                <label for="publictype" class="col-sm-3"><b>Event Type</b></label>
                <div class="col-sm-8">
                  <p class="my-auto view-event-lable-value" id="publictype" name="publictype">
                  <c:choose>
    		<c:when test="${event.ispublic == true}">
				Public
    		</c:when>    
    		<c:otherwise>
				Private
    		</c:otherwise>
		  </c:choose>
                  </p>
                </div>
              </div>
              <!-- Event Created on -->
              <div class="form-group row">
                <label for="eventcreatedon" class="col-sm-3"><b>Created On</b></label>
                <div class="col-sm-8">
                  <p class="my-auto view-event-lable-value" id="eventcreatedon" name="eventcreatedon">${event.createdOn}</p>
                </div>
              </div>
              <!-- Event Modified on -->
              <div class="form-group row">
                <label for="eventmodifiedon" class="col-sm-3"><b>Modified On</b></label>
                <div class="col-sm-8">
                  <p class="my-auto view-event-lable-value" id="eventmodifiedon" name="eventmodifiedon">${event.modifiedOn}</p>
                </div>
              </div>
            </div>
            <div class="col-6">
                <div class="form-group row">
                    <label class="col-sm-3"><b>Cover Image</b></label>
                </div>
                <div class="form-group row">
                <c:choose>
    		<c:when test="${empty event.image}">
				<img height="350px" width="550px" src="assets/images/undraw_events_re_98ue.svg" class="card-img-top" alt="...">
    		</c:when>    
    		<c:otherwise>
				<img height="350px" width="550px" src="data:image/jpg;base64,${event.image}">
    		</c:otherwise>
		  </c:choose>
                </div>
            </div>   
          </div>
        </div>  
        <div class="tab-pane fade show" id="nav-users" role="tabpanel" aria-labelledby="nav-users-tab">
            <div class="row">
                <div class="col-8 offset-2">
                    <div class="form-group row">
                        <h5 class="col-sm-6 offset-5"><b>Invitition list</b></h5>
                        <table class="table">
                            <thead class="thead-light">
                              <tr>
                                <th scope="col">#</th>
                                <th scope="col">Name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Accepted</th>
                              </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="user" items="${eveusers}" varStatus="loop">
                            <tr>
                                <th scope="row">${loop.count}</th>
                                <td>${user.user }</td>
                                <td>${user.email }</td>
                                <td>
                                <c:choose>
						    		<c:when test="${user.accepted eq true}">
										<i class="bi bi-check-circle-fill icon-green icon-md"></i>
						    		</c:when>    
						    		<c:otherwise>
										<i class="bi bi-x-circle-fill icon-red icon-md"></i>
						    		</c:otherwise>
								</c:choose>
								</td>
                              </tr>
                            </c:forEach>
                            </tbody>
                          </table>
                      </div>
                </div>
            </div>
        </div>
      </div>
    </div>
	<!-- Bootstrap scripts -->
	<jsp:include page="/components/common/footer.jsp"></jsp:include>
  </body>
</html>
