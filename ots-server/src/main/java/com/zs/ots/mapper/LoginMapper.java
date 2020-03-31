package com.zs.ots.mapper;

import com.zs.ots.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author 张帅
 * @date 2020/2/8 12:41
 * @description 这里不需要加@mapper注解，因为我们在主配置类上加了扫描
 */
@Repository
public interface LoginMapper {

    /**
     * 验证用户信息
     *
     * @param user
     * @return
     */
    User verifyUser(User user);


}
