let hotQuestionsApp=new Vue({
    el:'#hotQuestionsApp',
    data:{
        questions:[]
    },
    methods:{
        loadHotQuestion:function () {
           /* alert("准备加载热点问题")*/
            $.ajax({
                url: '/api/v1/questions/hot-list',
                success: function (json) {
                    let list = json.data;
                    let questions = [];
                    let statusTexts = ['未回复','未解决','已解决'];
                    let statusClasses = ['text-warning','text-info','text-success'];
                    for(let i = 0;i < list.length;i++){
                        let question = {};
                        question.title = list[i].title;
                        question.hits = list[i].hits;
                        console.log(list[i].status);
                        question.statusText = statusTexts[list[i].status];
                        question.statusClass = statusClasses[list[i].status];
                        questions[i] = question;
                    }
                    hotQuestionsApp.questions = questions;
                }
            });
        }
    },
    created:function () {
            this.loadHotQuestion();
    }
})