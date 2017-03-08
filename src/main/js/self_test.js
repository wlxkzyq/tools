/* create by zyq */
var survey={
            "title":"投资风险承受能力测试",
            "description":"高收益往往伴随着高风险，投资者一定要根据自己的风险承受能力，理性选择偷投资方式。通过本项测试可以了解您可承受的风险程度、以及您的投资经验，借此协助您选择合适的理财产品类别，以达到您的投资目标。",
            "testResult":[
            	{"score":"80~100","level":"A","title":"钻石级","content":"<p>您的风险承受能力非常高， 您较好的财务状况能轻松应对多种投资方式，您的理财关键在于升值赚钱！建议您的理财搭配方式：10%的货币理财、10%的债券理财、80%的股票理财。</p>"},
            	{"score":"60~79","level":"B","title":"钻石级","content":"<p>您的风险承受能力较高，您的投资方式可以适当选择一些高风险高收益的方式，但注意风险分散。建议您的理财搭配方式：10%的货币理财、40%的债券理财、50%的股票理财。</p>"},
                {"score":"40~59","level":"C","title":"钻石级","content":"<p>您的风险承受能力适中，您可以选择一些风险型的投资方式，但不宜占比过高。建议您的理财搭配方式：20%的货币理财、50%的债券理财、30%的股票理财。</p>"},
                {"score":"20~39","level":"D","title":"黄金级","content":"<p>您的风险承受能力较低，您应该选择较为稳健的投资理财方式，理财关键在于保值！建议您的理财搭配方式：50%的货币理财、40%的债券理财、10%的股票理财。</p>"},
                {"score":"0~19","level":"E","title":"白银级","content":"<p>您的风险承受能力非常低，您应该选择谨慎的投资理财方式，关键在于保本保值！建议您的理财搭配方式：70%的货币理财、30%的债券理财，避免选择股票理财。</p>"}
             ],
            "questions":[
            	{"id":"1",
                    "desc":"1.您目前从事的职业是：",
                    "special":"",
                    "answers":[
                        {
                            "id":"1",
                            "score":10,
                            "desc":"A. 公教人员",
                            "special":""
                        },
                        {
                            "id":"2",
                            "score":8,
                            "desc":"B.上班族",
                            "special":""
                        },
                        {
                        	"id":"3",
                        	"score":6,
                        	"desc":"C.佣金收入者",
                        	"special":""
                        },
                        {
                        	"id":"4",
                        	"score":4,
                        	"desc":"D.自营职业者",
                        	"special":""
                        },
                        {
                        	"id":"5",
                        	"score":2,
                        	"desc":"E.失业",
                        	"special":""
                        }
                    ]
                },
                {"id":"2",
                	"desc":"2.您目前的家庭成员状况：",
                	"special":"",
                	"answers":[
                		{
                			"id":"1",
                			"score":10,
                			"desc":"A.未婚 ",
                			"special":""
                		},
                		{
                			"id":"2",
                			"score":8,
                			"desc":"B.双薪无子女",
                			"special":""
                		},
                		{
                			"id":"3",
                			"score":6,
                			"desc":"C.双薪有子女",
                			"special":""
                		},
                		{
                			"id":"4",
                			"score":4,
                			"desc":"D.单薪有子女",
                			"special":""
                		},
                		{
                			"id":"5",
                			"score":2,
                			"desc":"E.单薪养三代",
                			"special":""
                		}
                		]
                },
                {"id":"3",
                	"desc":"3.您目前的置业状况：",
                	"special":"",
                	"answers":[
                		{
                			"id":"1",
                			"score":10,
                			"desc":"A.投资不动产",
                			"special":""
                		},
                		{
                			"id":"2",
                			"score":8,
                			"desc":"B.自宅无房贷",
                			"special":""
                		},
                		{
                			"id":"3",
                			"score":6,
                			"desc":"C.房贷﹤50%",
                			"special":""
                		},
                		{
                			"id":"4",
                			"score":4,
                			"desc":"D.房贷﹥50%",
                			"special":""
                		},
                		{
                			"id":"5",
                			"score":2,
                			"desc":"E.无自宅",
                			"special":""
                		}
                		]
                },
                {"id":"4",
                	"desc":"4.您的投资经验：",
                	"special":"",
                	"answers":[
                		{
                			"id":"1",
                			"score":10,
                			"desc":"A.10年以上",
                			"special":""
                		},
                		{
                			"id":"2",
                			"score":8,
                			"desc":"B.6---10年",
                			"special":""
                		},
                		{
                			"id":"3",
                			"score":6,
                			"desc":"C. 2-----5年",
                			"special":""
                		},
                		{
                			"id":"4",
                			"score":4,
                			"desc":"D.1年以内",
                			"special":""
                		},
                		{
                			"id":"5",
                			"score":2,
                			"desc":"E.无",
                			"special":""
                		}
                		]
                },
                {"id":"5",
                	"desc":"5.您的投资知识：",
                	"special":"",
                	"answers":[
                		{
                			"id":"1",
                			"score":10,
                			"desc":"A.有专业证照",
                			"special":""
                		},
                		{
                			"id":"2",
                			"score":8,
                			"desc":"B.财金专业毕业",
                			"special":""
                		},
                		{
                			"id":"3",
                			"score":6,
                			"desc":"C.自修有心得",
                			"special":""
                		},
                		{
                			"id":"4",
                			"score":4,
                			"desc":"D.懂一些",
                			"special":""
                		},
                		{
                			"id":"5",
                			"score":2,
                			"desc":"E.一片空白",
                			"special":""
                		}
                		]
                }

             ],
            "person":{
                "score":0,
                "special":"",
                "choice":{}
            },
            "current":-1,
        	"next":function nextQuestion(){
        		this.current+=1;
        		var obj=this.questions[this.current];
        		if(obj==undefined){return;}
        		while(obj.special!=null&&obj.special!=""&&this.person.special.indexOf(obj.special)<0){
        			this.current+=1;
        			obj=this.questions[this.current];
        		}
        		return obj;
        	},
        	"getScore":function(){
        		this.person.score=0;
        		for(var s in this.person.choice){
        			var n=this.questions[s-1].answers[this.person.choice[s]-1].score;
        			this.person.score+=n;
        		}
        		return this.person.score;
        	},
        	"getResult":function(sc){
        		var score=parseInt(sc);
        		var resultList=this.testResult;
        		for(var i=0;i<resultList.length;i++){
        			var result=resultList[i];
        			var range=result.score;
        			var ran=range.split("~");
        			if(score<=parseInt(ran[1])&&score>=parseInt(ran[0])){
        				return result;
        			}
        			
        		}
        	}

    }