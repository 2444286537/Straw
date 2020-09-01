package cn.tedu.straw.api.schedule;

import cn.tedu.straw.api.cache.QuestionCache;
import cn.tedu.straw.api.service.IQuestionService;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionCacheSchdule {
    @Autowired
    private IQuestionService questionService;

    public static final Object LOCK = new Object();

    @Scheduled(fixedRate = 10*60*1000)
    public void loadHotQuestionsCache(){
        synchronized (LOCK){
            QuestionCache.getQuestions().clear();
            List<QuestionListItemVO> questions=questionService.getHotList();
            QuestionCache.getQuestions().addAll(questions);
        }
    }
}
