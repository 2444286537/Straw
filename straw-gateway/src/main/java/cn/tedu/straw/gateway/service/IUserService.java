package cn.tedu.straw.gateway.service;


import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.gateway.vo.UserLoginVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
public interface IUserService extends IService<User> {



    UserLoginVO getUserLoginDetails(String username);

}
