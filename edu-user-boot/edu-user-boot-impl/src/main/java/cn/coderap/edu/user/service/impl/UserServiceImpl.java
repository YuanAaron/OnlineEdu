package cn.coderap.edu.user.service.impl;

import cn.coderap.edu.user.entity.User;
import cn.coderap.edu.user.mapper.UserMapper;
import cn.coderap.edu.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oshacker
 * @since 2022-03-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
