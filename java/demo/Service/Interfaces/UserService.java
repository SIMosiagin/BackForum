package demo.Service.Interfaces;


import demo.model.Users;

import java.util.List;

public interface UserService {

    public void save(Users user);

    public void saveOrUpdateIfExist(Users user);

    public List<Users> selectAllUser();

    public Users getUserByNickName(String nickName);

    public void delete(Users user);

}
