package com.example.dmitry.artistshower.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonParser {
    final String tag = "JsonParser.java";
    static InputStream inputStream = null;
    static JSONArray jObj = null;
    static String json = "";

    public JSONArray getJSONFromUrl(String urlSource) {
        //make HTTP request
        try {
            URL url = new URL(urlSource);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(tag, inputStream.toString());
        //Read JSON data from inputStream
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                Log.d(tag, "line = " + line);
                sb.append(line + "\n");
            }
            inputStream.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e(tag, "Error converting result " + e.toString());
        }

        Log.d(tag, "json = " + json);
        // try parse the string to a JSON object
        try {
            jObj = new JSONArray(json);
        } catch (JSONException e) {
            String str = e.toString();
            Log.e(tag, "(Error parsing data) " + str.substring(str.length() - 100));
        }

        return jObj;
    }
}
