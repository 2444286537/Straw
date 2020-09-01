package cn.tedu.straw.gateway.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //也可自动装配new BCryptPasswordEncoder（）代码加密器
    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录页面的URL
        String loginPage="/login.html";
        //处理登陆的URL
        //默认情况下Spring Securityde /login路径，党请求方式是get时  表示访问登录页面
        String loginProcessingUrl="/login";
        String loginFailureUrl="/login.html?error";
        //登陆成功后重定向的路径
        String loginSuccessUrl="/index.html";
        String logoutUrl="/logout";
        String logoutSuccessUrl="/login.html?logout";
        //对所有请求进行授权
        http.authorizeRequests().anyRequest().permitAll();
        //关闭跨域攻击
        http.csrf().disable();
        //使用表单验证登录
        http.formLogin().loginPage(loginPage)
        .loginProcessingUrl(loginProcessingUrl)
        .failureUrl(loginFailureUrl)
        .defaultSuccessUrl(loginSuccessUrl);
        //配置退出登录
        http.logout().logoutUrl(logoutUrl)
                .logoutSuccessUrl(logoutSuccessUrl);
    }
}
