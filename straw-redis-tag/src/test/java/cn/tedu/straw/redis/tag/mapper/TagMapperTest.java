package cn.tedu.straw.redis.tag.mapper;

import cn.tedu.straw.commons.vo.TagVO;
import cn.tedu.straw.redis.tag.service.ITagService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class TagMapperTest {
    @Autowired
    private TagMapper tagMapper;

    @Test
    public void a(){
        List<TagVO> tags=tagMapper.findAll();
        log.debug("tag list size={}",tags.size());
        for (TagVO tag:tags
             ) {
            log.debug(">>> {}",tag);
        }
    }

    @Autowired
    private ITagService tagService;
    @Test

    public void b(){
        List<TagVO> tags=tagService.getTagList();
        log.debug("tag list size={}",tags.size());
        for (TagVO tag:tags
        ) {
            log.debug(">>> {}",tag);
        }
    }
}
