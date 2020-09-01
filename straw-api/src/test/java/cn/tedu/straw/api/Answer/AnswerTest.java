package cn.tedu.straw.api.Answer;

import cn.tedu.straw.api.mapper.AnswerMapper;
import cn.tedu.straw.api.service.IAnswerService;
import cn.tedu.straw.commons.vo.AnswerVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AnswerTest {
    @Autowired
    private AnswerMapper mapper;
    @Autowired
    private IAnswerService answerService;

    @Test
    public void a(){
        Integer id=5;
        List<AnswerVO> list=answerService.getAnswerList(id);
        for (AnswerVO vo:list
             ) {
            log.debug("vo>>>{}",vo);
        }
    }
}
