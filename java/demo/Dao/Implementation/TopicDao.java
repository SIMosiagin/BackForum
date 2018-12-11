package demo.dao.implementation;

import com.SomeSite.Dao.Interfaces.TopicInterfaceDao;
import com.SomeSite.Entity.Topic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TopicInterfaceDao")
public class TopicDao extends  AbstractDao implements TopicInterfaceDao{

    public void save(Topic topic){
        persist(topic);
    }

    public void saveOrUpdateIfExist(Topic topic){
        saveOrUpdate(topic);
    }

    public List<Topic> selectAllPosts (){
        List<Topic> topics = getSession().createQuery("From Topic").list();
        return  topics;
    }

    public Topic getTopicsById(Integer id){
        Topic post = getSession().get(Topic.class, id);
        return post;
    }

    public void delete (Topic topic){
        getSession().delete(topic);
    }
}
