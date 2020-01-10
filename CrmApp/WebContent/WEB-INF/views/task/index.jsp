<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.myclass.util.UrlConstants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Danh sách công việc</h4>
		</div>
		<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
			<a href="<c:url value="${ UrlConstants.URL_TASK_ADD }" />" 
				class="btn btn-sm btn-success">Thêm mới</a>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /row -->
	<div class="row">
		<div class="col-md-6">
		
		 </div>
		 <div class="col-md-6">
		
		 </div>
		<div class="col-sm-12">
			<div class="white-box">
				<div class="table-responsive">
					<table class="table" id="example">
						<thead>
							<tr>
								<th>STT</th>
								<th>Tên Công Việc</th>
								<th>Ngày bắt đầu</th>
								<th>Ngày kết thúc</th>
								<th>Mã nhân viên phụ trách</th>
								<th>Mã dự án</th>
								<th>Mã trạng thái</th>
								<th>Hành Động</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${ tasks }" var="item" varStatus="loop">
							<tr>
								<td>${ item.id }</td>
								<td>${ item.name }</td>
								<td>${ item.startDate }</td>
								<td>${ item.endDate }</td>
								<td>${ item.userId }</td>
								<td>${ item.jobId }</td>
								<td>${ item.statusId }</td>
								<td>
									<a 
										href="<c:url value="${ UrlConstants.URL_TASK_EDIT }?id=${ item.id }" />" 
										class="btn btn-sm btn-primary">Sửa
									</a>
									<c:if test="${ !(roleName eq 'ROLE_MEMBER') }">
										<a 
											href="<c:url value="${ UrlConstants.URL_TASK_DELETE }?id=${ item.id }" />" 
											class="btn btn-sm btn-danger">Xóa
										</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- /.row -->
</div>