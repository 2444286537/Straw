package cn.tedu.straw.gateway;

import cn.tedu.straw.gateway.service.IUserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    IUserService service;



}
