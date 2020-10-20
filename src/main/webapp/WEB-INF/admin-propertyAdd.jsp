<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CH">
<%@include file="include-head.jsp"%>
<body>
	<%@include file="include-nav.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<%@include file="include-sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="admin/categoryGet.html">分类</a></li>
					<li><a href="admin/PropertyGet.html?cid=${cid }">属性列表</a></li>
					<li class="active">添加属性名</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						添加属性
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						<form action="admin/PropertyAdd.html" method="post" role="form">
						   <input type="hidden" name="cid" value="${cid }"/>
							<div class="form-group">
								<label for="exampleInputPassword1">属性名</label> <input
									name="name" type="text" class="form-control" id="exampleInputPassword1"
									placeholder="请输入属性名">
							</div>
							<button type="submit" class="btn btn-success">
								<i class="glyphicon glyphicon-plus"></i> 新增
							</button>
							<button type="reset" class="btn btn-danger">
								<i class="glyphicon glyphicon-refresh"></i> 重置
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>