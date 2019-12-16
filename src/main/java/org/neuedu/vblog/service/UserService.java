package org.neuedu.vblog.service;

import org.neuedu.vblog.mapper.UserMapper;
import org.neuedu.vblog.model.RespBean;
import org.neuedu.vblog.model.Role;
import org.neuedu.vblog.model.User;
import org.neuedu.vblog.model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user= userMapper.loadUserByUsername(username);
      if (user == null){
          throw new UsernameNotFoundException("没有此人");
      }
     List<Role> roles= userMapper.getRolesById(user.getId());
      user.setRoles(roles);
        return user;
    }

    public List<UserBean> getAllUser(String nickname) {
        List<UserBean> users=userMapper.getAllUser(nickname);
        for (UserBean user : users) {
            List<Role> roles= userMapper.getRolesById(user.getId());
            user.setRoles(roles);
        }
        return users;
    }

    public RespBean delUserById(UserBean userBean) {
      Integer i = userMapper.delUserById(userBean);
        if (i == 0) {
            return RespBean.error("删除失败");
        } else {
            return RespBean.ok("删除成功");
        }
    }

    public RespBean updateUserById(UserBean userBean) {
        Integer i = userMapper.updateUserById(userBean);
        if (i == 0) {
            return RespBean.error("更新用户失败");
        } else {
            return RespBean.ok("更新用户成功");
        }
    }
}
