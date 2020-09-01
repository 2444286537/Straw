package cn.tedu.straw.api;

import cn.tedu.straw.api.DTO.StudentRegisterDTO;
import cn.tedu.straw.api.ex.ServiceException;
import cn.tedu.straw.api.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    IUserService service;

    @Test
    void regStudent() {
        try {
            StudentRegisterDTO studentRegisterDTO = new StudentRegisterDTO()
                    .setPhone("13800896886")
                    .setPassword("1234")
                    .setDayOfBirth(LocalDate.now())
                    .setGender(1)
                    .setNickname("Frank")
                    .setInviteCode("JSD2002-525416")
                    .setSelfIntroduction("HAHAHA");
            service.regStudent(studentRegisterDTO);
            log.debug("注册成功！！！");
        } catch (ServiceException e) {
            log.debug("注册失败！！！异常={}" , e.getClass().getName());
        }
    }
}
