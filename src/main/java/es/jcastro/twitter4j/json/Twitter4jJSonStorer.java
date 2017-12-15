package es.jcastro.twitter4j.json;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by jcastro on 24/04/17.
 */
public class Twitter4jJSonStorer {


    private static Twitter4jJSonStorer instance = null;

    public static Twitter4jJSonStorer getInstance(){
        if(instance == null){
            instance = new Twitter4jJSonStorer();
        }

        return instance;
    }

    private Map<Object, String> jsonByTweet = new HashMap<Object, String>();

    private Queue<Object> queue = new LinkedList<>();
    private int queueLimit = 100000;

    public void setQueueLimit(int queueLimit){
        this.queueLimit =queueLimit;
    }

    public void addObjectJSon(Object tweet, String json){
        jsonByTweet.put(tweet,json);
        queue.add(tweet);

        if(queue.size() > queueLimit){
            Object tweetToRemove = queue.remove();
            deleteJSonOf(tweetToRemove);
        }
    }

    public String getJSonOf(Object object){
        if(jsonByTweet.containsKey(object)){
            return jsonByTweet.get(object
            );
        }else{
            throw new IllegalArgumentException("Object '"+object+"' not found");
        }
    }

    public void deleteJSonOf(Object object){
        if(jsonByTweet.containsKey(object)){
             jsonByTweet.remove(object);
        }else{
            throw new IllegalArgumentException("Object '"+object+"' not found");
        }
    }

    public boolean contains(Object object){
        return jsonByTweet.containsKey(object);
    }
}
