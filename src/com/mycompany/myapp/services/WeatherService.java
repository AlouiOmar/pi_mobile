package com.mycompany.myapp.services;

/*
 * Name:     Iuliia Buniak
 *
 * Course:   CS-13, Spring 2019
 *
 * Date:     05/01/19
 *
 * Filename: WxModel.java
 *
 * Purpose:  To handle all data access and manipulation in MVC model 
 *           for weather application which will get the weather by zipcode
 */
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Weather;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class WeatherService{
        public ArrayList<Weather> weathers;

public static WeatherService instance=null;
    public boolean resultOK;
    public ConnectionRequest req;
    final private String appid = "151299208a36b2f534ef585fda4fd673";
    
    
    public static WeatherService getInstance() {
        if (instance == null) {
            instance = new WeatherService();
        }
        return instance;
    }
    
        public Weather afficherWeather(String cityname){
            Weather w = new Weather();
        String url = "http://127.0.0.1:8000/mobile/weather?cityname="+cityname;
        ConnectionRequest req1=new ConnectionRequest();
        req1.setUrl(url);
        req1.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(req1.getResponseData());
                JSONParser j = new JSONParser();
                
                    Map<String, Object> u;
                    try {
                        u = j.parseJSON(new CharArrayReader(json.toCharArray()));

                        w.setCity(u.get("city").toString());
                        w.setWdate(u.get("wdate").toString());
                        w.setTemperature(u.get("temperature").toString());
                        w.setDescription(u.get("description").toString());
                    } 
                    catch (IOException ex) {
                    }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req1);
            
        return w;
    }
}