package demo.Controller;


import demo.Service.Interfaces.RolesService;
import demo.Service.Interfaces.UserService;
import demo.model.Roles;
import demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/floor1")
public class Rest {

    @Autowired
    UserService userService;

    @Autowired
    RolesService rolesService;

    @GetMapping("office1")
    public String enterOfficeOne (){
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return ("you are inside enterOfficeOne");
    }

    @GetMapping("office2")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String enterOfficeTwo (){
        return ("you are inside enterOfficeTwo");
    }

    @RequestMapping(name = "/testOne", method = RequestMethod.GET)
    public void doTest(){
        Users users = new Users();
        users.setNickName("someUser");
        users.setPass("pass");
        users.setId(1);

        Roles roles = new Roles();
        roles.setRoleName("ROLE_USER");
        roles.setId(1);
        rolesService.saveOrUpdateIfExist(roles);

        users.setRole(roles);
        userService.saveOrUpdateIfExist(users);
        System.out.println("done user");

    }
}
