package cn.tedu.straw.api.service;

import cn.tedu.straw.api.DTO.StudentRegisterDTO;
import cn.tedu.straw.api.vo.UserLoginVO;
import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
public interface IUserService extends IService<User> {
    /**
     * 学生注册   ctrl+alt+l排版   ctrl+alt+b 接口跳转实现类
     *
     * @param studentRegisterDTO 学生通过客户端提交的注册信息
     */
    void regStudent(StudentRegisterDTO studentRegisterDTO);

    List<TeacherSelectOptionVO> getTeacherSelectOptionList();

}
