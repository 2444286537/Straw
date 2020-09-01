package cn.tedu.straw.api.mapper;

import cn.tedu.straw.api.service.IUserService;
import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class TeacherTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService iUserService;
    @Test
    public void Test(){
        List<TeacherSelectOptionVO> list=iUserService.getTeacherSelectOptionList();
        for (TeacherSelectOptionVO teacher:list
             ) {
            log.debug("teacher:{}",teacher);
        }
    }
}
