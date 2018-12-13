package demo.dao.implementation;

import demo.dao.interfaces.PostInterfaceDao;
import demo.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PostInterfaceDao")
public class PostDao extends AbstractDao implements PostInterfaceDao {

    public void save(Post post){
        persist(post);
    }

    public void saveOrUpdateIfExist(Post post){
        saveOrUpdate(post);
    }

    public List<Post> selectAllPosts (){
        List<Post> posts = getSession().createQuery("From Post").list();
        return  posts;
    }

    public List<Post> getPostsByTopic (Integer topicId){
        List<Post> posts = getSession().createQuery("From Post Where topic_id = :id")
                .setParameter("id", topicId).list();
        return  posts;
    }

    public Post getPostById(Integer id){
        Post post = getSession().get(Post.class, id);
        return post;
    }

    public void delete (Post post){
        getSession().delete(post);
    }

}
