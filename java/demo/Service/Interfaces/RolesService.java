package demo.service.interfaces;

import demo.model.Roles;

import java.util.List;

public interface RolesService {

    public void save(Roles role);

    public void saveOrUpdateIfExist(Roles role);

    public List<Roles> selectAllRoles();

    public Roles getRoleByName(String name);

    public void delete(Roles role);
}
