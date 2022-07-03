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
		<jsp:include page="/components/common/toast.jsp"></jsp:include>
      <!-- My Events header stuff -->
      <div class="row">
        <div class="col-2 my-auto">
          <h2>Invitations</h2>
        </div>
      </div>
      <!-- My events cards panel -->
      <div class="container-fluid">
      <div class="row row-cols-3">
      <c:if test="${empty invitedeve}">
			<p class="badge badge-pill badge-dark mt-3">You don't any invitations...</p>
		</c:if>
      <c:forEach var="event" items="${invitedeve}" varStatus="loop">
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
              <a href="<%=request.getContextPath()%>/invites?action=acceptinvite&evid=${event.eventId}" class="btn btn-primary mt-3" data-toggle="tooltip" data-placement="top" title="Join this event"> ACCEPT&nbsp;<i class="bi bi-plus-circle"></i></a>
            </div>
          </div>
        </div>
        </c:forEach>
      </div>
    </div>
    <hr>
    </div>
	<!-- Bootstrap scripts -->
	<jsp:include page="/components/common/footer.jsp"></jsp:include>
  </body>
</html>
