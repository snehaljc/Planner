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
    <form>
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
              <select id="cityname" name="cityname" class="form-control">
                <option selected>Nashik</option>
                <option>Pune</option>
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
              <input type="date" class="form-control" id="eventdate" name="eventdate" required>
            </div>
          </div>
          <!-- Time -->
          <div class="form-group row">
            <label for="eventtime" class="col-sm-3 col-form-label">Time</label>
            <div class="col-sm-8">
              <input type="time" class="form-control" id="eventtime" name="eventtime" required>
            </div>
          </div>
          <!-- Uploda Image -->
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
                <input type="radio" id="publictype" name="publictype" class="custom-control-input" checked>
                <label class="custom-control-label" for="publictype">This is a public event</label>
              </div>
              <div class="custom-control custom-radio">
                <input type="radio" id="privatetype" name="privatetype" class="custom-control-input">
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
                        list='userlist' name='userlist'>
                        <div class="input-group-append">
                          <button class="btn btn-outline-primary add_user" type="button" id="button-addon2" disabled="false">Add</button>
                        </div>
                      </div>
                  <datalist id="userlist">
                    <option value="PHP">PHP</option>
                    <option value="JavaScript">JavaScript</option>
                    <option value="Cobol">Cobol</option>
                    <option value="C#">C#</option>
                    <option value="C++">C++</option>
                    <option value="Java">Java</option>
                    <option value="Pascal">Pascal</option>
                    <option value="FORTRAN">FORTRAN</option>
                    <option value="Lisp">Lisp</option>
                    <option value="Swift">Swift</option>
                </datalist>
              <!-- <div class="">
                <input type="text" value='166,149' placeholder='Write your country name' class='flexdatalist'
                  id="flexdatalist" data-data='countries.json' data-search-in='name'
                  data-visible-properties='["name","capital","continent"]' data-selection-required='true'
                  data-value-property='id' data-text-property='{capital}, {name}, {continent}' data-min-length='1'
                  multiple='multiple' name='country_preselect_id_multiple'>
              </div> -->
            </div>
          </div>
          <!-- Invitation list -->
          <div class="form-group row">
            <label for="eventinvitelist" class="col-sm-3 col-form-label">Invitation list</label>
            <div class="col-sm-8">
              <!-- <small id="nouserlistmsg" class="form-text text-muted mt-2">No users added to invitation list yet.</small> -->
              <table class="table" id="addedusertable">
              </table>
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
