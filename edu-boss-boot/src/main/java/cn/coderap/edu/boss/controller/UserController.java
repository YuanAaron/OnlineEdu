package cn.coderap.edu.boss.controller;

import cn.coderap.edu.dto.UserDTO;
import cn.coderap.edu.param.UserQueryParam;
import cn.coderap.edu.remote.UserRemoteService;
import cn.coderap.edu.response.ResponseDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRemoteService userRemoteService;

    @PostMapping("/getUsersPage")
    public ResponseDTO getUsersPage(@RequestBody UserQueryParam userQueryParam) {
        Integer currentPage = userQueryParam.getCurrentPage();
        Integer pageSize = userQueryParam.getPageSize();
        if (currentPage == null || currentPage <= 0) {
            userQueryParam.setCurrentPage(1);
        }
        if (pageSize == null || pageSize <= 0) {
            userQueryParam.setPageSize(10);
        }
        try {
            Page<UserDTO> userDTOPage = userRemoteService.getUsersPage(userQueryParam);
            return ResponseDTO.success(userDTOPage);
        } catch (Exception e) {
            return ResponseDTO.ofError(e.getMessage());
        }
    }

    @GetMapping("/forbidUser")
    public ResponseDTO forbidUser(@RequestParam("userId") Integer userId) {
        try {
            boolean result = userRemoteService.forbidUser(userId);
            return ResponseDTO.success();
        } catch (Exception e) {
            return ResponseDTO.ofError(e.getMessage());
        }
    }
}
