package demo.service.interfaces;

import demo.model.Topic;

import java.util.List;

public interface TopicService {

    public void save(Topic topic);

    public void saveOrUpdateIfExist(Topic topic);

    public List<Topic> selectAllPosts();

    public Topic getTopicsById(Integer id);

    public void delete(Topic topic);
}
