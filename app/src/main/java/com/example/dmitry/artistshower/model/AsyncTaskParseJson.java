package com.example.dmitry.artistshower.model;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dmitry on 24.04.16.
 */

public class AsyncTaskParseJson extends AsyncTask<String, String, String> {

    final String tag = "AsyncTaskParseJson.java";

    //TODO
    // set your json string url here
    String yourJsonStringUrl = "http://download.cdn.yandex.net/mobilization-2016/artists.json";
    //String yourJsonStringUrl = "http://demo.codeofaninja.com/tutorials/json-example-with-php/index.php";
    //String yourJsonStringUrl = "http://cache-default05h.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/artists.json";

    // contacts JSONArray
    JSONArray dataJsonArr = null;

    @Override
    protected void onPreExecute() {}

    @Override
    protected String doInBackground(String... arg0) {
        try {

            // instantiate our json parser
            JsonParser jParser = new JsonParser();

            // get json string from url
            JSONArray json = jParser.getJSONFromUrl(yourJsonStringUrl);
            Log.d(tag, json == null ? "yes :("  : "no :)");
            // get the array of users
            dataJsonArr = json;

            // loop through all users
            for (int i = 0; i < dataJsonArr.length(); i++) {

                JSONObject c = dataJsonArr.getJSONObject(i);

                // Storing each json item in variable
                String name = c.getString("name");
                String tracks = c.getString("tracks");
                String albums = c.getString("albums");

                // show the values in our logcat
                Log.e(tag, "name: " + name
                        + ", tracks: " + tracks
                        + ", albums: " + albums);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String strFromDoInBg) {}
}
