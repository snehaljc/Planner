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
      <!-- My Events header stuff -->
      <div class="row">
        <div class="col-2 my-auto">
          <h2>My Events</h2>
        </div>
        <div class="col-2 my-auto">
          <a href="events.html" class="btn btn-primary btn-sm">New Event</a>
        </div>
        <div class="col-3 offset-5">
          <div class="input-group my-auto mt-1">
            <input type="text" class="form-control" placeholder="Search events">
            <div class="input-group-append">
              <button class="btn btn-outline-secondary" type="button" id="button-addon2">
                <i class="bi bi-search"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
      <!-- My events cards panel -->
      <div class="container-fluid">
      <div class="row row-cols-3">
        <div class="col mt-4">
          <a href="#1" class="nostyle">
          <div class="card" style="width: 18rem;">
            <img src="assets/images/undraw_events_re_98ue.svg" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Birthday bang!!</h5>
              <h6 class="card-subtitle mb-2 text-muted">Nashik</h6>
              <p class="card-text mb-3">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
              <a href="#" class="btn btn-primary" data-toggle="tooltip" data-placement="top" title="Edit Event"><i class="bi bi-pen"></i></a>
              <a href="#" class="btn btn-danger"  data-toggle="tooltip" data-placement="top" title="Delete Event"><i class="bi bi-trash3"></i></a>
              <a href="#" class="btn btn-outline-secondary float-right"  data-toggle="tooltip" data-placement="top" title="Share Event"><i class="bi bi-share"></i></a>
            </div>
          </div>
        </a>
        </div>
        <div class="col mt-4">
          <div class="card" style="width: 18rem;">
            <img src="assets/images/undraw_events_re_98ue.svg" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Birthday bang!!</h5>
              <h6 class="card-subtitle mb-2 text-muted">Nashik</h6>
              <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
              <a href="#" class="btn btn-primary"><i class="bi bi-pen"></i></a>
              <a href="#" class="btn btn-danger"><i class="bi bi-trash3"></i></a>
              <a href="#" class="btn btn-outline-secondary float-right"><i class="bi bi-share"></i></a>
            </div>
          </div>
        </div>
        <div class="col mt-4">
          <div class="card" style="width: 18rem;">
            <img src="assets/images/undraw_events_re_98ue.svg" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Birthday bang!!</h5>
              <h6 class="card-subtitle mb-2 text-muted">Nashik</h6>
              <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
              <a href="#" class="btn btn-primary"><i class="bi bi-pen"></i></a>
              <a href="#" class="btn btn-danger"><i class="bi bi-trash3"></i></a>
              <a href="#" class="btn btn-outline-secondary float-right"><i class="bi bi-share"></i></a>
            </div>
          </div>
        </div>
      </div>
      <div class="row mt-2 justify-content-end">
          <a href="#" class="btn btn-outline-secondary">View all</a>
      </div>
    </div>
    <hr>
    <!-- Near by events header stuff -->
    <div class="row mt-5">
      <div class="col-4 my-auto">
        <h2>Events around you</h2>
      </div>
      <div class="col-3 offset-5">
        <select id="inputState" class="form-control">
          <option selected>Nashik</option>
          <option>Pune</option>
        </select>
      </div>
    </div>
      <!-- Near by events cards panel -->
      <div class="container-fluid">
        <div class="row row-cols-3">
          <div class="col mt-4">
            <a href="#1" class="nostyle">
            <div class="card" style="width: 18rem;">
              <img src="assets/images/undraw_events_re_98ue.svg" class="card-img-top" alt="...">
              <div class="card-body">
                <h5 class="card-title">Birthday bang!!</h5>
                <h6 class="card-subtitle mb-2 text-muted">Nashik</h6>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                <a href="#" class="btn btn-primary mt-3" data-toggle="tooltip" data-placement="top" title="Join this event"> JOIN&nbsp;<i class="bi bi-plus-circle"></i></a>
              </div>
            </div>
          </a>
          </div>
          <div class="col mt-4">
            <div class="card" style="width: 18rem;">
              <img src="assets/images/undraw_events_re_98ue.svg" class="card-img-top" alt="...">
              <div class="card-body">
                <h5 class="card-title">Birthday bang!!</h5>
                <h6 class="card-subtitle mb-2 text-muted">Nashik</h6>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                <a href="#" class="btn btn-primary" data-toggle="tooltip" data-placement="top" title="Join this event"> JOIN&nbsp;<i class="bi bi-plus-circle"></i></a>
              </div>
            </div>
          </div>
        </div>
        <div class="row mt-2 justify-content-end">
            <a href="#" class="btn btn-outline-secondary">View all</a>
        </div>
      </div>
    </div>
	<!-- Bootstrap scripts -->
	<jsp:include page="/components/common/footer.jsp"></jsp:include>
  </body>
</html>
