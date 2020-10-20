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
	var totalRecord=${pagelist.total};
	// 声明一个JSON对象存储Pagination的属性
	var propeties={
		num_edge_entries: 3,								// 边缘页数
		num_display_entries: 5,								// 主体页数
		callback: pageSelectCallback,						// 指定用户点击“翻页”的按钮时跳转页面的回调函数
		load_first_page: false,
		current_page: ${requestScope.pagelist.pageNum - 1},	// Pagination内部使用pageIndex来管理页码，pageIndex从0开始，pageNum从1开始，所以要减一
		prev_text: "上一页",									// 上一页按钮上显示的文本
		next_text: "下一页",									// 下一页按钮上显示的文本
		items_per_page: ${requestScope.pagelist.pageSize}	// 每页要显示的数据的数量
	
	};
	// 生成页码单行条
	$("#Pagination1").pagination(totalRecord,propeties);
}

// 页码单击函数
function pageSelectCallback(pageIndex,JQuery){
	var pageNum=pageIndex+1;
	window.location.href="admin/get/category.html?pageNum="+pageNum+"&keyword=${param.keyword}";
return false;
}
</script>
<body>

	<%@ include file="/WEB-INF/include-nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/include-sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">

						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
								
									<tr>
										<th width="30">#</th>
										<th>姓名</th>

									</tr>
								</thead>
								
								<tbody class="table table-bordered" id="ljw1">
								<c:forEach items="${pagelist.list }" var="user" varStatus="m">
								 <tr>
								    <td>${m.count }</td>
								    <td>${user.name } </td>
								 </tr>
								</c:forEach>
								</tbody>
								
								<tfoot>
									<tr>
										<td colspan="9" align="center">
											<div id="Pagination1" class="pagination"><!-- 这里显示分页 --></div>
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