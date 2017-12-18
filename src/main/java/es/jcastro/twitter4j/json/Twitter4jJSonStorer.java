package es.jcastro.twitter4j.json;

import java.util.*;

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

    private Set<Object> isRequested = new HashSet<>();
    private int queueLimit = 100000;

    public synchronized void setQueueLimit(int queueLimit){
        this.queueLimit = queueLimit;
    }

    public synchronized void addObjectJSon(Object tweet, String json){
        jsonByTweet.put(tweet,json);
        queue.add(tweet);

        while(queue.size() > queueLimit){
            Object tweetToRemove = queue.remove();
            boolean isTweetRequested = isRequested.contains(tweetToRemove);

            if(isTweetRequested) {
                isRequested.remove(tweetToRemove);
                deleteJSonOf(tweetToRemove);
            }else{
                queue.add(tweetToRemove);
            }
        }
    }

    public synchronized String getJSonOf(Object object){
        if (jsonByTweet.containsKey(object)) {
            isRequested.add(object);
            return jsonByTweet.get(object);
        } else {
            throw new IllegalArgumentException("Object '" + object + "' not found");
        }
    }

    public synchronized void deleteJSonOf(Object object){
        if (jsonByTweet.containsKey(object)) {
            jsonByTweet.remove(object);
        } else {
            throw new IllegalArgumentException("Object '" + object + "' not found");
        }
    }

    public synchronized boolean contains(Object object){
        return jsonByTweet.containsKey(object);
    }
}
