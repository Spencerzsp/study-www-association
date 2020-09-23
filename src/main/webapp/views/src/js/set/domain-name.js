layui.use(['table', 'form','admin'], function(){
    var $ = layui.$
    ,admin = layui.admin
    ,view = layui.view
    ,table = layui.table
    ,form = layui.form;
    sessionStorage.removeItem('shiant-portal-domain-name');

    table.render({
      elem: '#LAY-set-content-domain-name'
      ,url: layui.setter.url+'/service/domainName/getBeans' //模拟接口
      ,cols: [[
          {type: 'checkbox', fixed: 'left'}
          ,{field: 'id', width: 100, title: '编号'}
          ,{field: 'domain', title: '域名'}
          ,{field: 'title', title: '网站名'}
          ,{field: 'alias', title: '别名'}
          ,{field: 'updateDate', title: '修改时间',templet: '<div>{{ layui.util.timeAgo(d.updateDate) }}</div>'}
          ,{title: '操作', minWidth: 150, align: 'center', fixed: 'right', toolbar: '#table-content-list'}
      ]]
      ,page: true
      ,limit: 10
      ,limits: [10, 15, 20, 25, 30]
      ,text: {
    	    none: '暂无相关数据' 
      }
    });
    
    //监听工具条
    table.on('tool(LAY-set-content-domain-name)', function(obj){
    	var data = obj.data;
    	if(obj.event === 'del'){
    		layer.confirm('确定删除此网站名？', function(index){
    			$.ajax({
					url:layui.setter.url+'/service/domainName/deleteBean?ids='+data.id,
					type:"GET",
					dataType:"json",
					success:function(data){
						layer.msg("删除成功!");
						sessionStorage.removeItem('shiant-portal-domain-name');
						obj.del();      	
					},
					error:function(data){
						layer.msg(data.msg);
					}
				});
    			layer.close(index);
    		});
    	} else if(obj.event === 'edit'){
    		admin.popup({
    			title: '编辑网站名'
    			,area: ['400px', '400px']
    			,id: 'LAY-popup-content-edit'
    			,success: function(layero, index){
    				view(this.id).render('set/domain-name-form', data).done(function(){
    					form.render(null, 'layuiadmin-domain-name-form');
    					//监听提交
    					form.on('submit(layuiadmin-domain-name-form-submit)', function(data){
    						var field = data.field; //获取提交的字段
							$.ajax({
								url:layui.setter.url+'/service/domainName/updateBean',
								data:field,
								type:"POST",
								dataType:"json",
								success:function(data){
									layer.msg(data.msg);
									sessionStorage.removeItem('shiant-portal-domain-name');
									layui.table.reload('LAY-set-content-domain-name'); //重载表格
									layer.close(index); //执行关闭 
								},
								error:function(data){
									layer.msg(data.msg);
								}
							});
    					});
    				});
    			}
    		});
    	}else if(obj.event === 'analysis'){
    		layer.msg("研发中...");
    	}
    });
    
    var active = {
    		add: function(othis){
    			admin.popup({
    				title: '添加网站名'
    					,area: ['400px', '400px']
    					,id: 'LAY-popup-content-add'
    					,success: function(layero, index){
    						view(this.id).render('set/domain-name-form').done(function(){
	    						form.render(null, 'layuiadmin-domain-name-form');
	    						//监听提交
	    						form.on('submit(layuiadmin-domain-name-form-submit)', function(data){
	    							var field = data.field; //获取提交的字段
	    							$.ajax({
	    								url:layui.setter.url+'/service/domainName/addBean',
	    								data:field,
	    								type:"POST",
	    								dataType:"json",
	    								success:function(data){
	    									layer.msg(data.msg);
	    									sessionStorage.removeItem('shiant-portal-domain-name');
	    									layui.table.reload('LAY-set-content-domain-name'); //重载表格
	    									layer.close(index); //执行关闭 
	    								},
	    								error:function(data){
	    									layer.msg(data.msg);
	    								}
	    							});
	    						});
    						});
    				}
    			});
    		},
    		batchdel: function(){
    			var checkStatus = table.checkStatus('LAY-set-content-domain-name')
    			,checkData = checkStatus.data; //得到选中的数据
    			
    			//获取选中数量
    			var selectCount = checkStatus.data.length;
    			if(selectCount == 0){
    				layer.msg('批量删除至少选中一项数据',function(){});
    				return false;
    			}
    			
    			layer.confirm('确定删除吗？', function(index) {
    				layer.close(index);
    				index = layer.load(1, {shade: [0.1,'#fff']});
    				
    				var isStr="";
    				for(var i=0; i<selectCount; i++){
    					isStr = isStr + "," + checkStatus.data[i].id;
    				}
    				
    				$.ajax({
    					url:layui.setter.url+'/service/domainName/deleteBean?ids='+isStr,
    					type:"GET",
    					dataType:"json",
    					success:function(data){
    						layer.msg("删除成功!");
    						sessionStorage.removeItem('shiant-portal-domain-name');
    						layui.table.reload('LAY-set-content-domain-name'); 
    						layer.close(index);             	},
    						error:function(data){
    							layer.close(index); 
    							layer.msg(data.msg);
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
    form.on('submit(LAY-set-domain-name-search)', function(data){
      var field = data.field;

      //执行重载
      table.reload('LAY-set-content-domain-name', {
        where: field
      });
    });

});