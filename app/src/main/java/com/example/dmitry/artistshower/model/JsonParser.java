package com.example.dmitry.artistshower.model;

import android.content.Context;
import android.widget.Toast;

import com.example.dmitry.artistshower.presenter.IMainActivityPresenter;

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

import javax.inject.Inject;

public final class JsonParser {
    private Context mContext;

    public JsonParser(Context context) {
        mContext = context;
    }

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
            Toast.makeText(mContext, "неправильная кодировка", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(mContext, "ошибка ввода/вывода (IO)", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        //с помощью BufferedReader считываем из inputStream
        String json = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            inputStream.close();
            json = sb.toString();
        } catch (Exception e) {
            Toast.makeText(mContext, "ошибка при чтении потока (неправильно указано кодировка?)", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        //преобразуем полученную строку в json
        JSONArray jObj = null;
        try {
            jObj = new JSONArray(json);
        } catch (JSONException e) {
            Toast.makeText(mContext, "ошибка преобразования в JSONArray полученной строки", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        return jObj;
    }
}
