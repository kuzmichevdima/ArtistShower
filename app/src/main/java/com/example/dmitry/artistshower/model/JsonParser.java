package com.example.dmitry.artistshower.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class JsonParser {
    static final String mTag = "JsonParser";

    public JSONArray getJSONFromUrl(String urlSource) {
        //HTTP запрос
        InputStream inputStream = null;
        try {
            URL url = new URL(urlSource);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
        } catch (UnsupportedEncodingException e) {
            Log.e(mTag, "неправильная кодировка");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(mTag, "ошибка ввода/вывода (IO)");
            e.printStackTrace();
        }

        //с помощью BufferedReader считываем из inputStream
        String json = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            inputStream.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e(mTag, "ошибка при чтении потока (неправильно указано кодировка?)");
            e.printStackTrace();
        }

        //преобразуем полученную строку в json
        JSONArray jObj = null;
        try {
            jObj = new JSONArray(json);
        } catch (JSONException e) {
            Log.e(mTag, "ошибка преобразования в JSONArray полученной строки");
            e.printStackTrace();
        }

        return jObj;
    }
}
