package demo.dao.implementation;


import demo.dao.interfaces.RolesInterfaceDao;
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

    public Roles getRoleByName(String name){
        Roles role = getSession().createQuery("From Roles Where rolename = :name", Roles.class)
                .setParameter("name", name)
                .getSingleResult();
        return role;
    }

    public void delete (Roles role){
        getSession().delete(role);
    }
}
