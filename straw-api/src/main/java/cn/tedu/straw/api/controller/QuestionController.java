package cn.tedu.straw.api.controller;


import cn.tedu.straw.api.cache.QuestionCache;
import cn.tedu.straw.api.DTO.PostQuestionDTO;
import cn.tedu.straw.api.ex.*;
import cn.tedu.straw.api.service.IQuestionService;
import cn.tedu.straw.commons.security.LoginUserInfo;
import cn.tedu.straw.commons.vo.QuestionDetailVO;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.QuestionVO;
import cn.tedu.straw.commons.vo.R;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
@RestController
@RequestMapping("/v1/questions")
public class QuestionController {

    @Value("${project.upload.base-dir}")
    private String uploadBaseDir;
    @Value("${project.upload.question-image.max-size}")
    private long imageMaxSize;
    @Value("${project.upload.question-image.types}")
    private List<String> imageTypes;
    @Value("${project.resource-server.name}")
    private String resourceServerName;
    @Autowired
    private IQuestionService questionService;

    // http://localhost/api/v1/questions/post?title=HelloWorld&content=PublicStaticVoidMain&tagIds=2&tagIds=7&tagIds=9&teacherIds=1&teacherIds=5&teacherIds=8&userId=13&userNickName=Alex
    // http://localhost:8080/v1/questions/post?title=HelloWorld&content=PublicStaticVoidMain&tagIds=2&tagIds=7&tagIds=9&teacherIds=1&teacherIds=5&teacherIds=8&userId=13&userNickName=Alex
    @RequestMapping("/post")
    public R<Integer> post(@Valid PostQuestionDTO postQuestionDTO,
                           BindingResult bindingResult,
                           @AuthenticationPrincipal LoginUserInfo loginUserInfo) {
        System.out.println("--------------------");
        System.out.println(postQuestionDTO.toString());
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            throw new ParameterValidationException(errorMessage);
        }
        Integer questionId = questionService.post(postQuestionDTO, loginUserInfo.getId(), loginUserInfo.getNickname());
        return R.ok(questionId);
    }
    //http://localhost:8080/v1/questions/hot-list
    @GetMapping("/hot-list")
    public R<List<QuestionListItemVO>> g(){
        return R.ok(QuestionCache.getQuestions());
    }

    // http://localhost:8080/v1/questions/upload-image
    @PostMapping("/upload-image")
    public R<String> uploadImage(MultipartFile imageFile) {
        // 调用参数imageFile.isEmpty()判断上传的文件是否为空
        boolean isempty=imageFile.isEmpty();
        if(isempty){
            throw new FileEmptyExeption("上传文件不能为空");
        }
        // 是：抛出FileEmptyExeption

        // 调用参数imageFile.getSize()获取文件大小
        long size=imageFile.getSize();
        if(size>imageMaxSize){
            throw new FileSizeException("上传大小本那个超过"+imageMaxSize/1024+"MB大小");
        }
        // 判断文件大小是否超出了imageMaxSize值的限制
        // 是：抛出FileSizeException
        String contentType=imageFile.getContentType();
        if(!imageTypes.contains(contentType)){
            throw new FileTypeException("上传格式仅支持以下文件"+imageTypes);
        }
        // 调用参数imageFile.getContentType()获取文件类型
        // 判断文件类型是否不被imageTypes包含（调用contains()方法）
        // 是：抛出FileTypeException
        String subDir= DateTimeFormatter.ofPattern("yyyy/MM").format(LocalDate.now());
        // 保存上传文件的文件夹：D:/IdeaProjects/straw-upload/2020/08
        // 通过DateTimeFormatter.ofPattern("yyyy/MM").format(LocalDate.now())得到 2020/08
        // 创建上传文件夹的File对象：File parent = new File(uploadBaseDir, 以上得到的2020/08);
        File parent =new File(uploadBaseDir,subDir);
        // 调用parent.exists()判断文件夹是否不存在
        if(!parent.exists()){
            parent.mkdirs();
        }
        // 是：调用parent.mkdirs()创建文件夹

        // 文件名filename：自定义策略，保证文件名不会冲突
        String filename=System.currentTimeMillis()+"-"+System.nanoTime();
        // 调用参数imageFile.getOriginalFilename()获取原始文件名
        String originaFileename=imageFile.getOriginalFilename();
        // 基于原始文件名进行截取，得到扩展名suffix
        String suffix=originaFileename.substring(originaFileename.lastIndexOf("."));
        // 基于以上filename和suffix得到文件全名child
        String child=filename+suffix;

        // 基于parent和child创建文件对象，表示将上传的文件保存到哪里
        try{
            imageFile.transferTo(new File(parent,child));
        }catch (IOException e){
            throw new FileIOException("出现读写异常，请稍后再试");
        }
        catch (IllegalStateException e){
            throw new FileStateException("出现状态异常");
        }
        // try {
        // -- 调用imageFile.transferTo()方法执行保存
        // } catch (IOException e) {
        // -- 抛出FileIOException
        // } catch (IllegalStateException e) {
        // -- 抛出FileStateException
        // }
        String imageUrl="/"+resourceServerName+"/"+subDir+"/"+child;
        // 基于 2020/08 和 文件全名child 得到上传的文件的路径：/2020/08/xxxx.jpg
        return R.ok(imageUrl);
        // 返回文件路径
    }

    // http://localhost/api/v1/questions/my-list?page=1
    @GetMapping("/my-list")
    public R<PageInfo<QuestionVO>> getMyList(@AuthenticationPrincipal LoginUserInfo loginUserInfo, Integer page) {
        if (page == null || page < 1) {
            page = 1;
        }
        PageInfo<QuestionVO> questions = questionService.getMyQuestions(loginUserInfo.getId(), page);
        return R.ok(questions);
    }

    // http://localhost/api/v1/questions/2
    @GetMapping("/{id}")
    public R<QuestionDetailVO> getQuestionDetail(@PathVariable("id") Integer id) {
        // 调用业务方法查询数据，并返回
        QuestionDetailVO questionDetailVO=questionService.getQuestionDetail(id);
        return R.ok(questionDetailVO);
    }
}


