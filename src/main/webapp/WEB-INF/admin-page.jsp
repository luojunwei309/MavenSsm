<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp"%>
<link rel="stylesheet" href="css/pagination.css" />
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript">
	$(function(){
		initPageination();
		
		$("#useradd").click(function(){
			$("#useraddModal").modal("show");
		});
		$("#saveUserBtn").click(function(){
			var loginAcct=$.trim($("#useraddModal [name=loginAcct]").val());
			var userPswd=$.trim($("#useraddModal [name=userPswd]").val());
			var userName=$.trim($("#useraddModal [name=userName]").val());
			var email=$.trim($("#useraddModal [name=email]").val());
			$.ajax({
				"url":"admin/save.json",
				"type":"post",
				"data":$('#userForm').serialize(),
					/* "loginAcct":loginAcct,
					"userPswd":userPswd,
					"userName":userName,
					"email":email */
				"dataType":"json",
				//"contentType":"application/json;charset=UTF-8",
				"success":function(response){
					var result=response.result;
					if (result == "SUCCESS") {
						layer.msg("操作成功");
						initPageination();
					}
					if (result=="FAILED") {
						layer.msg("操作失败！"+response.message);
					}
				},
				"error":function(response){
					layer.msg(response.status+""+response.statusText);
				}
			});
			
			$("#useraddModal").modal("hide");
		
		});
		
		$("#ck").click(function(){
			var cur=this.checked;
			$(".ljw").prop("checked",cur);
		});
		

		
		
	});
	
	// 生成页码的导航条函数
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
		$("#Pagination").pagination(totalRecord,propeties);
	}
	
	// 页码单击函数
	function pageSelectCallback(pageIndex,JQuery){
		var pageNum=pageIndex+1;
		window.location.href="admin/get/page.html?pageNum="+pageNum+"&keyword=${param.keyword}";
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
						<form action="admin/get/page.html" method="post" class="form-inline" role="form" style="float: left;">
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
						<button id="aad" type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<!-- 
						旧代码
						<button type="button" class="btn btn-primary"
							style="float: right;" onclick="window.location.href='add.html'">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button> -->
						<!-- 新代码 -->
						<a id="useradd" style="float: right;" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> 新增</a>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input id="ck" type="checkbox"></th>
										<th>账号</th>
										<th>名称</th>
										<th>邮箱地址</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody id="ljw1">
									<c:if test="${empty requestScope.pageInfo.list }">
										<tr>
											<td colspan="6" align="center">抱歉！没有查询到您要的数据！</td>
										</tr>
									</c:if>
									<c:if test="${!empty requestScope.pageInfo.list }">
										<c:forEach items="${requestScope.pageInfo.list }" var="admin" varStatus="myStatus">
											<tr>
												<td>${myStatus.count }</td>
												<td><input id="${admin.id}" class="ljw" type="checkbox"></td>
												<td>${admin.loginAcct }</td>
												<td>${admin.userName }</td>
												<td>${admin.email }</td>
												<td>
												
												<security:authorize access="hasRole('经理操作者') or hasAuthority('user:edit')">
													<a href="assign/to/assign/role/page.html?adminId=${admin.id }&pageNum=${pageInfo.pageNum}&keyword=${param.keyword}" class="btn btn-success btn-xs">
														<i class=" glyphicon glyphicon-check"></i>
													</a>
											   </security:authorize>
													<!-- 旧代码 -->
													<!-- <button type="button" class="btn btn-primary btn-xs">
														<i class=" glyphicon glyphicon-pencil"></i>
													</button> -->
													<!-- 新代码 -->
													<a href="admin/to/edit/page.html?adminId=${admin.id }&pageNum=${pageInfo.pageNum}&keyword=${param.keyword}" class="btn btn-primary btn-xs">
														<i class=" glyphicon glyphicon-pencil"></i>
													</a>
													<a href="javascript:Delete('你确定删除${admin.userName }','admin/remove/${admin.id }/${pageInfo.pageNum }/${param.keyword }.html')" class="btn btn-danger btn-xs">
														<i class=" glyphicon glyphicon-remove"></i>
													</a>
												</td>
											</tr>
										</c:forEach>
									</c:if>
									<script>
									function Delete(mess, url) {
		                        		if(confirm(mess)) {
		                        			location.href=url;
		                        			
		                        		}
		                        	}
									// ALTER TABLE `t_admin` DROP INDEX `login_acct` , ADD UNIQUE INDEX (`login_acct`)
									</script>
									
								</tbody>
								<tfoot>
									<tr>
										<td colspan="6" align="center">
											<div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
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
	
	<%@ include file="/WEB-INF/modal-user-add.jsp" %>
	<%@ include file="/WEB-INF/modal-user-confirm.jsp" %>
</body>
</html>