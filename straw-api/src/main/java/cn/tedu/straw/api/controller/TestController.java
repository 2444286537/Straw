package cn.tedu.straw.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    /*@PreAuthorize("hasAuthority('/admin/list')")//表示执行该方法之前需要授权*/
    @GetMapping("/admin/list")//参数就是权限标识
    public String adminList(){
        return "admin list";
    }

    /*@PreAuthorize("hasAuthority('/admin/delete')")*/
    @GetMapping("/admin/delete")
    public String admindelete(){
        return "admin delete";
    }
    @GetMapping("/user/list")//参数就是权限标识
    public String userList(){
        return "user list";
    }


    @GetMapping("/user/delete")
    public String userelete(){
        return "user delete";
    }
}
