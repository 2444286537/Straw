$(document).ready(function () {
    $('#summernote').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN',
        placeholder: '请输入问题的详细描述...',
        callbacks: {
            onImageUpload: function(files) {
                // 当前函数的参数名是自定义的，表示summernote浏览到的若干个图片文件
                // alert("将执行自定义上传图片的处理！");
                // 取出浏览到的第1个图片（也许也是唯一的一个图片）
                let file = files[0];
                // 上传时提交到服务器端的数据，上传文件的数据必须通过new FormData()来创建数据对象
                let data = new FormData();
                // 添加到上传的数据对象中的属性名称必须与服务器端接收的参数名保持一致
                data.append('imageFile', file);
                // 执行上传
                // 处理上传的$.ajax()中必须配置processData:false和contentType:false
                $.ajax({
                    url: '/api/v1/questions/upload-image',
                    data: data,
                    type: 'post',
                    processData: false,
                    contentType: false,
                    dataType: 'json',
                    success: function(json) {
                        if (json.state == 2000) {
                            // alert("上传成功，请在浏览器的控制台查看图片路径！");
                            // console.log(json.data);
                            // --------------------------
                            // 创建<img>标签并用于显示新上传的图片
                            // new Image()对象对应的就是HTML中的<img>标签
                            let img = new Image();
                            img.src = json.data;
                            // 将<img>标签插入到summernote中
                            // 以下代码中的insertNode是固定的名称，是summernote定义的
                            $('#summernote').summernote('insertNode', img);
                        } else {
                            alert(json.message);
                        }
                    }
                });
            }
        }
    });
});