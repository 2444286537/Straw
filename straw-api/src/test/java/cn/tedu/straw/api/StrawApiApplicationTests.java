package cn.tedu.straw.api;

import cn.tedu.straw.api.mapper.UserMapper;
import cn.tedu.straw.commons.model.Permission;
import cn.tedu.straw.commons.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
class StrawApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    DataSource dataSource;


    @Test
    void getConnertion() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void md5Test(){
        String password="1234";
        String encodePassword= DigestUtils.md5Hex(password);
        System.out.println("encodePassword:"+encodePassword);
    }
    @Test
    public void sha256Test(){
        String password="1234";
        String encodePassword= DigestUtils.sha256Hex(password);//sha384Hex  sha512Hex
        System.out.println("encodePassword:"+encodePassword);
    }
    @Test
    public void bcryptTest(){
        String password="1234";
        String encodePassword=new BCryptPasswordEncoder().encode(password);
        System.out.println("encodePassword:::"+encodePassword);
    }

    @Resource
    UserMapper userMapper;

    @Test
    public void findByUserName(){
        String username="13214220601";
        User user=userMapper.findByUserName(username);
        log.debug("user:{}",user);

    }
    @Test
    public void aa(){

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        System.out.println("现在的时间是：：：：："+dateFormat.format(date));
    }


}
