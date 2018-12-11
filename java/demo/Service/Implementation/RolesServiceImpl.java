package demo.Service.Implementation;


import demo.Dao.Interfaces.RolesInterfaceDao;
import demo.Service.Interfaces.RolesService;
import demo.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service(value = "RolesService")
@Transactional
public class RolesServiceImpl implements RolesService {

    @Autowired
    RolesInterfaceDao rolesInterfaceDao;

    public void save(Roles role) {
        rolesInterfaceDao.save(role);
    }

    public void saveOrUpdateIfExist(Roles role) {
        rolesInterfaceDao.saveOrUpdateIfExist(role);
    }

    public List<Roles> selectAllRoles() {
        return rolesInterfaceDao.selectAllRoles();
    }

    public void delete(Roles role) {
        rolesInterfaceDao.delete(role);
    }
}
