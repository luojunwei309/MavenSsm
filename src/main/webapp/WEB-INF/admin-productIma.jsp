<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CH">

<%@ include file="/WEB-INF/include-head.jsp"%>
<link rel="stylesheet" href="css/pagination.css" />
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<link href="css/back/style.css" rel="stylesheet">
<script type="text/javascript">
	$(function() {
	});
</script>
<body>

	<%@ include file="/WEB-INF/include-nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/include-sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"
				style="float: left;">
				<ol class="breadcrumb">
					<li><a href="admin/categoryGet.html">分类</a></li>
					<li class="active">图片</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 图片列表
						</h3>
					</div>
					<div class="workingArea">

						<table class="addPictureTable" align="center">
							<tr>
								<td class="addPictureTableTD">
									<div>
										<div class="panel panel-warning addPictureDiv">
											<div class="panel-heading">
												新增产品<b class="text-primary"> 单个 </b>图片
											</div>
											<div class="panel-body">
												<form method="post" class="addFormSingle"
													action="admin/productImage/Add.html"
													enctype="multipart/form-data">
													<table class="addTable">
														<tr>
															<td>请选择本地图片 尺寸400X400 为佳</td>
														</tr>
														<tr>
															<td>
															<input id="filepathSingle" type="file"
																name="image" /></td>
														</tr>
														<tr class="submitTR">
															<td align="center">
															<input type="hidden" name="pid" value="${pid}" />
																<button type="submit" class="btn btn-success">提
																	交</button></td>
														</tr>
													</table>
												</form>
											</div>
										</div>
										<table
											class="table table-striped table-bordered table-hover  table-condensed">
											<thead>
											
												<tr class="success">
													<th>ID</th>
													<th>产品单个图片缩略图</th>
													<th>删除</th>
												</tr>
										    
											</thead>
											<tbody>
                                                <c:forEach items="${imas }" var="ima">
												<tr>
													<td>${ima.id }</td>
													<td><a title="点击查看原图" href="${ima.type }"><img
															height="50px" src="${ima.type }"></a></td>
													<td>
													<a deleteLink="true" href="admin/productImage/delete.html?id=${ima.id }&pid=${pid}">
													<span class="glyphicon glyphicon-trash"></span></a></td>

												</tr>
                                               </c:forEach>

											</tbody>
										</table>

									</div>
								</td>
								<td class="addPictureTableTD">
									<div>

										<div class="panel panel-warning addPictureDiv">
											<div class="panel-heading">
												新增产品<b class="text-primary"> 详情 </b>图片
											</div>
											<div class="panel-body">
												<form method="post" class="addFormDetail"
													action="admin/productImages/add.html"
													enctype="multipart/form-data">
													<table class="addTable">
														<tr>
															<td>请选择本地图片 宽度790 为佳</td>
														</tr>
														<tr>
															<td><input id="filepathDetail" type="file"
																name="images" /></td>
														</tr>
														<tr class="submitTR">
															<td align="center">
															<input type="hidden" name="pid" value="${pid }" />
																<button type="submit" class="btn btn-success">提
																	交</button></td>
														</tr>
													</table>
												</form>
											</div>
										</div>
										<table
											class="table table-striped table-bordered table-hover  table-condensed">
											<thead>
												<tr class="success">
													<th>ID</th>
													<th>产品详情图片缩略图</th>
													<th>删除</th>
												</tr>
											</thead>
											<tbody>
											  <c:forEach items="${images }" var="images">
												<tr>
													<td>${images.id}</td>
													<td><a title="点击查看原图" href="${images.type }"><img
															height="50px" src="${images.type }"></a></td>
													<td><a deleteLink="true"
														href="javascript:imaDel('你确定删除图片id：${images.id }','admin/productImages/delete.html?id=${images.id }&pid=${pid}')"><span
															class="glyphicon glyphicon-trash"></span></a></td>

												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							
							<script>
							 function imaDel(msg,url){
								 if(confirm(msg)){
									 location.href=url;
								 }
							 }
							</script>
							
						</table>





					</div>




				</div>
			</div>
		</div>

	</div>
</body>
</html>