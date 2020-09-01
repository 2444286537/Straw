package cn.tedu.straw.gateway;

import cn.tedu.straw.commons.model.Permission;
import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.gateway.mapper.PermissionMapper;
import cn.tedu.straw.gateway.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@Slf4j
class StrawGatewayApplicationTests {

    @Autowired
    UserMapper mapper;

    @Test
    void insert(){
        User user=new User();
        user.setUsername("李wu");
        user.setPassword("123456");
        int rows=mapper.insert(user);
        log.debug("rows={}",rows);
    }

    @Test
    void delete(){
        Integer id=1;
        int rows=mapper.deleteById(id);
        log.debug("rows={}",rows);
    }

    @Test
    void updateById(){
        User user=new User();
        user.setId(3);
        user.setPassword("66666");
        int rows=mapper.updateById(user);
        log.debug("rows={}",rows);
    }
    @Test
    void a(){
        Integer id=5;
        User user=mapper.selectById(id);
        log.debug("query result>>>{}",user);
    }
    @Test
    void selectByUsername(){
        //根据用户查询数据  指定用户名的值
        String username="李肆";
        //创建查询条件对象
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        //eq方法表示username=?  表示“等于”的判断条件
        queryWrapper.eq("username",username);
        User user=mapper.selectOne(queryWrapper);
        log.debug("query result>>>{}",user);
    }

    @Test
    void selectList(){
        List<User> users=mapper.selectList(null);
        log.debug("query result count={}",users.size());
        //输入iter即可生成遍历for循环  iter=iterator
        for (User user : users) {
            log.debug(">>>{}",user);
        }
    }
    @Autowired
    PermissionMapper permissionMapper;
    @Test
    public void findByUsername(){
        String username="13214220608";
        List<Permission> list= permissionMapper.findByUsername(username);
        for (Permission p:list
        ) {
            System.out.println(p);
        }

    }

}
