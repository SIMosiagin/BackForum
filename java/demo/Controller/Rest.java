package demo.controller;


import demo.service.interfaces.PostService;
import demo.service.interfaces.RolesService;
import demo.service.interfaces.TopicService;
import demo.service.interfaces.UserService;
import demo.model.Roles;
import demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rest {

    @Autowired
    UserService userService;

    @Autowired
    RolesService rolesService;

    @Autowired
    PostService postService;

    @Autowired
    TopicService topicService;

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
        users.setNickName("zxczxc");
        users.setPass("asdas");

        Roles roles = rolesService.getRoleByName("ROLE_USER");

        users.setRole(roles);
        userService.saveOrUpdateIfExist(users);
        System.out.println("done user");

    }

    
}
