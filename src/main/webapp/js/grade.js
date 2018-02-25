$(function(){

	obj = {
			editRow:undefined,
			//添加一行
			add:function(){
				$("#save,#redo").show();
				if(this.editRow==undefined){
					$("#datagrid").datagrid('insertRow',{
						index:0,
						row:{
							
						}
					});
					//将第一行设置为可编辑状态
					$("#datagrid").datagrid('beginEdit',0);
					this.editRow = 0; 
				}
			},
			save:function(){
				//将第一行设为结束编辑
				$("#datagrid").datagrid('endEdit',this.editRow);
			},
			redo:function(){
				this.editRow = undefined;
				$("#save,#redo").hide();
				$("#datagrid").datagrid('rejectChanges');
			},
			edit:function(){
				var rows = $("#datagrid").datagrid('getSelections');
				if(rows.length == 1){
					if(this.editRow!=undefined){
						$("#datagrid").datagrid('endEdit',this.editRow);
					}
					
					if(undefined==this.editRow){
						var index = $("#datagrid").datagrid('getRowIndex',rows[0]);
						$("#save,#redo").show();
						$("#datagrid").datagrid('beginEdit',index);
						this.editRow = index;
						$("#datagrid").datagrid('unselectRow',index);
					}
				}else{
					$.messager.alert('警告','修改必须选中一行且只能选中一行','warning');
				}
				
			},
			remove:function(){
				var rows = $("#datagrid").datagrid('getSelections');
				if(rows.length>0){
					$.messager.confirm('提示','您确定删除这些记录吗？',function(flag){
						if(flag){
							var ids = [];
							for(var i = 0;i<rows.length;i++){
								ids.push(rows[i].id);
							}
							$.ajax({
								type:'post',
								url:'gradedelete.do',
								data:{
									ids:ids.join(','),
								},
								beforeSend:function(){
									$("#datagrid").datagrid('loading');
								},
								success:function(data){
									if(data){
										$("#datagrid").datagrid('loaded');
										$("#datagrid").datagrid('load');
										$("#datagrid").datagrid('unselectAll');
										$.messager.show({
											title:'提示',
											msg:'删除成功了'+data,
										});
									}
								}
							});
						}
					});
				}else{
					$.messager.alert('提示','请选择你要删除的记录','info');
				}
			},
			image:function(){
				var rows = $("#datagrid").datagrid('getSelections');
				if(rows.length == 1){
					var jq = top.jQuery;    
					var url = 'serviceimageupload.jsp?id='+rows[0].id;
					window.location.href = url;
				}else{
					$.messager.alert('警告','修改必须选中一行且只能选中一行','warning');
				}
				
			}
			
		};
	$("#datagrid").datagrid({
		width:700,
		fit:true,
		url:'gradelist.do',
		title:'班级信息',
		striped:true,
		rownumbers:true,
		fitColumns:true,
		columns:[[
			{
				field:'id',
				title:'编号',
				width:100,
				checkbox:true,
			},
			{
				field:'gradename',
				title:'班级名',
				width:100,
				editor:{
					type:'validatebox',
					options:{
						required:true,
					},
				}
			},
			{
				field:'count',
				title:'人数',
				width:100,
				editor:{
					type:'validatebox',
					options:{
						required:true,
					},
				}
			},
			{
				field:'avg',
				title:'平均分',
				width:100,
				editor:{
					type:'numberbox',
					options:{
						required:true,
					},
				}
			},
		]],
		toolbar:'#tb',
		pagination:true,
		pagePosition:'bottom',
		onAfterEdit:function(rowIndex,rowData,changes){
			obj.editRow = undefined;
			$("#save,#redo").hide();
			
			var inserted = $("#datagrid").datagrid('getChanges','inserted');
			var updated = $("#datagrid").datagrid('getChanges','updated');
			//执行新增的方法
			if(inserted.length>0){
				$.ajax({
					type:'post',
					url:'gradesave.do',
					data:{
						number:inserted[0].number,
						gradename:inserted[0].gradename,
						count:inserted[0].count,
						avg:inserted[0].avg,
					},
					beforeSend:function(){
						$("#datagrid").datagrid('loading');
					},
					success:function(data){
						if(data){
							$("#datagrid").datagrid('loaded');
							$("#datagrid").datagrid('load');
							$("#datagrid").datagrid('unselectAll');
							$.messager.show({
								title:'提示',
								msg:'插入成功了'+data,
							});
						}
					}
				});
			};
			//执行修改的方法
			if(updated.length>0){
				$.ajax({
					type:'post',
					url:'gradeupdate.do',
					data:{
						id:updated[0].id,
						number:updated[0].number,
						gradename:updated[0].gradename,
						count:updated[0].count,
						avg:updated[0].avg,
					},
					beforeSend:function(){
						$("#datagrid").datagrid('loading');
					},
					success:function(data){
						if(data){
							$("#datagrid").datagrid('loaded');
							$("#datagrid").datagrid('load');
							$("#datagrid").datagrid('unselectAll');
							$.messager.show({
								title:'提示',
								msg:'修改成功了'+data,
							});
						}
					}
				});
			}
		},
		onDblClickRow:function(rowIndex,rowdata){
			
		},
		onRowContextMenu:function(e,rowIndex,rowData){
			e.preventDefault();
			$("#menu").menu('show',{
				top:e.pageY,
				left:e.pageX,
			})
		}
	});
})
