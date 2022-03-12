package cn.coderap.edu.user.remote;

import cn.coderap.edu.dto.UserDTO;
import cn.coderap.edu.param.UserQueryParam;
import cn.coderap.edu.remote.UserRemoteService;
import cn.coderap.edu.user.entity.User;
import cn.coderap.edu.user.service.IUserService;
import cn.coderap.edu.util.ConvertUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserRemoteServiceImpl implements UserRemoteService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private IUserService userService;

    @Override
    public Page<UserDTO> getUsersPage(UserQueryParam userQueryParam) {
        Page<User> page = new Page<>(userQueryParam.getCurrentPage(),userQueryParam.getPageSize());

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Integer userId = userQueryParam.getUserId();
        String phone = userQueryParam.getPhone();
        Date startCreateTime = userQueryParam.getStartCreateTime();
        Date endCreateTime = userQueryParam.getEndCreateTime();
        if (userId != null && userId > 0) {
            queryWrapper.eq("id", userId);
        }
        if (StringUtils.isNotBlank(phone)) {
            queryWrapper.like("phone", phone);
        }
        if (startCreateTime !=null && endCreateTime != null) {
            queryWrapper.ge("createTime", startCreateTime);
            queryWrapper.le("createTime", endCreateTime);
        }

        int total = userService.count(queryWrapper);

        queryWrapper.orderByDesc("id");
        Page<User> selectPage = userService.getBaseMapper().selectPage(page, queryWrapper);

        Page<UserDTO> res = new Page<>();
        ConvertUtil.convert(selectPage, res);
        res.setRecords(ConvertUtil.convertList(selectPage.getRecords(), UserDTO.class));
        res.setTotal(total);
        return res;
    }

    @Override
    public boolean forbidUser(Integer userId) {
        return false;
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = userService.getById(userId);
        return ConvertUtil.convert(user, UserDTO.class);
    }

    @Override
    public UserDTO getUserByPhone(String phone) {
        List<User> list = userService.lambdaQuery().eq(User::getPhone, phone).orderByDesc(User::getId).list();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return ConvertUtil.convert(list.get(0), UserDTO.class);
    }

    @Override
    public boolean isRegister(String phone) {
        UserDTO userByPhone = getUserByPhone(phone);
        return userByPhone != null && Boolean.FALSE.equals(userByPhone.getIsDel());
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = ConvertUtil.convert(userDTO, User.class);
        // 密码加密保存
        user.setPassword(encoder.encode(user.getPassword()));
        // 重新设置用户昵称
        if (StringUtils.isNotBlank(user.getPhone())) {
            String phone = user.getPhone();
            user.setName("用户" + phone.substring(phone.length()-4));
        }
        userService.save(user);
        log.info("用户[{}]保存成功", user);
        return ConvertUtil.convert(user, UserDTO.class);
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        if (userDTO.getId() == null || userDTO.getId() <= 0) {
            log.info("用户id为空，无法更新");
            return false;
        }
        User user = ConvertUtil.convert(userDTO, User.class);
        user.setUpdateTime(new Date());
        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        userService.updateById(user);
        log.info("用户[{}]更新成功", user);
        return true;
    }

    @Override
    public boolean isUpdatedPassword(Integer userId) {
        User user = userService.getById(userId);
        if (user == null) {
            return false;
        }
        boolean matches = encoder.matches(user.getPhone(), user.getPassword());
        log.info("用户[{}]是否有修改过初始密码[{}]", userId, matches);
        return true;
    }

    @Override
    public boolean setPassword(Integer userId, String password, String confirmPassword) {
        User user = this.userService.getById(userId);
        if (user == null) {
            return false;
        }
        if (!StringUtils.equals(password, confirmPassword)) {
            return false;
        }
        user.setPassword(encoder.encode(password));
        userService.updateById(user);
        log.info("用户[{}]设置密码成功", userId);
        return true;
    }

    @Override
    public boolean updatePassword(Integer userId, String oldPassword, String newPassword, String confirmPassword) {
        User user = this.userService.getById(userId);
        if (user == null) {
            return false;
        }
        if (!StringUtils.equals(newPassword, confirmPassword)) {
            return false;
        }
        if (!encoder.matches(oldPassword, user.getPassword())) {
            log.info("用户[{}]旧密码错误", userId);
            return false;
        }
        user.setPassword(encoder.encode(newPassword));
        this.userService.updateById(user);
        log.info("用户[{}]更新密码成功", userId);
        return true;
    }
}
