package cn.tedu.straw.api.service.impl;


import cn.tedu.straw.api.DTO.PostCommentDTO;
import cn.tedu.straw.api.ex.AccessDeniedException;
import cn.tedu.straw.api.ex.CommentNotFoundException;
import cn.tedu.straw.api.ex.DeleteExcetion;
import cn.tedu.straw.api.ex.InsertException;
import cn.tedu.straw.api.mapper.CommentMapper;
import cn.tedu.straw.api.service.ICommentService;
import cn.tedu.straw.commons.model.Comment;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Comment post(PostCommentDTO postCommentDTO, Integer userId, String userNickName) {
        Comment comment=new Comment()
                .setAnswerId(postCommentDTO.getAnswerId())
                .setContent(postCommentDTO.getContent())
                .setUserId(userId)
                .setUserNickName(userNickName)
                .setCreatedTime(LocalDateTime.now());
            int rows=commentMapper.insert(comment);
            if(rows!=1){
                throw new InsertException("天见评论失败");
            }
            return comment;
    }

    @Override
    public Comment delete(Integer commentId, Integer userId) {
        // 基于参数commentId调用commentMapper.selectById()查询数据
        Comment comment=commentMapper.selectById(commentId);
        // 判断查询结果是否为null
        if(comment==null){
            throw new CommentNotFoundException("未找到评论，删除失败");
        }
        // 是：抛出CommentNotFoundException

        // 判断查询结果中的userId与当前登录的用户的id是否不相同（使用equals()对比，不要使用==或!=符号）
        if(comment.getUserId().equals(userId)){
            throw new AccessDeniedException("删除权限不足");
        }
        // 是：抛出AccessDeniedException（注意导包不要出错）

        // 基于参数commentId调用commentMapper.deleteById()删除数据
        Integer rows=commentMapper.deleteById(commentId);
        // 判断删除操作的返回值是否不为1
        if(rows!=1){
            throw new DeleteExcetion("删除失败");
        }
        // 是：抛出DeleteException

        // 返回此前的查询结果
        return comment;
    }
}
