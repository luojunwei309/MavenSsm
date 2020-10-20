<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CH">
<%@ include file="/WEB-INF/include-head.jsp" %>
<script type="text/javascript">
  $(function(){
	  $("#adminAddUser").click(function(){
		  window.location.href="admin-cateforyAdd.html";
	  });
	  
		// 点击上传图片按钮打开文件选择框
		$("#uploadBtn").click(function(){
			$("[name=returnPicture]").click();
		});
		
		// 在文件上传框的值改变事件响应函数中预览并上传图片
		$("[name=returnPicture]").change(function(event){
			
			var file = event.target.files[0];
			
			var url = window.url || window.webkitURL;
			
			var path = url.createObjectURL(file);
			
			$(this).next().next().next().next().attr("src",path).show();
			
			// 将上传的文件封装到FormData对象中
			var formData = new FormData();
			
			formData.append("returnPicture", file);
		
		});
  });
  



</script>
<body>

	<%@ include file="/WEB-INF/include-nav.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="admin/to/main/page.html">首页</a></li>
					<li><a href="admin/get/page.html">数据列表</a></li>
					<li class="active">新增</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						添加分类

					</div>
					<div class="panel-body">
						<form id="f1"  action="admin/categoryUpdate.html" method="post"  enctype="multipart/form-data">
							<input type="hidden" name="id" value="${category.id }">
							<div class="form-group">
								<label for="exampleInputPassword1">属性名字</label> <input
									name="name" value="${category.name }" type="text" class="form-control" id="exampleInputPassword1"
									placeholder="请输入属性名">
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">说明图片</label><br><br>
								<div class="col-sm-10">
									<input type="file" name="returnPicture" style="display: none;" />
									<button type="button" id="uploadBtn"
										class="btn btn-primary btn-lg active">上传图片</button>
									<label class="control-label">支持jpg、jpeg、png、gif格式，大小不超过2M，建议尺寸：760*510px选择文件</label>
									<br/>
									<img style="display: none" />
								</div>
							</div><br><br><br>
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
				<div class="navbar-fixed-bottom" style="text-align: center;width: 100%;height: 30px;background: black;color: white;line-height: 30px;">
	  <a  href="http://beian.miit.gov.cn"> 粤ICP备2020097285号-1</a>
     </div>
</body>
</html>