package com.sahil.mtours.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {
	public static String excepCountGlobal=null;
	public static boolean validateExceptionCount(String jsonUrl, String ruleName, String exceptionCount, String buyername) throws IOException,
            MalformedURLException {
      System.out.println(jsonUrl);
     // InputStream is = new URL(jsonUrl).openStream();
      String jsonText = null;
      try {
            /*BufferedReader rd = new BufferedReader(new InputStreamReader(is,
                        Charset.forName("UTF-8")));
            String jsonText = readAll(rd);*/
            
    	  jsonText =  postJson(jsonUrl, buyername);
            
            
            HashMap<String, String> levels = new HashMap();
            HashMap<String, String> rules = new HashMap();
            try {

                  JSONObject json = new JSONObject(jsonText);
                  JSONArray levelList = json.getJSONArray("LevelOnes");
                  for (int i = 0; i < levelList.length(); i++) {
                	  JSONObject firstLevel = (JSONObject)levelList.get(i);
                	 
                	  	String level1Name = firstLevel.getString("Level"+(i+1)+"_name");
                		 String excepCount = firstLevel.getString("Total_Exception_Count");
                		 excepCountGlobal=excepCount;
                		if(level1Name.equalsIgnoreCase(ruleName)){
                			if(excepCount.equalsIgnoreCase(exceptionCount)){
                				return true;
                			}
                			return false;
                		}
                		
                	  	levels.put(level1Name, excepCount);
                        JSONArray ruleDetails =  firstLevel.getJSONArray("Rules");
                        System.out.println(ruleDetails.length());
                        for(int ruleCount=0; ruleCount < ruleDetails.length(); ruleCount++){
                        	JSONObject ruleObject = ruleDetails.getJSONObject(i);
                        	 String rule = ruleObject.getString("Rule");
                        
                        	 excepCount = ruleObject.getString("Total_Exception_Count");
                        	 System.out.println("Actual:-->"+excepCount);
                        	 if(rule.equalsIgnoreCase(ruleName)){
                     			if(excepCount.equalsIgnoreCase(exceptionCount)){
                     				return true;
                     			}
                     			return false;
                     		}
                        	 
                        	 rules.put(rule, excepCount);
                        }
                       

                  }
                 
                 
            } catch (JSONException e) {

                  e.printStackTrace();
                  return false;
            } 

      } catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
         //   is.close();
      }
      return false;
}


public static String readAll(BufferedReader rd) throws IOException {
      StringBuilder sb = new StringBuilder();
      int cp;
      while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
      }
      return sb.toString();
}

public static String postJson (String url, String buyer) throws IOException, JSONException {
    StringBuilder sb = new StringBuilder();
    int cp;
    JSONObject json = new JSONObject();
    json.put("buyer", "SMALLKITCHENAPP.");    
    json.put("isCatDir", "false");  
    String data = "{\"buyer\":\""+buyer+"\",\"isCatDir\":false,\"filter\":{\"favorite\":0,\"ignore\":0,\"online\":0,\"kvi\":0}}";
    json.put("filter", data);  

	


HttpClient client = new DefaultHttpClient();
    byte[] response =null;
    StringBuffer result = null;

    try {
        HttpPost post = new HttpPost(url);
        //post.setHeader("Referer",url);
        //post.setHeader("Authorization", "Basic (with a username and password)");
        post.setHeader("Content-type", "application/json");
        StringEntity params = new StringEntity(data);
        params.setContentEncoding("UTF-8");
        params.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        //post.setEntity(se);

       // request.addHeader("content-type", "application/json");
        post.setEntity(params);
        HttpResponse response1 = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));

        result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result);
       
    // handle response here...
    } catch (Exception ex) {
        // handle exception here
    } finally {
    	//client.close();
    }
    return result.toString();
}


}
