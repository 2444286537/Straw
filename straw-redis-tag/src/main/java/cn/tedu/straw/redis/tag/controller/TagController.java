package cn.tedu.straw.redis.tag.controller;

import cn.tedu.straw.commons.vo.R;
import cn.tedu.straw.commons.vo.TagVO;
import cn.tedu.straw.redis.tag.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/tags")
public class TagController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtils redisUtils;
    @GetMapping("")
    public R<List<Object>> a(){
        String key="tags";
       /* ListOperations<String,Object> ops=redisTemplate.opsForList();
        List<Object> list=ops.range(key,0,ops.size(key));
*/
        List<Object> list=redisUtils.getList(key);
        R<List<Object>> r=new R<>();
        r.setState(R.State.SUCCESS);
        r.setData(list);
        return r;
    }

    @GetMapping("/{id}")
    public R<Object> getTag(@PathVariable("id") Integer id){
        Object obj=redisUtils.get("tag:"+id);
        return R.ok(obj);
    }
    @GetMapping("/{id}/simple")
    public Object getsimpleTag(@PathVariable("id") Integer id){
        return redisUtils.get("tag:"+id);
    }
}
