package org.neuedu.vblog.mapper;

import org.neuedu.vblog.model.Role;
import org.neuedu.vblog.model.User;
import org.neuedu.vblog.model.UserBean;

import java.util.List;

public interface UserMapper {
    User loadUserByUsername(String username);

    List<Role> getRolesById(Integer id);

    List<UserBean> getAllUser(String nickname);

    Integer delUserById(UserBean userBean);

    Integer updateUserById(UserBean userBean);
}
