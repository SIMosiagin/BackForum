package demo.controller;


import demo.model.Post;
import demo.model.Topic;
import demo.service.interfaces.PostService;
import demo.service.interfaces.RolesService;
import demo.service.interfaces.TopicService;
import demo.service.interfaces.UserService;
import demo.model.Roles;
import demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class Rest {

    @Autowired
    UserService userService;

    @Autowired
    RolesService rolesService;

    @Autowired
    PostService postService;

    @Autowired
    TopicService topicService;


    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public Users signUpNewUser(@RequestBody Users user){
        userService.saveOrUpdateIfExist(user);
        return user;
    }

    @PreAuthorize("hasAnyRole('USER')")
    @RequestMapping(value = "/forum/getAllTopic", method = RequestMethod.GET)
    public List<Topic> getAllTopic (){
        return topicService.selectAllPosts();
    }

    @RequestMapping(value = "/forum/getPostsByTopic", method = RequestMethod.POST)
    public List<Post> getPostsByTopic(@RequestBody  Topic topic){
        return postService.getPostsByTopic(topic.getId());
    }

    @RequestMapping(value = "/forum/saveTopic", method = RequestMethod.PUT)
    public List<Topic> saveTopic (@RequestBody Topic topic) {
        topicService.saveOrUpdateIfExist(topic);
        return topicService.selectAllPosts();
    }

    @RequestMapping(value = "/forum/savePost", method = RequestMethod.PUT)
    public List<Post> savePost (@RequestBody Post post) {
        postService.saveOrUpdateIfExist(post);
        return postService.getPostsByTopic(post.getTopic().getId());
    }

    @RequestMapping(value = "/forum/editUser", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<Users> editUser(@RequestBody Users user){
        userService.saveOrUpdateIfExist(user);
        return userService.selectAllUser();
    }

    @RequestMapping(value = "/forum/getAllUsers", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<Users> getUsers(){
        return userService.selectAllUser();
    }

    @RequestMapping(value = "/forum/getUser", method = RequestMethod.POST)
    public Users getUser(@RequestBody String nickName){
        return userService.getUserByNickName(nickName);
    }




}
