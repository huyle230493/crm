<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<head>
<title>Trang chủ</title>
</head>

<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Dashboard</h4>
		</div>
		<ol class="breadcrumb">
			<li><a href="#">Dashboard</a></li>
		</ol>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- row -->
<div class="row">
	<!--col -->
	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
		<div class="white-box">
			<div class="col-in row">
				<div class="col-md-6 col-sm-6 col-xs-6">
					<i data-icon="E" class="linea-icon linea-basic"></i>
					<h5 class="text-muted vb">NHÂN VIÊN</h5>
				</div>
				<div class="col-md-6 col-sm-6 col-xs-6">
					<h3 class="counter text-right m-t-15 text-danger">${ userCount }</h3>
				</div>
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="progress">
						<div class="progress-bar progress-bar-danger" role="progressbar"
							aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
							style="width: 40%">
							<span class="sr-only">40% Complete (success)</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.col -->
	<!--col -->
	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
		<div class="white-box">
			<div class="col-in row">
				<div class="col-md-6 col-sm-6 col-xs-6">
					<i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
					<h5 class="text-muted vb">DỰ ÁN</h5>
				</div>
				<div class="col-md-6 col-sm-6 col-xs-6">
					<h3 class="counter text-right m-t-15 text-megna">${ jobCount }</h3>
				</div>
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="progress">
						<div class="progress-bar progress-bar-megna" role="progressbar"
							aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
							style="width: 40%">
							<span class="sr-only">90% Complete (success)</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.col -->
	<!--col -->
	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
		<div class="white-box">
			<div class="col-in row">
				<div class="col-md-6 col-sm-6 col-xs-6">
					<i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>
					<h5 class="text-muted vb">CÔNG VIỆC</h5>
				</div>
				<div class="col-md-6 col-sm-6 col-xs-6">
					<h3 class="counter text-right m-t-15 text-primary">${ taskCount }</h3>
				</div>
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="progress">
						<div class="progress-bar progress-bar-primary" role="progressbar"
							aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
							style="width: 40%">
							<span class="sr-only">40% Complete (success)</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->