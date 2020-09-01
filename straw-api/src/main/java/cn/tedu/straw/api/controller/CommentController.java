package cn.tedu.straw.api.controller;


import cn.tedu.straw.api.DTO.PostCommentDTO;
import cn.tedu.straw.api.ex.ParameterValidationException;
import cn.tedu.straw.api.service.ICommentService;
import cn.tedu.straw.commons.model.Comment;
import cn.tedu.straw.commons.security.LoginUserInfo;
import cn.tedu.straw.commons.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
@RestController
@RequestMapping("/v1/comments")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    // http://localhost/api/v1/comments/post?answerId=7&content=Comment7
    // http://localhost:8080/v1/comments/post?answerId=7&content=Comment7
    @RequestMapping("/post")
    public R<Comment> post(@Valid PostCommentDTO postCommentDTO,
                           BindingResult bindingResult,
                           @AuthenticationPrincipal LoginUserInfo loginUserInfo) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            throw new ParameterValidationException(message);
        }
        Comment comment = commentService.post(postCommentDTO, loginUserInfo.getId(), loginUserInfo.getNickname());
        return R.ok(comment);
    }
}
