package cn.tedu.straw.api.schedule;


import cn.tedu.straw.api.cache.TeacherCache;
import cn.tedu.straw.api.service.IUserService;
import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TeacherCacheSchedule {
    @Autowired
    private IUserService userService;

    public static final Object LOCK=new Object();
    @Scheduled(fixedRate = 1*60*1000)
    public void a(){
        synchronized (LOCK){
            TeacherCache.getTeachers().clear();
            List<TeacherSelectOptionVO> teachers=userService.getTeacherSelectOptionList();
            TeacherCache.getTeachers().addAll(teachers);
        }

    }
}
