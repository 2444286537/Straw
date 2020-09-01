package cn.tedu.straw.api.controller;


import cn.tedu.straw.api.cache.TeacherCache;
import cn.tedu.straw.api.DTO.StudentRegisterDTO;
import cn.tedu.straw.api.ex.*;
import cn.tedu.straw.api.service.IUserService;
import cn.tedu.straw.commons.vo.R;
import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/v1/users")
@Slf4j
public class UserController {
    @Autowired
    private IUserService userService;

   /* @RequestMapping("ex")
    public String a() {
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            return "异常被处理";
        }
    }*/
    //http://localhost8080/api/v1/users/student/register
    @RequestMapping("/student/register")
    public R regStudent(@Validated StudentRegisterDTO studentRegisterDTO,
                        BindingResult bindingResult) {
            if(bindingResult.hasErrors()){
                String errorMessage=bindingResult.getFieldError().getDefaultMessage();
                log.debug("验证参数而是失败{}",errorMessage);
                throw new ParameterValidationException(errorMessage);
            }
            userService.regStudent(studentRegisterDTO);
            return R.ok();


    }
    @GetMapping("/teachers")
    public R<List<TeacherSelectOptionVO>> getTeachers() {
        return R.ok(TeacherCache.getTeachers());
    }

}
