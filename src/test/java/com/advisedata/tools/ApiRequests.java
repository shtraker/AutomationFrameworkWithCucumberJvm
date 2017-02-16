package com.advisedata.tools;


import java.net.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static javafx.css.StyleOrigin.USER_AGENT;

public class ApiRequests extends Tools {

    static ThreadLocal<CookieManager> cookieManager = new ThreadLocal<CookieManager>() {

        /*
         * initialValue() is called
         */
        @Override
        protected CookieManager initialValue() {
            CookieManager cookieManager = new CookieManager();
            return cookieManager;
        }
    };

    static {
        CookieHandler.setDefault(new CookieManager(new ThreadLocalCookieStore(), null));

    }



   static private String sendPostRequest(String urlParameters,String serviceUrl) throws IOException {

       byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
       int postDataLength = postData.length;
       URL url = new URL( serviceUrl);
       HttpURLConnection conn = (HttpURLConnection) url.openConnection();
       conn.setDoOutput(true);
       conn.setInstanceFollowRedirects(true);
       conn.setRequestMethod("POST");
       conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
       conn.setRequestProperty("charset", "utf-8");
       conn.setRequestProperty("Content-Length", Integer.toString( postDataLength ));
       conn.setUseCaches( false );

       DataOutputStream wr = new DataOutputStream( conn.getOutputStream());
       wr.write( postData );
       wr.flush();
       wr.close();

       int responseCode = conn.getResponseCode();
//       System.out.println("\nSending 'POST' request to URL : " + url);
//       System.out.println("Post parameters : " + urlParameters);
//       System.out.println("Response Code : " + responseCode);

       BufferedReader in = new BufferedReader(
               new InputStreamReader(conn.getInputStream()));
       String inputLine;
       StringBuffer response = new StringBuffer();

       while ((inputLine = in.readLine()) != null) {
           response.append(inputLine);
       }
       in.close();

//      System.out.println("output:" + response.toString());

        return response.toString();
   }

}
