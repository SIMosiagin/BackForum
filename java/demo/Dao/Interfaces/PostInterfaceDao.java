package demo.dao.interfaces;


import demo.model.Post;

import java.util.List;

public interface PostInterfaceDao {

    public void save(Post post);

    public void saveOrUpdateIfExist(Post post);

    public List<Post> selectAllPosts();

    public List<Post> getPostsByTopic(Integer topicId);

    public Post getPostById(Integer id);

    public void delete(Post post);
}
