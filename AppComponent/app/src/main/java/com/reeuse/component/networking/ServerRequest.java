package com.reeuse.component.networking;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * ServerRequest.java
 */
public class ServerRequest {
    public static final String TAG = ServerRequest.class.getSimpleName();

    /**
     * To send the get request.
     * @param requestURL
     * @return server Response
     */
    public String httpGet(String requestURL) {
        String response = "";
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(requestURL); // URL.java.net;
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json"); // optional value
            urlConnection.setRequestProperty("Accept", "application/json");// optional value
            urlConnection.setRequestMethod("GET"); // HTTP GET Method
            int statusCode = urlConnection.getResponseCode();
            if (statusCode ==HttpURLConnection.HTTP_OK) { //success code 200
                String line;
                //To read the response from the urlConnection input stream
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else
                Log.d(TAG, "Status code: " + statusCode);
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }finally {
            if(urlConnection!=null)
          urlConnection.disconnect();;// disconnect the urlConnection
        }
        return response;
    }


    /**
     * To
     * @param requestURL
     * @param postDataParams
     * @return
     */
    public String httpPost(String requestURL, HashMap<String, String> postDataParams) {
        String response = "";
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(requestURL); // URL.java.net;
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST"); // HTTP POST Method
            urlConnection.setDoInput(true); // Allow input
            urlConnection.setDoOutput(true);// Allow output
            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(getPostDataString(postDataParams));// write the post data values in the output stream.
            writer.flush();
            writer.close();
            outputStream.close();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {//success code 200
                String line;
                //To read the response from the urlConnection input stream
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            }else
                Log.d(TAG, "Status code: " + statusCode);
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }finally {
            if(urlConnection!=null)
            urlConnection.disconnect();// disconnect the urlConnection
        }
        return response;
    }

    /**
     * To structure the post data to server.
     *
     * @param params HashMap<String, String> post data key and values.
     * @return String post data in structure format.
     * @throws UnsupportedEncodingException
     */
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }


}
