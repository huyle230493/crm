<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.myclass.util.UrlConstants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Thêm mới dự án</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
				<p>${ message }</p>
				<form action="<c:url value="${ UrlConstants.URL_JOB_ADD }" />" method="post" class="form-horizontal form-material">
					<div class="form-group">
						<label class="col-md-12">Tên dự án</label>
						<div class="col-md-12">
							<input type="text" placeholder="Tên dự án" name="name"
								class="form-control form-control-line" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Ngày bắt đầu</label>
						<div class="col-md-12">
							<input type="text" placeholder="yyyy-MM-dd" name="start-date"
								class="form-control form-control-line" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Ngày kết thúc</label>
						<div class="col-md-12">
							<input type="text" placeholder="yyyy-MM-dd" name="end-date"
								class="form-control form-control-line" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Mã nhân viên phụ trách</label>
						<div class="col-md-12">
							<c:choose>
								<c:when test="${ roleName eq 'ROLE_MANAGER' }">
									<input type="text" placeholder="Mã nhân viên phụ trách" name="user-id" value="${ USER_LOGIN.id }" readonly
									class="form-control form-control-line" />
								</c:when>
								<c:otherwise>
									<select name="user-id" class="form-control form-control-line">
										<c:forEach items="${ users }" var="item">
											<option value="${ item.id }">${ item.fullname }</option>
										</c:forEach>
									</select>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Add Job</button>
							<a href="<c:url value="${ UrlConstants.URL_JOB_LIST }" />" class="btn btn-primary">Quay lại</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-md-2 col-12"></div>
	</div>
	<!-- /.row -->
</div>