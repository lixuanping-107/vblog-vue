package org.neuedu.vblog.controller.user;

import org.neuedu.vblog.model.RespBean;
import org.neuedu.vblog.model.User;
import org.neuedu.vblog.model.UserBean;
import org.neuedu.vblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/getAllUser")
    public List<UserBean> getAllUser(@RequestParam(value = "nickname",defaultValue = "") String nickname){
        return userService.getAllUser(nickname);
    }

    @DeleteMapping("/delUser")
    public RespBean delUserById(@RequestBody UserBean userBean){
        System.out.println(userBean.getId());
        return userService.delUserById(userBean);
    }

    @PutMapping("/updateUser")
    public RespBean updateUser(@RequestBody UserBean userBean){
        return userService.updateUserById(userBean);
    }

}
