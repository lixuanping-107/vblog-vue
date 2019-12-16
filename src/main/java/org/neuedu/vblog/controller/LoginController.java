package org.neuedu.vblog.controller;



import org.neuedu.vblog.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("没有登录，请先登录");
    }
}
