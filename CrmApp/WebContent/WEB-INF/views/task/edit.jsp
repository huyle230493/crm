<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.myclass.util.UrlConstants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Cập nhật công việc</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
				<form action="<c:url value="${ UrlConstants.URL_TASK_EDIT }" />" 
					method="post" class="form-horizontal form-material">
					
					<input type="hidden" name="id" value="${ task.id }" />
					
					<div class="form-group">
						<label class="col-md-12">Tên công việc</label>
						<div class="col-md-12">
							<input type="text" placeholder="Tên quyền" name="name" value="${ task.name }"
								class="form-control form-control-line" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Ngày bắt đầu</label>
						<div class="col-md-12">
							<input type="text" placeholder="Ngày bắt đầu" name="start-date" value="${ task.startDate }"
								class="form-control form-control-line" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Ngày kết thúc</label>
						<div class="col-md-12">
							<input type="text" placeholder="Ngày kết thúc" name="end-date" value="${ task.endDate }"
								class="form-control form-control-line" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Nhân viên phụ trách</label>
						<div class="col-sm-12">
							<c:choose>
								<c:when test="${ roleName eq 'ROLE_MEMBER' }">
									<input type="text" placeholder="Nhân viên phụ trách" name="user-id" value="${ task.userId }" readonly
									class="form-control form-control-line" />
								</c:when>
								<c:otherwise>
									<select name="user-id" class="form-control form-control-line">
										<c:forEach items="${ users }" var="item">
											<option value="${ item.id }" ${ task.userId == item.id ? 'selected' : '' }>${ item.fullname }</option>
										</c:forEach>
									</select>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Thuộc dự án</label>
						<div class="col-sm-12">
							<c:choose>
								<c:when test="${ roleName eq 'ROLE_MEMBER' }">
									<input type="text" placeholder="Mã dự án" name="job-id" value="${ task.jobId }" readonly
									class="form-control form-control-line" />
								</c:when>
								<c:otherwise>
									<select name="job-id" class="form-control form-control-line">
										<c:forEach items="${ jobs }" var="item">
											<option value="${ item.id }" ${ task.jobId == item.id ? 'selected' : '' }>${ item.name }</option>
										</c:forEach>
									</select>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Trạng thái công việc</label>
						<div class="col-sm-12">
							<select name="status-id"
								class="form-control form-control-line">
								<c:forEach items="${ status }" var="item">
									<option value="${ item.id }" ${ task.statusId == item.id ? 'selected' : '' }>${ item.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Edit Task</button>
							<a href="<c:url value="${ UrlConstants.URL_TASK_LIST }" />" 
								class="btn btn-primary">Quay lại</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-md-2 col-12"></div>
	</div>
	<!-- /.row -->
</div>