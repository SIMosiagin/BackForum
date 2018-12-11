package demo.service.implementation;

import demo.dao.interfaces.PostInterfaceDao;
import demo.model.Post;
import demo.service.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service(value = "PostService")
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    PostInterfaceDao postInterfaceDao;

    public void save(Post post) {
        postInterfaceDao.save(post);
    }

    public void saveOrUpdateIfExist(Post post) {
        postInterfaceDao.saveOrUpdateIfExist(post);
    }

    public List<Post> selectAllPosts() {
        return postInterfaceDao.selectAllPosts();
    }

    public List<Post> getPostsByTopic(Integer topicId) {
        return postInterfaceDao.getPostsByTopic(topicId);
    }

    public Post getPostById(Integer id) {
        return postInterfaceDao.getPostById(id);
    }

    public void delete(Post post) {
        postInterfaceDao.delete(post);
    }
}
