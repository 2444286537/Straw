package cn.tedu.straw.api.service;

import cn.tedu.straw.api.DTO.PostAnswerDTO;
import cn.tedu.straw.api.DTO.PostQuestionDTO;
import cn.tedu.straw.commons.model.Answer;
import cn.tedu.straw.commons.vo.AnswerVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
public interface IAnswerService extends IService<Answer> {
    void post(PostAnswerDTO postAnswerDTO, Integer userId, String userNickName);
    List<AnswerVO> getAnswerList(Integer questionId);
 }
