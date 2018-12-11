package demo.Dao.Interfaces;

import demo.model.Roles;

import java.util.List;

public interface RolesInterfaceDao {

    public void save(Roles role);

    public void saveOrUpdateIfExist(Roles role);

    public List<Roles> selectAllRoles();

    public void delete(Roles role);
}
