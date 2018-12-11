package demo.Service.Implementation;


import demo.Dao.Interfaces.UserInterfaceDao;
import demo.Service.Interfaces.UserService;
import demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service(value = "UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserInterfaceDao userInterfaceDao;

    @Autowired
    BCryptPasswordEncoder encoder;

    public void save(Users user) {
        user.setPass(encoder.encode(user.getPass()));
        userInterfaceDao.save(user);
    }

    public void saveOrUpdateIfExist(Users user) {
        user.setPass(encoder.encode(user.getPass()));
        userInterfaceDao.saveOrUpdateIfExist(user);
    }

    public List<Users> selectAllUser() {
        return userInterfaceDao.selectAllUser();
    }

    public Users getUserByNickName(String nickName) {
        return userInterfaceDao.getUserByNickName(nickName);
    }

    public void delete(Users user) {
        userInterfaceDao.delete(user);
    }


}
