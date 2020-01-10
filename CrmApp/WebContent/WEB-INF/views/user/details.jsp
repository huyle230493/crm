<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.myclass.util.UrlConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Chi tiết thành viên</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-4 col-xs-12">
			<div class="white-box">
				<div class="user-bg">
					<img width="100%" alt="user"
						src="<c:url value="/assets/plugins/images/large/img1.jpg" />">
					<div class="overlay-box">
						<div class="user-content">
							<a href="javascript:void(0)"><img
								src="<c:url value="/assets/plugins/images/users/genu.jpg"/>"
								class="thumb-lg img-circle" alt="img"></a>
							<h4 class="text-white">${ username }</h4>
							<h5 class="text-white">${ useremail }</h5>
						</div>
					</div>
				</div>
				<div class="user-btm-box">
					<div class="col-md-4 col-sm-4 text-center">
						<p class="text-purple">
							<i class="ti-facebook"></i>
						</p>
						<h4>${ newTasks }%</h4>
						<h6>Chưa thực hiện</h6>
					</div>
					<div class="col-md-4 col-sm-4 text-center">
						<p class="text-blue">
							<i class="ti-twitter"></i>
						</p>
						<h4>${ todoTasks }%</h4>
						<h6>Đang thực hiện</h6>
					</div>
					<div class="col-md-4 col-sm-4 text-center">
						<p class="text-danger">
							<i class="ti-dribbble"></i>
						</p>
						<h4>${ doneTasks }%</h4>
						<h6>Hoàn thành</h6>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
				<form class="form-horizontal form-material">
					<div class="form-group">
						<label class="col-md-4">ID</label>
						<p class="col-md-4">${ id }</p>
					</div>
					<div class="form-group">
						<label class="col-md-4">Full Name</label>
						<p class="col-md-4">${ name }</p>
					</div>
					<div class="form-group">
						<label for="example-email" class="col-md-4">Email</label>
						<p class="col-md-4">${ email }</p>
					</div>
					<div class="form-group">
						<label class="col-md-4">Role</label>
						<p class="col-md-4">${ role }</p>
					</div>
					<div class="form-group">
						<label class="col-md-4">Country</label>
						<p class="col-md-4">Thành phố Hồ Chí Minh</p>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br />
	<!-- /.row -->
	<!-- BEGIN DANH SÁCH CÔNG VIỆC -->
	<h4>DANH SÁCH CÔNG VIỆC</h4>
	<div class="row">
		<div class="col-md-4">
			<div class="white-box" style="height: 240px; overflow: auto;">
				<h3 class="box-title">Chưa thực hiện</h3>
				<div class="message-center">
					<c:forEach items="${ tasks }" var="item" varStatus="loop">
						<c:if test="${ item.statusId == 1 }">
							<a href="#">
								<div class="mail-contnet">
									<h5>${ item.name }</h5>
									<span class="mail-desc">${ item.jobName }</span> <span
										class="time">${ item.endDate }</span>
								</div>
							</a>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="white-box" style="height: 240px; overflow: auto;">
				<h3 class="box-title">Đang thực hiện</h3>
				<div class="message-center">
					<c:forEach items="${ tasks }" var="item" varStatus="loop">
						<c:if test="${ item.statusId == 2 }">
							<a href="#">
								<div class="mail-contnet">
									<h5>${ item.name }</h5>
									<span class="mail-desc">${ item.jobName }</span> <span
										class="time">${ item.endDate }</span>
								</div>
							</a>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="white-box" style="height: 240px; overflow: auto;">
				<h3 class="box-title">Đã hoàn thành</h3>
				<div class="message-center">
					<c:forEach items="${ tasks }" var="item" varStatus="loop">
						<c:if test="${ item.statusId == 3 }">
							<a href="#">
								<div class="mail-contnet">
									<h5>${ item.name }</h5>
									<span class="mail-desc">${ item.jobName }</span> <span
										class="time">${ item.endDate }</span>
								</div>
							</a>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<!-- END DANH SÁCH CÔNG VIỆC -->
</div>