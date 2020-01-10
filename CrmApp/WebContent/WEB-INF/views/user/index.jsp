<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.myclass.util.UrlConstants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Danh sách thành viên</h4>
		</div>
		<c:if test="${ roleName eq 'ROLE_ADMIN' }">
		<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
			<a href="<c:url value="${ UrlConstants.URL_USER_ADD }" />" class="btn btn-sm btn-success">Thêm mới</a>
		</div>
		</c:if>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /row -->
	<div class="row">
		<div class="col-sm-12">
			<div class="white-box">
				<div class="table-responsive">
					<table class="table" id="example">
						<thead>
							<tr>
								<th>STT</th>
								<th>Ho Ten</th>
								<th>Email</th>
								<th>Quyen</th>
								<th>#</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ users }" var="item" varStatus="loop">
								<tr>
									<td>${ loop.index + 1 }</td>
									<td>${ item.fullname }</td>
									<td>${ item.email }</td>
									<td>${ item.description }</td>
									<td>
										<c:if test="${ roleName eq 'ROLE_ADMIN' }">
											<a  
												href="<c:url value="${ UrlConstants.URL_USER_EDIT }?id=${ item.id }" />" 
												class="btn btn-sm btn-primary">Sửa
											</a> 
											<a
												href="<c:url value="${ UrlConstants.URL_USER_DELETE }?id=${ item.id }" />" 
												class="btn btn-sm btn-danger">Xóa
											</a>
										</c:if>
										<a
											href="<c:url value="${ UrlConstants.URL_USER_DETAILS }?id=${ item.id }" />" 
											class="btn btn-sm btn-info">Xem
										</a>
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