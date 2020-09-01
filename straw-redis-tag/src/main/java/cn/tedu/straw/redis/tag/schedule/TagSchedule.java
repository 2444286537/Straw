package cn.tedu.straw.redis.tag.schedule;

import cn.tedu.straw.commons.vo.TagVO;
import cn.tedu.straw.redis.tag.service.ITagService;
import cn.tedu.straw.redis.tag.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TagSchedule {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private ITagService tagService;
    @Autowired
    private RedisUtils redisUtils;
    //计划任务方法
    //应该是public权限   返回类型是void
    @Scheduled(fixedRate = 10*60*1000)
    public void loadTags(){


        //向Redis中存入的数据key
        String key="tags";
        //清除Redis服务器中名为tags的数据   避免反复向List类型的数据中添加重复的数据
        redisUtils.delete(key);
        //从数据库中查询tag列表
        List<TagVO> tags=tagService.getTagList();
        //获取惭怍Redis中List类型数据的操作器
       /* ListOperations<String,Object> ops=redisTemplate.opsForList();*/
        //将标签数据保存到Redis中
        for (TagVO tag:tags
             ) {
            redisUtils.rightPushListItem(key,tag);
            redisUtils.set("tag:"+tag.getId(),tag);
        }

    }
}
