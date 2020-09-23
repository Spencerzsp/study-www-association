layui.use(['table', 'form','admin'], function(){
    var $ = layui.$
    ,admin = layui.admin
    ,view = layui.view
    ,table = layui.table
    ,form = layui.form;

    shiant.translate();
    
    table.render({
      elem: '#LAY-table-organization'
      ,url: layui.setter.url+'/service/organization/getBeans' //模拟接口
      ,cols: [[
          {type: 'checkbox'}
          ,{field: 'orgid', width: 60, title: shiant.l('编号')}
          ,{field: 'orgName', title: shiant.l('企业名称'), minWidth: 100}
          ,{field: 'orgCode', title: shiant.l('企业代码')}
          ,{field: 'legalPerson', title: shiant.l('企业管理员姓名')}
          ,{field: 'legalPersonPhone', title: shiant.l('企业管理员手机号')}
          ,{field: 'orgScale', title: shiant.l('企业规模')}
          ,{field: 'industry', title: shiant.l('所属行业')}
          ,{field: 'address', title: shiant.l('企业地址'), width: 200}
          ,{field: 'customerNum', title: shiant.l('客户数量')}
          ,{field: 'productNum', title: shiant.l('产品数量'), width: 90}
          ,{field: 'isDelete', title: shiant.l('状态'), width: 70, templet:function(d){
        	  if(!d['delete']){
        		  return shiant.l("正常");
        	  }else{
        		  return shiant.l("停用");
        	  }
          }}
          ,{field: 'createDate', title: shiant.l('创建时间'), width: 90,templet: '<div>{{ layui.util.timeAgo(d.createDate) }}</div>'}
          ,{title: shiant.l('操作'), minWidth: 100, align: 'center', fixed: 'right', toolbar: '#table-content-list'}
      ]]
      ,page: true
      ,limit: 10
      ,limits: [10, 15, 20, 25, 30]
      ,text: {
    	    none: shiant.l('暂无数据') 
      }
    });
    
    //监听工具条
    table.on('tool(LAY-table-filter-organization)', function(obj){
    	var data = obj.data;
    	if(obj.event === 'del'){
    		layer.confirm(shiant.l('确定删除企业'),{title:shiant.l('提示')}, function(index){
    			$.ajax({
					url:layui.setter.url+'/service/organization/deleteBean?ids='+data.orgid,
					type:"GET",
					dataType:"json",
					success:function(data){
						layer.msg(shiant.l("删除成功"));
						obj.del();      	
					},
					error:function(data){
						layer.msg(shiant.l(data.msg));
					}
				});
    			layer.close(index);
    		});
    	} else if(obj.event === 'edit'){
    		admin.popup({
    			title: shiant.l('编辑企业')
    			,area: ['850px', '500px']
    			,id: 'LAY-popup-content-edit'
    			,success: function(layero, index){
    				view(this.id).render('organization/organization-form', data).done(function(){
    					form.render(null, 'layuiadmin-organization-form');
    					shiant.translate('LAY-popup-content-edit');
    					//监听提交
    					form.on('submit(layuiadmin-organization-form-submit)', function(data){
    						var field = data.field; //获取提交的字段
    						if(field['delete'] == 'on'){
								field['delete'] = false;
							}else{
								field['delete'] = true;
							}
							$.ajax({
								url:layui.setter.url+'/service/organization/updateBean',
								data:field,
								type:"POST",
								dataType:"json",
								success:function(data){
									layer.msg(data.msg);
									layui.table.reload('LAY-table-organization'); //重载表格
									layer.close(index); //执行关闭 
								},
								error:function(data){
									layer.msg(shiant.l(data.msg));
								}
							});
    					});
    				});
    			}
    		});
    	}
    });
    
    var active = {
    		add: function(othis){
    			admin.popup({
    				title: shiant.l('添加企业')
    					,area: ['850px', '500px']
    					,id: 'LAY-popup-content-add'
    					,success: function(layero, index){
    						view(this.id).render('organization/organization-form').done(function(){
    							form.render(null, 'layuiadmin-organization-form');
    							shiant.translate('LAY-popup-content-add');
	    						//监听提交
	    						form.on('submit(layuiadmin-organization-form-submit)', function(data){
	    							var field = data.field; //获取提交的字段
	    							if(field['delete'] == 'on'){
	    								field['delete'] = false;
	    							}else{
	    								field['delete'] = true;
	    							}
	    							$.ajax({
	    								url:layui.setter.url+'/service/organization/addBean',
	    								data:field,
	    								type:"POST",
	    								dataType:"json",
	    								success:function(data){
	    									layer.msg(shiant.l(data.msg));
	    									layui.table.reload('LAY-table-organization'); //重载表格
	    									layer.close(index); //执行关闭 
	    								},
	    								error:function(data){
	    									layer.msg(shiant.l(data.msg));
	    								}
	    							});
	    						});
    						});
    				}
    			});
    		},
    		batchdel: function(){
    			var checkStatus = table.checkStatus('LAY-table-organization')
    			,checkData = checkStatus.data; //得到选中的数据
    			
    			//获取选中数量
    			var selectCount = checkStatus.data.length;
    			if(selectCount == 0){
    				layer.msg(shiant.l('批量删除至少选中一项数据'),function(){});
    				return false;
    			}
    			
    			layer.confirm(shiant.l('确定删除企业'), {title:shiant.l('提示')}, function(index) {
    				layer.close(index);
    				index = layer.load(1, {shade: [0.1,'#fff']});
    				
    				var isStr="";
    				for(var i=0; i<selectCount; i++){
    					isStr = isStr + "," + checkStatus.data[i].id;
    				}
    				
    				$.ajax({
    					url:layui.setter.url+'/service/organization/deleteBean?ids='+isStr,
    					type:"GET",
    					dataType:"json",
    					success:function(data){
    						layer.msg(shiant.l("删除成功"));
    						layui.table.reload('LAY-table-organization'); 
    						layer.close(index);             	},
    						error:function(data){
    							layer.close(index); 
    							layer.msg(shiant.l(data.msg));
    						}
    				});
    			});
    		}
    }; 

    $('.layui-btn.layuiadmin-btn-list').on('click', function(){
      var type = $(this).data('type');
      active[type] ? active[type].call(this) : '';
    });
    
    //监听搜索
    form.on('submit(LAY-organization-search)', function(data){
      var field = data.field;

      //执行重载
      table.reload('LAY-table-organization', {
        where: field
      });
    });
});