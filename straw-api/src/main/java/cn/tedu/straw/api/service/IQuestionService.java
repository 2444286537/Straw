package cn.tedu.straw.api.service;

import cn.tedu.straw.api.DTO.PostQuestionDTO;
import cn.tedu.straw.commons.model.Question;
import cn.tedu.straw.commons.vo.QuestionDetailVO;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.QuestionVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
public interface IQuestionService extends IService<Question> {
    Integer post(PostQuestionDTO postQuestionDTO, Integer userId, String userNickname );
    List<QuestionListItemVO> getHotList();
    PageInfo<QuestionVO> getMyQuestions(Integer userId, Integer page);
    QuestionDetailVO getQuestionDetail(Integer id);
}
