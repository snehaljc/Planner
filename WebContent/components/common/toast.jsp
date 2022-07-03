<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty toastSuccess}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
  				${toastSuccess}
  		<button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="clearToastSuccess()">
    		<span aria-hidden="true">&times;</span>
  		</button>
		</div>
</c:if>

<c:if test="${not empty toast}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
  				${toast}
  		<button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="clearToast()">
    		<span aria-hidden="true">&times;</span>
  		</button>
		</div>
</c:if>