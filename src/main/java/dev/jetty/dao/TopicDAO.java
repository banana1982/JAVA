package dev.jetty.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;


import dev.jetty.model.Topic;

public class TopicDAO {
    private static final Map<String, Topic> topicMap = new HashMap<String, Topic>();

    static {
        initTopics();
    }

    public static void initTopics(){
        Topic topic1 = new Topic("1","JAVA","1543289528000","cuonglt2","","");
        Topic topic2 = new Topic("2","C#","1543289528000","cuonglt2","","");
        Topic topic3 = new Topic("3","Python","1543289528000","cuonglt2","","");
        Topic topic4 = new Topic("4","PHP","1543289528000","cuonglt2","","");
        Topic topic5 = new Topic("5","GO","1543289528000","cuonglt2","","");

        topicMap.put(topic1.getId(),topic1);
        topicMap.put(topic2.getId(),topic2);
        topicMap.put(topic3.getId(),topic3);
        topicMap.put(topic4.getId(),topic4);
        topicMap.put(topic5.getId(),topic5);
    }

    public static Topic getTopic(String ID){
        return topicMap.get(ID);
    }

    private int getLastID(){
        Collection<Topic> topicCollection = topicMap.values();
        List<Topic> topicList = new ArrayList<Topic>();
        topicList.addAll(topicCollection);
        int lastID = topicList.size() + 1;
        return lastID;
    }

    public static Topic addTopic(Topic topic){
        TopicDAO dao = new TopicDAO();
        Long time_current = System.currentTimeMillis();
        String current = time_current.toString();
        String id = Integer.toString(dao.getLastID());
        topic.setId(id);
        topic.setCreated_date(current);
        topicMap.put(topic.getId(),topic);
        return topic;
    }

    public static Topic updateTopic(Topic topic){
        Long time_current = System.currentTimeMillis();
        String current = time_current.toString();
        topic.setLastupdated_date(current);
        topicMap.put(topic.getId(),topic);
        return topic;
    }

    public static void deleteTopic(String ID){
        topicMap.remove(ID);
    }

    public static List<Topic> getAllTopics(){
        Collection<Topic> topicCollection = topicMap.values();
        List<Topic> topicList = new ArrayList<Topic>();
        topicList.addAll(topicCollection);
        return topicList;
    }

    List<Topic> topicList;
}
