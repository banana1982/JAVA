package dev.jetty.api;

import com.google.gson.Gson;
import dev.jetty.dao.TopicDAO;
import dev.jetty.model.Body;
import dev.jetty.model.Topic;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path("/{api}")
public class API {

    @GET
    @Path("/topics")
    @Produces(MediaType.APPLICATION_JSON)
    public String getListTopicJSON() throws Exception {
        Body body = new Body();
        try {
            List<Topic> topicList = TopicDAO.getAllTopics();
            if (topicList.size() > 0)
            {
                body = new Body(1,1000,"success",topicList,null);
            }
            else
            {
                body = new Body(1,1000,"NO_DATA",topicList,null);
            }

        } catch (Exception ex)
        {
            body = new Body(-1,1001,ex.toString(),null,null);
        }
        return new Gson().toJson(body);
    }

    @POST
    @Path("/topics/create")
    @Produces("application/json")
    public String addTopic(Topic topic) throws Exception{
        Body body;
        try{
            Topic topic_ret = TopicDAO.addTopic(topic);
            if (topic_ret != null)
            {
                body = new Body(1,1000,"success",null,topic_ret);
            }
            else
            {
                body = new Body(1,1000,"Create_failed",null,null);
            }
        }catch (Exception ex) {
            body = new Body(-1,1001,ex.toString(),null,null);
        }
        return new Gson().toJson(body);
    }

    @PUT
    @Path("/topics/update")
    @Produces("application/json")
    public String updateTopic(Topic topic) throws Exception{
        Body body;
        try{
            Topic topic_ret = TopicDAO.updateTopic(topic);
            if (topic_ret != null)
            {
                body = new Body(1,1000,"success",null,topic_ret);
            }
            else
            {
                body = new Body(1,1000,"Update_failed",null,null);
            }
            body = new Body(1,1000,"SC_OK",null,topic_ret);
        }catch (Exception ex) {
            body = new Body(-1,1001,ex.toString(),null,null);
        }
        return new Gson().toJson(body);
    }

    @DELETE
    @Path("/topics/delete/{ID}")
    @Produces("application/json")
    public String deleteTopic(@PathParam("ID") String ID) throws Exception{
        Body body;
        try{
            TopicDAO.deleteTopic(ID);
            body = new Body(1,1000,"success",null,null);
        }catch (Exception ex) {
            body = new Body(-1,1001,ex.toString(),null,null);
        }
        return new Gson().toJson(body);
    }

    @GET
    @Path("/topics/{ID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDetailTopicJSON(@PathParam("ID") String ID) throws Exception{
        Body body;
        try {
            Topic topic = TopicDAO.getTopic(ID);
            if (topic != null) {
                body = new Body(1,1000,"success",null,topic);
            } else
            {
                body = new Body(1,1000,"NO_DATA",null,null);
            }
        }
        catch (Exception ex)
        {
            Topic topic = TopicDAO.getTopic(ID);
            body = new Body(-1,1001,ex.toString(),null,null);

        }
        return new Gson().toJson(body);
    }

    @GET
    @Path("/topics/search")
    @Produces(MediaType.APPLICATION_JSON)
    public String searchTopicJSON(@QueryParam("content") String content, @QueryParam("date") String date, @QueryParam("create_by") String create_by) throws Exception{
        Map<String, Topic> topicTempMap = new HashMap<String, Topic>();
        Body body;
        try {
            List<Topic> topicList = TopicDAO.getAllTopics();
            List<Topic> resultList = new ArrayList<Topic>();
            if (topicList.size() > 0)
            {
                if(content != null || date != null || create_by != null)
                {
                    for (Topic topic: topicList) {
                        String check_content = topic.getContent();
                        String check_createdate = topic.getCreated_date();
                        String check_createby = topic.getCreated_by();
                        if (content != null && check_content.contains(content))
                        {
                            topicTempMap.put(topic.getId(),topic);
                        }
                        if (date != null && check_createdate.contains(date))
                        {
                            topicTempMap.put(topic.getId(),topic);
                        }
                        if (create_by != null && check_createby.contains(create_by))
                        {
                            topicTempMap.put(topic.getId(),topic);
                        }
                    }
                }
                Collection<Topic> topicCollection = topicTempMap.values();
                resultList.addAll(topicCollection);
                if (resultList.size() > 0)
                {
                    body = new Body(1,1000,"success",resultList,null);
                }
                else
                {
                    body = new Body(1,1000,"NO_DATA",null,null);
                }
            }
            else
            {
                body = new Body(1,1000,"Search_failed",null,null);
            }
        }
        catch (Exception ex)
        {
            body = new Body(-1,1001,ex.toString(),null,null);

        }
        return new Gson().toJson(body);
    }

}
