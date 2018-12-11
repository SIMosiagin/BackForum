package demo.service;

import demo.service.interfaces.RolesService;
import demo.service.interfaces.UserService;
import demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    RolesService rolesService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Users myUser = userService.getUserByNickName(name);
        return new User(myUser.getNickName(),myUser.getPass(), AuthorityUtils.createAuthorityList(myUser.getRole().getRoleName()));
    }



    public Users loadMyUserByUsername(String name){
        return userService.getUserByNickName(name);
    }
}
