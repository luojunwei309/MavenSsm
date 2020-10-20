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
	  $("#adminAddUser").click(function(){
		  window.location.href="to/categoryAdd.html";
	  });
	  
	  $("#sBox1").click(function(){
  		
  		// 10.1获取当前选项框自身的状态
  		var cur=this.checked;
  		
  		// 10.2用当前多选框设置其他的选框
  		$(".it").prop("checked",cur);  // attr()函数是一次性的
  	});
	  
	  $("#cate").on("click",".it",function(){
		  var checkedBoxCount =  $(".it:checked").length;
		  var total = $(".it:checked").length;
		  $("#sBox1").prop("checked",checkedBoxCount == total);
	  });
	  
	  $("#cateDel").click(function(){
		  var cateArray=[];
		  var ccid=[];
		  $(".it:checked").each(function(){
			  var id=this.id;
			  cateArray.push({
				  "id":id
			  });
		  });
		 
		  
		  if(cateArray.length == 0){
			  layer.msg("什么都没选中");
			  return;
		  }
		  for (var i = 0; i < cateArray.length; i++) {
			var cate = cateArray[i];
			var cid = cate.id;
    		
    		ccid.push(cid);
		}
		  
		  window.location.href="admin/cateDelAll.html?id="+ccid;
		  
	  });

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
  	$("#Pagination3").pagination(totalRecord,propeties);
  }

  // 页码单击函数
  function pageSelectCallback(pageIndex,JQuery){
  	var pageNum=pageIndex+1;
  	window.location.href="admin/categoryGet.html?pageNum="+pageNum+"&keyword=${param.keyword}";
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
					<li class="active">分类</li>

				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form action="admin/categoryGet.html" method="post" class="form-inline" role="form" style="float: left;">
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
						<button id="cateDel" type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						
						<a id="adminAddUser" style="float: right;" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> 新增</a>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input id="sBox1" type="checkbox"></th>
										<th width="300px">图片</th>
										<th>分类名称</th>
										<th>属性管理</th>
										<th>产品管理</th>
										<th>编辑</th>
										<th>操作</th>
									</tr>
								</thead>
								
								<tbody class="table table-bordered" id="cate">
								<c:forEach items="${pageInfo.list }" var="cate">
								 <tr>
								    <td>${cate.id }</td>
								    <td><input id="${cate.id }" type="checkbox" class="it" /> </td>
								    <td><img width="200px" height="50px" src="${cate.pictname }"> </td>
								    <td>${cate.name }</td>
								    <td><a href="admin/PropertyGet.html?cid=${cate.id }"><span class="glyphicon glyphicon-th-list"></span></a></td>
								    <td><a href="admin/ProductGet.html?id=${cate.id }"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>
								    <td><a href="admin/categoryToUpdate.html?id=${cate.id }"><span class="glyphicon glyphicon-edit"></span></a></td>
								    <td><a href="javascript:Del('你确定删除分类【${cate.name }】','admin/categoryDel.html?id=${cate.id }')"><span class="glyphicon glyphicon-trash"></span></a></td>
								 </tr>
								
								 <script>
								   function Del(mess,url){
									   if (confirm(mess)) {
										location.href=url;
									}
								   }
								 </script>
								 
								</c:forEach>
								</tbody>
								
								<tfoot>
									<tr>
										<td colspan="9" align="center">
											<div id="Pagination3" class="pagination"><!-- 这里显示分页 --></div>
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
		<div class="navbar-fixed-bottom" style="text-align: center;width: 100%;height: 30px;background: black;color: white;line-height: 30px;">
	  <a  href="http://beian.miit.gov.cn"> 粤ICP备2020097285号-1</a>
     </div>
</body>
</html>