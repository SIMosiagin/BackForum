package demo.Service;

import demo.Service.Interfaces.UserService;
import demo.model.MyUser;
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
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Users myUser = userService.getUserByNickName(name);
        return new User(myUser.getNickName(),myUser.getPass(), AuthorityUtils.createAuthorityList(myUser.getRole().getRoleName()));
    }

    public MyUser loadMyUserByUsername(String name){
        Users user = userService.getUserByNickName(name);

        MyUser u2 =  new MyUser();
        u2.setPass(user.getPass());
        u2.setName(user.getNickName());
        u2.setRole("ROLE_USER");


        return u2;
    }
}
