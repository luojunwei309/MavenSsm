<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CH">

<%@ include file="/WEB-INF/include-head.jsp" %>
<link rel="stylesheet" href="css/pagination.css" />
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript">

  $(function(){
	  initPageination();
  });
 
//生成页码的导航条函数
  function initPageination(){
  	// 获取总记录数
  	var totalRecord=${pageInfo.total};
  	// 声明一个JSON对象存储Pagination的属性
  	var propeties={
  		num_edge_entries: 3,								// 边缘页数
  		num_display_entries: 5,								// 主体页数
  		callback: pageSelectCallback,						// 指定用户点击“翻页”的按钮时跳转页面的回调函数
  		load_first_page: false,
  		current_page: ${requestScope.pageInfo.pageNum - 1},	// Pagination内部使用pageIndex来管理页码，pageIndex从0开始，pageNum从1开始，所以要减一
  		prev_text: "上一页",									// 上一页按钮上显示的文本
  		next_text: "下一页",									// 下一页按钮上显示的文本
  		items_per_page: ${requestScope.pageInfo.pageSize}	// 每页要显示的数据的数量
  	
  	};
  	// 生成页码单行条
  	$("#Pagination4").pagination(totalRecord,propeties);
  }
 
 // 页码单击函数
 function pageSelectCallback(pageIndex,JQuery){
 	var pageNum=pageIndex+1;
 	window.location.href="admin/PropertyGet.html?pageNum="+pageNum+"&keyword=${param.keyword}&cid="+${cid};
 return false;
 }
   
</script>
<body>

	<%@ include file="/WEB-INF/include-nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/include-sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<ol class="breadcrumb">
					<li><a href="admin/categoryGet.html">分类</a></li>
					<li class="active" >属性列表</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form action="admin/PropertyGet.html" method="post" class="form-inline" role="form" style="float: left;">
							<input type="hidden" name="cid" value="${cid }">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input name="keyword" class="form-control has-success" type="text"
										placeholder="请输入查询条件">
								</div>
							</div>
							<button type="submit" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<a id="adminProUser" href="admin/ToPropertyAdd.html?cid=${cid }" style="float: right;" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> 新增</a>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">ID</th>
										<th>属性名称</th>
										<th>编辑</th>
										<th>删除</th>
									</tr>
								</thead>
								
								<tbody class="table table-bordered" id="cate">
								<c:if test="${empty pageInfo.list }">
								<tr>
								   <td  colspan="4" style="font-size:30px;text-align:center;">没有数据</td>
								</tr>
								</c:if>
								<c:forEach items="${pageInfo.list }" var="pro">
								 <tr>
								    <td >${pro.id }</td>
								    <td>${pro.name }</td>
								    <td><a href="admin/ToPropertyUpdate.html?id=${pro.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
								    <td><a href="javascript:Del('你确定删除属性：【${pro.name }】','admin/propertyDel.html?id=${pro.id }&cid=${cid }')"><span class="glyphicon glyphicon-trash"></span></a></td>
								 </tr>
								
								 <script>
								   function Del(mess,url){
									   if(confirm(mess)){
										   location.href=url;
									   }
								   }
								 </script>
								 </c:forEach>
								</tbody>
								
								<tfoot>
									<tr>
										<td colspan="9" align="center">
											<div id="Pagination4" class="pagination"><!-- 这里显示分页 --></div>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>