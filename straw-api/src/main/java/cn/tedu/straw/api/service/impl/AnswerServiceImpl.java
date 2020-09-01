package cn.tedu.straw.api.service.impl;

import cn.tedu.straw.api.DTO.PostAnswerDTO;
import cn.tedu.straw.api.DTO.PostQuestionDTO;
import cn.tedu.straw.api.ex.InsertException;
import cn.tedu.straw.api.mapper.AnswerMapper;
import cn.tedu.straw.api.service.IAnswerService;
import cn.tedu.straw.commons.model.Answer;

import cn.tedu.straw.commons.vo.AnswerVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements IAnswerService {
    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public void post(PostAnswerDTO postAnswerDTO, Integer userId, String userNickName) {
        // 创建Answer对象
        Answer answer = new Answer().setCountOfLikes(0)
                // 补全Answer对象的属性值：content		<<<	参数
                .setContent(postAnswerDTO.getContent())
                // 补全Answer对象的属性值：countOfLikes	<<< 0
                // 补全Answer对象的属性值：userId		<<< 参数
                .setUserId(userId)
                // 补全Answer对象的属性值：userNickName	<<< 参数
                .setUserNickName(userNickName)
                // 补全Answer对象的属性值：questionId	<<<	参数
                .setQuestionId(postAnswerDTO.getQuestionId())
                // 补全Answer对象的属性值：createdTime	<<< 当前时间
                .setCreatedTime(LocalDateTime.now())
                // 补全Answer对象的属性值：statusOfAccept<<<	0
                .setStatusOfAccept(0);
        // 调用answerMapper.insert(Answer answer)方法插入“答案”数据，获取返回值
        int rows = answerMapper.insert(answer);
        // 判断返回结果是否不为1
        if (rows != 1) {
            // 是：抛出InsertException
            throw new InsertException("发表答案失败！服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public List<AnswerVO> getAnswerList(Integer questionId) {
        List<AnswerVO> list=answerMapper.findByQuestionId(questionId);
        return list;
    }

}
