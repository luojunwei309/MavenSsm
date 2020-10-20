<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CH">
<%@include file="include-head.jsp"%>
<script type="text/javascript" charset="UTF-8" src="crowd/my-role.js" ></script>
<link rel="stylesheet" href="ztree/zTreeStyle.css" />
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="css/pagination.css" />
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript">
  
  $(function(){
	  // 1.分页数据
	  	window.keyword="";
	  	window.pageNum=1;
	  	window.pageSize=5;
	 
	  	// 2.调用函数，显示分页
	  	generatePage();
	  	
	  	// 查询
	  	$("#searchBtn").click(function(){
	  		window.keyword=$("#keywordInput").val();
	  		generatePage();
	  	});
	  	
	  	// 打开新增模态框
	  	$("#showAddModalBtn").click(function(){
	  		$("#addModal").modal("show");
	  	});
	  	
	  	
	  	// 给添加角色添加单击函数
	  	$("#saveRoleBtn").click(function(){
	  		var roleName=$("#addModal [name=roleName]").val();
	  		$.ajax({
	  			"url":"role.json",
	  			"type":"post",
	  			"data":{
	  				"name":roleName
	  			},
	  			"dataType":"json",
	  			"success":function(response){
	  				var result=response.result;
	  				if(result=="SUCCESS"){
	  					layer.msg("保存成功");
	  					window.pageNum=999999;
	  					generatePage();
	  				}
	  				if(result=="FAILED"){
	  					layer.msg("操作失败！"+response.message);
	  					
	  				}
	  			},
	  			"error":function(response){
	  				layer.msg(response.status+""+response.statusText);
	  			}
	  		});
	  		$("#addModal").modal("hide");
	  		$("#addModal [name=roleName]").val("");
	  	});
	  	
	  	// 打开修改模态框
	  	$("#rolePageBody").on("click",".pencilBtn",function(){
	  		$("#editMOdal").modal("show");
	  		var roleName = $(this).parent().prev().text();
	  		window.roleId = this.id;
	  		$("#editMOdal [name=roleName]").val(roleName);
	  	});
	  	
	    // 给更新模态框中的更新按钮绑定单击函数
	    $("#updateRoleBtn").click(function(){
	    	var roleName=$("#editMOdal [name=roleName]").val();
	    	$.ajax({
	    		"url":"role/update.json",
	    		"type":"post",
	    		"data":{
	    			"id":window.roleId,
    				"name":roleName
	    		},
	    		"dataType":"json",
	    		"success":function(response){
	    			var result=response.result;
	    			if (result=="SUCCESS") {
						layer.msg("修改成功");
						generatePage();
					}
	    			if (result=="FAILED") {
						layer.msg("修改失败。。"+response.message);
					}
	    		},
	    		"error":function(response){
	    			layer.msg(response.status+""+response.statusText);
	    		}
	    	});
	    	$("#editMOdal").modal("hide");
	    });
	    
		// var r=[{roleId:5,roleName:"aaa"},{roleId:6,roleName:"111"}];
    	// showConfirmModal(r);
    	
	    // 点击确认模态框中的确认按钮进行删除
    	$("#removeRoleBtn").click(function(){
    		// 从全局变量范围获取roleIdArray,转换为JSON字符串
    		var requestBody=JSON.stringify(window.roleIdArray);
    		
    		$.ajax({
    			"url":"role/delete/by/role/id/array.json",
    			"type":"post",
    			"data":requestBody,
    			"contentType":"application/json;chartset=UTF-8",
    			"dataType":"json",
    			"success":function(response){
    				var result=response.result;
    				if (result == "SUCCESS") {
						layer.msg("操作成功");
						generatePage();
					}
    				if (result == "FAILED") {
    					layer.msg("操作失败！"+response.message);
					}
    			},
    			"error":function(response){
    				layer.msg(response.status+" "+response.statusText);
    			}
    			
    		});
    		// 关闭模态框
    		$("#confirmModal").modal('hide');
    		
    	});
    	
    	// 9.单条删除
    	$("#rolePageBody").on("click",".removeBtn",function(){
    		
    		// 获取名称
    		var roleName=$(this).parent().prev().text();
    		
    		// 准备roleArray数据
    		var roleArray=[{
    			roleId:this.id,
    			roleName:roleName
    		}];
    		
			// 调用函数打开模态框
			showConfirmModal(roleArray);
			
		});
    	
    	// 11.全选、全不选反向操作
    	$("#sBox").click(function(){
    		var cur = this.checked;
    		$(".itemBox").prop("checked",cur);
    	});
    	
    	$("#rolePageBody").on("click",".itemBox",function(){
    		// 11.1获取当前已经选择.itemBox的数量
    		var checkedBoxCount=$(".itemBox:checked").length;
    		
    		// 获取全部.itemBox数量
    		var totalBoxCount=$(".itemBox").length;
    		$("#sBox").porp("checked",checkedBoxCount==totalBoxCount);
    	});
    	
    	// 批量删除
    	$("#batchRemoveBtn").click(function(){
    		var roleArray=[];
    		$(".itemBox:checked").each(function(){
    			var roleId=this.id;
    			var roleName=$(this).parent().next().next();
    			
    			roleArray.push({
    				"roleId":roleId,
    				"roleName":roleName
    			});
    		});
    		if(roleArray.length==0){
    			layer.msg("什么都没选中");
				return;
    		}
    		showConfirmModal(roleArray);
    	}); 
    	
    	 
    	// 给分配权限绑定函数
    	$("#rolePageBody").on("click",".checkBtn",function(){
    		$("#assignModal").modal("show");
    		window.roleId=this.id;
    		
    		// 在模态框中装载Auth的树形结构数据
    		fileAuthTree();
    	});
    	
    	// 分配权限
    	$("#assignBtn").click(function(){
    		// 收集树种被勾选的节点Id
    		var authIdArray=[];
    		// 获取树对象
    		var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
    		
    		// 获取被勾选的节点
    		var checkedNodes = zTreeObj.getCheckedNodes();
    		
    		for (var i = 0; i < checkedNodes.length; i++) {
				var checkedNode=checkedNodes[i];
				var authId=checkedNode.id;
				authIdArray.push(authId);
			}
    		
    		var requestBody={
    				"authIdArray":authIdArray,
    				"roleId":[window.roleId]
    		};
    		requestBody = JSON.stringify(requestBody);
    		
    		// 发送ajax请求
    		$.ajax({
    			"url":"assign/do/role/assign/auth.json",
    			"type":"post",
    			"data":requestBody,
    			"dataType":"json",
    			"contentType":"application/json;charset=UTF-8",
    			"success":function(response){
    				var result=response.result;
    				if (result =="SUCCESS") {
						layer.msg("操作成功");
					}
    				if (result == "FAILED") {
    					layer.msg("操作失败"+response.meaasge);
					}
    			},
				"error":function(response){
    				layer.msg(response.status+" "+response.statusText);
    			}
    		});
    		$("#assignModal").modal("hide");
    	});
  });
  
</script>
<body>
	<%@include file="include-nav.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<%@include file="include-sidebar.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input id="keywordInput" class="form-control has-success" type="text"
										placeholder="请输入查询条件">
								</div>
							</div>
							<button id="searchBtn" type="button" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button id="batchRemoveBtn" type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<button type="button" id="showAddModalBtn"  class="btn btn-primary"
							style="float: right;">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input id="sBox" type="checkbox"></th>
										<th>名称</th>
										<th width="100" >操作</th>
									</tr>
								</thead>
								<tbody id="rolePageBody">
									
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
	</div>	<div class="navbar-fixed-bottom" style="text-align: center;width: 100%;height: 30px;background: black;color: white;line-height: 30px;">
	  <a  href="http://beian.miit.gov.cn"> 粤ICP备2020097285号-1</a>
     </div>
<%@ include file="/WEB-INF/modal-role-add.jsp" %>
<%@ include file="/WEB-INF/modal-role-edit.jsp" %>
<%@ include file="/WEB-INF/modal-role-confirm.jsp" %>
<%@ include file="/WEB-INF/modal-role-assign-auth.jsp" %>
</body>
</html>