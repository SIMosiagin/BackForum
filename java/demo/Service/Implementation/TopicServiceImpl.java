package demo.service.implementation;

import demo.dao.interfaces.TopicInterfaceDao;
import demo.model.Topic;
import demo.service.interfaces.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service(value = "TopicService")
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicInterfaceDao topicInterfaceDao;


    public void save(Topic topic) {
        topicInterfaceDao.save(topic);
    }

    public void saveOrUpdateIfExist(Topic topic) {
        topicInterfaceDao.saveOrUpdateIfExist(topic);
    }

    public List<Topic> selectAllPosts() {
        return topicInterfaceDao.selectAllPosts();
    }

    public Topic getTopicsById(Integer id) {
        return topicInterfaceDao.getTopicsById(id);
    }

    public void delete(Topic topic) {
        topicInterfaceDao.delete(topic);
    }
}
