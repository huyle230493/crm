<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.myclass.util.UrlConstants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Thêm mới công việc</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
				<p>${ message }</p>
				<form action="<c:url value="${ UrlConstants.URL_TASK_ADD }" />" method="post" class="form-horizontal form-material">
					<div class="form-group">
						<label class="col-md-12">Tên công việc</label>
						<div class="col-md-12">
							<input type="text" placeholder="Tên công việc" name="name"
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
						<label class="col-sm-12">Nhân viên phụ trách</label>
						<div class="col-sm-12">
							<select name="user-id" class="form-control form-control-line">
								<c:forEach items="${ users }" var="item">
									<option value="${ item.id }">${ item.fullname }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Thuộc dự án</label>
						<div class="col-sm-12">
							<select name="job-id" class="form-control form-control-line">
								<c:forEach items="${ jobs }" var="item">
									<option value="${ item.id }">${ item.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Trạng thái</label>
						<div class="col-sm-12">
							<select name="status-id" class="form-control form-control-line">
								<c:forEach items="${ status }" var="item">
									<option value="${ item.id }">${ item.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Add Job</button>
							<a href="<c:url value="${ UrlConstants.URL_TASK_LIST }" />" class="btn btn-primary">Quay lại</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-md-2 col-12"></div>
	</div>
	<!-- /.row -->
</div>