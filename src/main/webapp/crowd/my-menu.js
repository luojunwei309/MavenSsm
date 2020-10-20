// 生成树形结构的函数
function generateTree(){
	$.ajax({
		"url":"menu/get/whole/tree.json",
		"type":"post",
		"dataType":"json",
		"success":function(response){
			var result=response.result;
			if (result=="SUCCESS") {
				var zNodes=response.data;
				
				// JSON对象储存ztree
				var seeting={
						"view":{
							"addDivDom":myAddDiyDom,
							"addHoveDom":myaddHoverDom,
							"removeHoverDom":myremoveHoverDom
						},
						"data":{
							"key":{
								"url":"maomisss"
							}
						}
				};
				
				// 初始化树形结构
//				var a=JSON.stringify(zNodes);
//				$("#treeDemo").text(a);
				 $.fn.zTree.init($("#treeDemo"),seeting,zNodes);
			}
			if (result=="FAILED") {
				layer.msg(response.message);
			}
		},
		"error":function(response){
			layer.msg(response.status+"--"+response.statusText);
		}
	});
}

// 鼠标移除节点，删除按钮组
function myremoveHoverDom(treeId,TreeNode){
	var btnGroupId=	treeNode.tId+"_btnGrp";
	
	$("#"+btnGroupId).remove();
	
}

//在鼠标移入节点，添加按钮组
function myaddHoverDom(treeId,treeNode){
	var btnGroupId = treeId.tId+"_btnGrp";
	if ($("#"+btnGroupId).lemgth > 0) {
		return;
	}
	
    var addBtn = "<a id='"+treeNode.id+"' class='addBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    var removeBtn = "<a id='"+treeNode.id+"' class='removeBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title=' 删 除 节 点 '>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
    var editBtn = "<a id='"+treeNode.id+"' class='editBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title=' 修 改 节 点 '>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";
	
    var btnHTML="";
    
    var level=treeNode.level;
    if(level == 0){
    	btnHtml=addBtn;
    }
    if(level == 1){
    	btnHTML=addBtn+" "+editBtn;
    	
    	// 获取当前节点的子节点
    	var length=treeNode.children.length;
    	if(length == 0){
    		btnHTML=addBtn+" "+editBtn+" "+removeBtn;
    	}
    }
    if (level == 2) {
    	btnHTML=editBtn+" "+removeBtn;
	}
	// 找到 按钮组的超链接
	var anchorId=treeNode.tId+"_a";
	
	
	// 执行在超链接后面附加span元素的操作
	$("#"+anchorId).after("<span id='"+btnGroupId+"'>"+btnHTML+"</span>");
}

//修改默认的图标
function myAddDiyDom(treeId,treeNode){
	// treeId是ul标签的id
	console.log("treeId:"+treeId);
	// treeNode当前树形节点的全部数据，包括从后端查询得到的Menu对象的全部属性
	console.log(treeNode);
	
	// zTree生成id的规则（例：treeDemo_2_ico）
	// 解析：ul标签的id_当前节点的序号_功能（treeNode的tId属性加_ico）
	// 根据id的生成规则拼接成span的标签id
	var spanId=treeNode.tId+"_ico";
	
	// 根据控制图标的span标签的id找到这个span
	// 删除旧的class
	// 添加新的class
	$("#"+spanId).removeClass().addClass(treeNode.icon); 
	
}