package demo.Dao.Implementation;


import demo.Dao.Interfaces.RolesInterfaceDao;
import demo.model.Roles;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "RolesInterfaceDao")
public class RolesDao extends AbstractDao implements RolesInterfaceDao {


    public void save(Roles role){
        persist(role);
    }

    public void saveOrUpdateIfExist(Roles role){
        saveOrUpdate(role);
    }

    public List<Roles> selectAllRoles (){
        List<Roles> roles = getSession().createQuery("From Roles").list();
        return  roles;
    }

    public void delete (Roles role){
        getSession().delete(role);
    }
}
