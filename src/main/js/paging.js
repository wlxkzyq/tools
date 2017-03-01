var paging={
        "pageSize":10,
        "currentPage":1,
        "totalRow":11,
        "totalPage":1,
        "url":"",
        "data":"",
        "type":"GET",
        "content":"<ul class='pagination'>"+
			        "<li><span>共<i style='color: red'></i>条</span></li>"+
			        "<li class='form-inline'>"+
			            "<div  style='float: left'>"+
			                "<input type='number' placeholder='页码' class='form-control'  style='border-radius: 0px;width: 50px;padding: 3px 3px;'><input type='button' value='跳转' class='form-control btn btn-info' style='border-radius: 0px;'>"+
			            "</div>"+
			        "</li>"+
			        "<li><a>首页</a></li>"+
			        "<li><a>上页</a></li>"+
			        "<li><a>下页</a></li>"+
			        "<li><a>尾页</a></li>"+
			        "<li><span>共<i style='color: red'>70</i>页</span></li>"+
			        "<li class='form-inline'>"+
			            "<div  style='float: left'>"+
			                "<select class='form-control' style='border-radius: 0px;width: 50px;padding: 3px 3px'>"+
			            	    "<option value='5'>5</option>"+
			                    "<option value='10' selected='selected'>10</option>"+
			                    "<option value='20'>20</option>"+
			                    "<option value='30'>30</option>"+
			                    "<option value='40'>40</option>"+
			                    "<option value='50'>50</option>"+
			                "</select>"+
			            "</div>"+
			        "</li>"+
			    "</ul>",
        "init":function(selector){
        	$(selector).html(this.content);
        	var pg=this;
        	$(".pagination").children().eq(1).children().children().last().click(function(){
        		pg.jump($(this).prev().val());
        	});
        	$(".pagination").children().eq(2).children().click(function(){
        		pg.jump(1);
        	});
        	$(".pagination").children().eq(3).children().click(function(){
        		pg.jump(pg.currentPage-1);
        	});
        	$(".pagination").children().last().children().children().change(function(){
        		pg.pageSize=$(this).val();
        		pg.jump(1);
        	});
        	$(".pagination").children().last().prev().prev().children().click(function(){
        		pg.jump(pg.totalPage);
        	});
        	$(".pagination").children().last().prev().prev().prev().children().click(function(){
        		pg.jump(pg.currentPage+1);
        	});
        	this.jump(1);
        },
		"handleData":function(){},
        "plus":function extend(destination, source) {
            for (var property in source)
              destination[property] = source[property];
            return destination;
        },
        
        "getNumbers":function(){
        	var array=new Array();
        	if(this.totalPage<=5){
        		for(var i=1;i<=this.totalPage;i++){
        			array.push(i);
        		}
        	}else if(this.currentPage<=5){
        		array.push(1);
        		array.push(2);
        		array.push(3);
        		array.push(4);
        		array.push(5);
        	}else{
        		for (var i = 0; i < 5; i++) {
					if((this.currentPage+i-1)<=this.totalPage){
						array.push(this.currentPage+i-1);
					}else{
						break;
					}
				}
        	}
        	return array;
        },
        
        "insertNumber":function(array){
        	$(".pagination .pg_number").remove();
        	var html="";
        	for (var i = 0; i < array.length; i++) {
        		if(this.currentPage==array[i]){
        			html+="<li class='pg_number active'><a>"+array[i]+"</a></li>"
        		}else{
					html+="<li class='pg_number'><a>"+array[i]+"</a></li>"
        		}
			}
        	$(".pagination").children().eq(3).after(html);
        	var pa=this;
        	$(".pagination .pg_number a").click(function(){
        		pa.jump($(this).html());
        	});
        },

        "getTotalPage":function(){
            return Math.ceil(this.totalRow/this.pageSize);
         },
        "jump":function(n){
        	if(n<1){n=1;}
        	if(n>this.totalPage){n=this.totalPage;}
        	this.currentPage=n;
            var datas;
            $.ajax({
                url:this.url,
                type:this.type,
                dataType:"json",
                data:this.plus(this.plus({"page":this.currentPage,"rows":this.pageSize},this.data)),
                async: false,
                error:function(){
                    alert("分页请求数据失败！");
                },
                success:function(data){
                    datas=data;
                }
            });
            
            this.totalRow=datas.total;
            this.totalPage=this.getTotalPage();
            
            if(typeof this.handleData=="function"){
            	this.handleData(datas);
            }
            
            var array=this.getNumbers();
            this.insertNumber(array);
            $(".pagination").children().first().children().children().html(this.totalRow);
            $(".pagination").children().last().prev().children().children().html(this.totalPage);
            $(".pagination").children().last().children().children().val(this.pageSize);
        }


    };