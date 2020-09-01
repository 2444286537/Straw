package cn.tedu.straw.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {
    @GetMapping("/index.html")
    /**
     * 前缀  /templates
     * 后缀   .html
     * /templates/index.html
     */
    public String index(){
        return "index";
    }
    @GetMapping("/register.html")
    public String register(){
        return "register";
    }

    @GetMapping("/login.html")
    public String login(){
        return "login";
    }

    @GetMapping("/question/create.html")
    public String createQuestion(){
        return "question/create";
    }

    @GetMapping("/question/detail.html")
    public String showQuestionDetail(){
        return "question/detail";
    }
}
