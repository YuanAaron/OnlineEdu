package cn.coderap.edu.user.controller;


import cn.coderap.edu.dto.UserDTO;
import cn.coderap.edu.param.UserQueryParam;
import cn.coderap.edu.remote.UserRemoteService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author oshacker
 * @since 2022-03-10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRemoteService userRemoteService;

    @PostMapping("/getUsersPage")
    public Page<UserDTO> getUsersPage(@RequestBody UserQueryParam userQueryParam) {
        return userRemoteService.getUsersPage(userQueryParam);
    }

    @GetMapping("/getUserById")
    public UserDTO getUserById(@RequestParam("userId") Integer userId) {
        return userRemoteService.getUserById(userId);
    }

}
