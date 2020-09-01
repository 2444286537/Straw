package cn.tedu.straw.api.service;

import cn.tedu.straw.api.DTO.PostAnswerDTO;
import cn.tedu.straw.api.ex.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AnswerServiceTests {

    @Autowired
    IAnswerService service;

    @Test
    void post() {
        try {
            PostAnswerDTO postAnswerDTO = new PostAnswerDTO()
                    .setQuestionId(1)
                    .setContent("测试1号问题的第1个答案");
            Integer userId = 8;
            String userNickName = "奥特曼";
            service.post(postAnswerDTO, userId, userNickName);
            log.debug("OK !!!");
        } catch (ServiceException e) {
            log.debug("error !!!");
            log.debug("type={}", e.getClass().getName());
            log.debug("message={}", e.getMessage());
        }
    }

}
