package cn.coderap.edu.remote;

import cn.coderap.edu.dto.UserDTO;
import cn.coderap.edu.param.UserQueryParam;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "edu-user-boot",path = "/user")
public interface UserRemoteService {

    /*----------------------------管理后台接口------------------*/
    @PostMapping("/getUsersPage")
    Page<UserDTO> getUsersPage(@RequestBody UserQueryParam userQueryParam);

    @PostMapping("/forbidUser")
    boolean forbidUser(@RequestParam("userId") Integer userId);

    /*----------------------------门户后台接口----------------*/
    @GetMapping("/getUserById")
    UserDTO getUserById(@RequestParam("userId") Integer userId);

    @GetMapping("/getUserByPhone")
    UserDTO getUserByPhone(@RequestParam("phone") String phone);

    @GetMapping("/isRegister")
    boolean isRegister(@RequestParam("phone") String phone);

    @PostMapping("/saveUser")
    UserDTO saveUser(@RequestBody UserDTO userDTO);

    @PostMapping("/updateUser")
    boolean updateUser(@RequestBody UserDTO userDTO);

    @GetMapping("/isUpdatedPassword")
    boolean isUpdatedPassword(@RequestParam("userId") Integer userId);

    @PostMapping("/setPassword")
    boolean setPassword(@RequestParam("userId") Integer userId,
                        @RequestParam("password") String password,
                        @RequestParam("confirmPassword") String confirmPassword);

    @PostMapping("/updatePassword")
    boolean updatePassword(@RequestParam("userId") Integer userId,
                           @RequestParam("oldPassword") String oldPassword,
                           @RequestParam("newPassword") String newPassword,
                           @RequestParam("confirmPassword") String confirmPassword);
}
