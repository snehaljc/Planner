<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<!-- Bootstrap styles -->
	<jsp:include page="/components/common/header.jsp"></jsp:include>
  <body>
  
	<jsp:include page="/components/common/navbar.jsp"></jsp:include>
	      <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="deleteModalLabel">Delete event</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
			Do you want to delete this event? This action is irreversible. 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <a href type="button" class="btn btn-danger action">Delete</a>
      </div>
    </div>
  </div>
</div>
	    <div class="container mt-3">
		<jsp:include page="/components/common/toast.jsp"></jsp:include>
      <!-- My Events header stuff -->
      <div class="row">
        <div class="col-2 my-auto">
          <h2>My Events</h2>
        </div>
        <div class="col-2 my-auto">
          <a href="<%=request.getContextPath()%>/events?action=newevent" class="btn btn-primary btn-sm">New Event</a>
        </div>
        <!--
        <div class="col-3 offset-5">
          <div class="input-group my-auto mt-1">
            <input type="text" class="form-control" placeholder="Search events">
            <div class="input-group-append">
              <button class="btn btn-outline-secondary" type="button" id="button-addon2">
                <i class="bi bi-search"></i>
              </button>
            </div>
          </div>
        </div> -->
      </div>
      <!-- My events cards panel -->
      <div class="container-fluid">
      <div class="row row-cols-3">
      <c:if test="${empty dashEvents}">
			<p class="badge badge-pill badge-dark mt-3">No events added yet...</p>
		</c:if>
      <c:forEach var="event" items="${dashEvents}" varStatus="loop">
      <c:if test="${loop.count le 3}">
        <div class="col mt-4">
          <div class="card" style="width: 18rem;">
          <c:choose>
    		<c:when test="${empty event.image}">
				<img height="220px" src="assets/images/undraw_events_re_98ue.svg" class="card-img-top" alt="...">
    		</c:when>    
    		<c:otherwise>
				<img height="220px" src="data:image/jpg;base64,${event.image}" class="card-img-top" alt="...">
    		</c:otherwise>
		  </c:choose>
            <div class="card-body">
              <h5 class="card-title"><c:out value="${event.name}" /></h5>
              <h6 class="card-subtitle mb-2 text-muted"><c:out value="${event.city}" /></h6>
              <p class="card-text mb-3"><c:out value="${event.description}" /></p>
              <a href="<%=request.getContextPath()%>/events?action=viewevent&evid=${event.eventId}" class="btn btn btn-warning"  data-toggle="tooltip" data-placement="top" title="View Event"><i class="bi bi-box-arrow-up-right"></i></a>
              <a href="<%=request.getContextPath()%>/events?action=editevent&evid=${event.eventId}" class="btn btn-primary"  data-toggle="tooltip" data-placement="top" title="Edit Event"><i class="bi bi-pen"></i></a>
              <button class="btn btn-danger" title="Delete Event" data-toggle="modal" data-target="#deleteModal" data-eventid=<c:out value="${event.eventId}" />><i class="bi bi-trash3"></i></button>
              <!--  <a href="#" class="btn btn-outline-secondary float-right"  data-toggle="tooltip" data-placement="top" title="Share Event"><i class="bi bi-share"></i></a> -->
            </div>
          </div>
        </div>
        </c:if>
        </c:forEach>
      </div>
      <!-- <c:if test="${dashEvents.size() gt 3}">
      <div class="row mt-2 justify-content-end">
          <a href="#" class="btn btn-outline-secondary">View all</a>
      </div>
      </c:if> -->
      <div class="row mt-2 justify-content-end">
          <a href="<%=request.getContextPath()%>/dashboard?action=alleve" class="btn btn-outline-secondary">View all</a>
      </div>
    </div>
    <hr>
    <!-- Near by events header stuff -->
    <div class="row mt-5">
      <div class="col-4 my-auto">
        <h2>Events around you</h2>
      </div>
      <div class="col-3 offset-5">
        <select id="nearbycityname" name="nearbyeventcity" class="form-control" onchange="updateNearByEvents(this)">
              <c:forEach var="event" items="${allLocations}">
              	<option value=${event.key}>${event.value}</option>
              </c:forEach>
              </select>
      </div>
    </div>
      <!-- Near by events cards panel -->
      <div class="container-fluid">
      <div class="row row-cols-3">
      <c:if test="${empty nearbyevents}">
			<p class="badge badge-pill badge-dark mt-3">No near by events yet...</p>
		</c:if>
      <c:forEach var="event" items="${nearbyevents}" varStatus="loop">
      <c:if test="${loop.count le 3}">
        <div class="col mt-4">
          <div class="card" style="width: 18rem;">
          <c:choose>
    		<c:when test="${empty event.image}">
				<img height="220px" src="assets/images/undraw_events_re_98ue.svg" class="card-img-top" alt="...">
    		</c:when>    
    		<c:otherwise>
				<img height="220px" src="data:image/jpg;base64,${event.image}" class="card-img-top" alt="...">
    		</c:otherwise>
		  </c:choose>
            <div class="card-body">
              <h5 class="card-title"><c:out value="${event.name}" /></h5>
              <h6 class="card-subtitle mb-2 text-muted"><c:out value="${event.city}" /></h6>
              <p class="card-text mb-3"><c:out value="${event.description}" /></p>
			<a href="#" class="btn btn-primary mt-3" data-toggle="tooltip" data-placement="top" title="Join this event"> JOIN&nbsp;<i class="bi bi-plus-circle"></i></a>
            </div>
          </div>
        </div>
        </c:if>
        </c:forEach>
      </div>
      <div class="row mt-2 justify-content-end">
          <a href="<%=request.getContextPath()%>/dashboard?action=alleve" class="btn btn-outline-secondary">View all</a>
      </div>
    </div>
    </div>
	<!-- Bootstrap scripts -->
	<jsp:include page="/components/common/footer.jsp"></jsp:include>
  </body>
</html>
