package cn.tedu.straw.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 当前类用于演示案例1   @Deprecated  表示已废弃
 */
@Deprecated
/*@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)*/
public class WebSecurityConfig1 extends WebSecurityConfigurerAdapter {
    /**
     * 创建密码加密器对象交给Spring框架管理
     * @return 密码加密器对象
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("root")
                .password("1234")
                //指定授权  参数是权限表示字符串
                .authorities("/admin/list");

    }
}
