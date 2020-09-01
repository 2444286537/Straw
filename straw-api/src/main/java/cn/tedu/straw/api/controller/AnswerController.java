package cn.tedu.straw.api.controller;


import cn.tedu.straw.api.DTO.PostAnswerDTO;
import cn.tedu.straw.api.ex.ParameterValidationException;
import cn.tedu.straw.api.service.IAnswerService;
import cn.tedu.straw.commons.security.LoginUserInfo;
import cn.tedu.straw.commons.vo.AnswerVO;
import cn.tedu.straw.commons.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
@RequestMapping("/v1/answers")
public class AnswerController {
    @Autowired
    private IAnswerService answerService;

    @PostMapping("/post")
    public R<Void> post(@Valid PostAnswerDTO postAnswerDTO, BindingResult bindingResult,
                        @AuthenticationPrincipal LoginUserInfo loginUserInfo){
        if(bindingResult.hasErrors()){
            String errorMessage=bindingResult.getFieldError().getDefaultMessage();
            throw new ParameterValidationException(errorMessage);
        }
        answerService.post(postAnswerDTO,loginUserInfo.getId(),loginUserInfo.getNickname());
        return R.ok();
    }

    @GetMapping({"", "/"})
    public R<List<AnswerVO>> getAnswerList(Integer questionId){

        return R.ok(answerService.getAnswerList(questionId));
    }
}
