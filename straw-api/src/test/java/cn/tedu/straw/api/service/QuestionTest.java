package cn.tedu.straw.api.service;

import cn.tedu.straw.api.DTO.PostQuestionDTO;
import cn.tedu.straw.api.ex.InsertException;
import cn.tedu.straw.api.ex.ServiceException;
import cn.tedu.straw.api.mapper.QuestionMapper;
import cn.tedu.straw.api.service.impl.QuestionServiceImpl;
import cn.tedu.straw.commons.model.Question;
import cn.tedu.straw.commons.vo.QuestionDetailVO;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.QuestionVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class QuestionTest {
    @Autowired
    private IQuestionService service;

    @Test
    public void a(){
        try{
            PostQuestionDTO question=new PostQuestionDTO();
            question.setTitle("单元测试怎么用");
            question.setContent("我啥也不会啊");
            question.setTagIds(new Integer[]{1,2,3});
            question.setTeacherIds(new Integer[]{1,3,5});
            Integer userId=12;
            String userNickName="12号sb";
            Integer questionId=service.post(question,userId,userNickName);
            log.debug("post question ok,queston id={}",questionId);
        }catch (ServiceException e){
            log.debug("error,type={}",e.getClass().getName());
            log.debug("message={}",e.getMessage());
        }


    }
    @Autowired
    private QuestionMapper mapper;
    @Test
    void haha(){
        List<QuestionListItemVO> questions=mapper.findHotList();
        for (QuestionListItemVO question:questions
             ) {
            log.debug("question={}",question);
        }
    }
    @Autowired
    private QuestionServiceImpl questionService;
    @Test
    void heihei(){
        List<QuestionListItemVO> questions=questionService.getHotList();
        for (QuestionListItemVO question:questions
        ) {
            log.debug("question={}",question);
        }
    }

    @Test
    public void bb(){
        int page=1;
        int pageSize=3;
        PageHelper.startPage(page,pageSize);
        Integer id=1;
        List<QuestionVO> questions=mapper.findMyQuestions(id);
        for (QuestionVO question:questions
        ) {
            log.debug("question={}",question);
        }
        PageInfo<QuestionVO> questionPageInfo = new PageInfo(questions);
        log.debug("pageinfo >>> {}", questionPageInfo);
    }

    @Test
    public void findById(){
        Integer id=2;
        QuestionDetailVO questionDetailVO=questionService.getQuestionDetail(2);
        log.debug("questionDetailVO>>>>{}",questionDetailVO);
    }

}
