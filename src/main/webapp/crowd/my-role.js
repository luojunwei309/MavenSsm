//
//			$("#userconfirmModal").modal("show");
//			window.adminIdArra=[];
//			for (var i = 0; i < adminArray.length; i++) {
//				var admin=adminIdArra[i];
//				var adminId=admin.id;
//				$("#userNameDiv").append("Id:"+adminId);
//				window.adminIdArra.push(adminId);
//			}
//// 函数打开用户删除模态框
//function showConfirmModal(roleArray){
//	$("#userconfirmModal").modal("show");
//	$("#roleNameDiv").empty();
//
//}
//	

// 声明函数用来分配Auth的模态框中显示Auth的树形结构数据
function fileAuthTree(){
	var ajaxReturn=$.ajax({
		"url":"assign/get/all/auth.json",
		"type":"post",
		"dataType":"json",
		"async":false
	});
	
	if (ajaxReturn.status != 200) {
		layer.msg("请求出错:"+ajaxReturn.status+","+ajaxReturn.statusText);
	return;
	}
	
	// 获取数据
	var authList = ajaxReturn.responseJSON.data;
	// 设置ztree的json对象
	var setting={
			"data":{
				"simpleData":{
					"enable":true,
					"pIdKey":"categoryId"
				},
				"key":{
					"name":"title"
				}
			},
			"check":{
				"enable":true
	}
	};
	
	// 生成树
	$.fn.zTree.init($("#authTreeDemo"),setting,authList);
	
	// 调用zTree对象方法，把节点打开
	var zTreeObj=$.fn.zTree.getZTreeObj("authTreeDemo");
	zTreeObj.expandAll("true");
	
	// 查询已分配的Auth的id组成的List数组
	ajaxReturn=$.ajax({
		"url":"assign/get/assigned/auth/id/by/role/id.json",
		"type":"post",
		"data":{
			"roleId":window.roleId
		},
		"dataType":"json",
		"async":false
	});
	
	
	
	if (ajaxReturn.status != 200) {
		layer.msg("请求出错“响应码是："+ajaxReturn.status+";说明是:"+ajaxReturn.statusText);
	   return;
	}

	// 从响应结果获取
	var authIdArray=ajaxReturn.responseJSON.data;
	
	// 6.根据authArray把树形结构中对应的节点勾选上
	// 6.1先遍历数组
	for (var i = 0; i < authIdArray.length; i++) {
		var authId=authIdArray[i];
		
		// 6.2根据id查树形结构对应的节点
		var treeNode=zTreeObj.getNodeByParam("id",authId);
		// 6.3将treeNode设置为勾选
		 var checked=true; // true表示节点勾选
		 var checkTypeFlag=false; // false表示不联动
		zTreeObj.checkNode(treeNode,checked,checkTypeFlag);
	}
	
}
			

// 函数打开角色删除模态框
function showConfirmModal(roleArray){
	$("#confirmModal").modal("show");
	$("#roleNameDiv").empty();
	window.roleIdArray=[];
	for (var i = 0; i < roleArray.length; i++) {
		var role=roleArray[i];
		var roleName=role.roleName;
		var roleId=role.roleId;
		$("#roleNameDiv").append("ID:"+roleId+"---名字:"+roleName+"<br/>");
		
		// 调用数组对象push()方法存入新元素
		window.roleIdArray.push(roleId);
	}
}


// 分页函数
function generatePage(){
	var pageInfo=getPageInfoRemote();
	fillTableBody(pageInfo);
}

// ajax获取分页数据
function getPageInfoRemote(){
	var ajaxResult=$.ajax({
		"url":"role/get/page/info.json",
		"type":"post",
		"data":{
			 "pageNum":window.pageNum,
			 "pageSize":window.pageSize,
			 "keyword":window.keyword
		 },
		 "async":false,
		 "dataType":"json"
	});
	
   
   // 判断当前响应体状态是否为200
   var statueCode = ajaxResult.status;
   if(statueCode != 200){
	   layer.msg("失败！响应状态码为:"+statueCode+",信息："+ajaxResult.statusText);
   }
   
     // 成功
   var resultEntity = ajaxResult.responseJSON;

	 // 从resultEntity中获取result属性
	 var result = resultEntity.result;
	 
	 // 判断rsult是否成功（失败）
	 if(result == "FAILED"){
		 layer.msg(resultEntity.message);
		 return null;
	 }
	 
	 // 成功（获取pageInfo）
	 var pageInfo=resultEntity.data;
	 return pageInfo;
   
}

// 填充表格
function fillTableBody(pageInfo){
	//清除tbody中的旧内容
	$("#rolePageBody").empty();
	// 这里清空是为了让没有搜索结果时不显示页码导航条
	$("#Pagination").empty();
	
	if(pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length == 0){
		$("#rolePageBody").append("<tr><td colspan='4'>抱歉没有查到你所要的数据</td></tr>");
	    return;
	}
	
	// 使用pageInfo的list属性填充tbody
	for(var i = 0;i<pageInfo.list.length;i++){
		var role = pageInfo.list[i];
		
		var roleId = role.id;
		
		var roleName = role.name;
		
		
		var numberTd = "<td>"+(i+1)+"</td>";
		var checkboxTd = "<td><input id='"+roleId+"' class='itemBox' type='checkbox'></td>";
		var roleNameTd = "<td>"+roleName+"</td>";
		
		var checkBtn = "<button id='"+roleId+"' type='button' class='btn btn-success btn-xs checkBtn'><i class=' glyphicon glyphicon-check'></i></button>";
		
		// 通过button标签的id属性（别的属性其实也可以）把roleId值传递到button按钮的单击响应函数中，在单击响应函数中使用this.id
		var pencilBtn = "<button id='"+roleId+"' type='button' class='btn btn-primary btn-xs pencilBtn'><i class=' glyphicon glyphicon-pencil'></i></button>";
		
		// 通过button标签的id属性（别的属性其实也可以）把roleId值传递到button按钮的单击响应函数中，在单击响应函数中使用this.id
		var removeBtn = "<button id='"+roleId+"' type='button' class='btn btn-danger btn-xs removeBtn'><i class=' glyphicon glyphicon-remove'></i></button>";
			
		var buttonTd = "<td>"+checkBtn+" "+pencilBtn+" "+removeBtn+"</td>";
		
		var tr = "<tr>"+numberTd+checkboxTd+roleNameTd+buttonTd+"</tr>";
		
		$("#rolePageBody").append(tr);
		
	}
	generateNavigator(pageInfo);
}

//生成分页页码导航条
function generateNavigator(pageInfo) {
	
	// 获取总记录数
	var totalRecord = pageInfo.total;
	
	// 声明相关属性
	var properties = {
		"num_edge_entries": 3,
		"num_display_entries": 5,
		"callback": paginationCallBack,
		"items_per_page": pageInfo.pageSize,
		"current_page": pageInfo.pageNum-1,
		"prev_text": "上一页",
		"next_text": "下一页"
	}

	// 调用pagination()函数
	$("#Pagination").pagination(totalRecord,properties);
}

//翻页时的回调函数
function paginationCallBack(pageIndex, jQuery) {
	// 修改window对象的pageNum属性
	window.pageNum = pageIndex+1;
	
	// 调用分页函数
	generatePage();
	
	// 取消页码超链接的默认行为
	return false;
	
}