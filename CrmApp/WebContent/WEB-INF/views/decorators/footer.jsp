<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<footer class="footer text-center"> 2019 &copy; lenguyenminhhuy.com </footer>
	
	<input type="hidden" id="textNotify" value="${ notify }"/>
	
<!-- jQuery -->
    <script src="<c:url value="/assets/plugins/bower_components/jquery/dist/jquery.min.js"/>"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/assets/bootstrap/dist/js/bootstrap.min.js"/>"></script>
    <!--slimscroll JavaScript -->
    <script src="<c:url value="/assets/js/jquery.slimscroll.js"/>"></script>
    <!--Wave Effects -->
    <script src="<c:url value="/assets/js/waves.js"/>"></script>
    <!--Counter js -->
    <script src="<c:url value="/assets/plugins/bower_components/waypoints/lib/jquery.waypoints.js"/>"></script>
    <script src="<c:url value="/assets/plugins/bower_components/counterup/jquery.counterup.min.js"/>"></script>
    <!--Morris (BIỂU ĐỒ) -->
    <script src="<c:url value="/assets/plugins/bower_components/raphael/raphael-min.js"/>"></script>
    <script src="<c:url value="/assets/plugins/bower_components/morrisjs/morris.js"/>"></script>
    <!-- Custom Theme JavaScript -->
    <script src="<c:url value="/assets/js/custom.min.js"/>"></script>
    <script src="<c:url value="/assets/js/dashboard1.js"/>"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script>
    
    	var message = document.getElementById("textNotify").value;
    	// Kiem tra neu co message
    	if(message !== null && message.length > 0){
    		swal("Thông báo!", message, "success");
    	}
    </script>
    
    