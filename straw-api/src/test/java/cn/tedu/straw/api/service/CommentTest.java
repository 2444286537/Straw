package cn.tedu.straw.api.service;

import cn.tedu.straw.api.DTO.PostCommentDTO;
import cn.tedu.straw.commons.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CommentTest {
    @Autowired
    private ICommentService commentService;

    @Test
    public void a(){
        PostCommentDTO postCommentDTO=new PostCommentDTO()
                .setAnswerId(4)
                .setContent("你说的不对");
        Integer userId=5;
        String userNickName="王克晶";
        Comment comment=commentService.post(postCommentDTO,userId,userNickName);
        log.debug("Comment>>>{}",comment);
    }
}
