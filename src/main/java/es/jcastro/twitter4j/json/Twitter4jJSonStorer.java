package es.jcastro.twitter4j.json;

import java.util.HashMap;
import java.util.Map;

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

    public void addObjectJSon(Object tweet, String json){
        jsonByTweet.put(tweet,json);
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
