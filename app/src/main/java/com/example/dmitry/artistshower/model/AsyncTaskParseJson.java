package com.example.dmitry.artistshower.model;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.dmitry.artistshower.presenter.IMainActivityPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 24.04.16.
 */

public class AsyncTaskParseJson extends AsyncTask<String, String, String> {
    //презентер здесь нужен для того, чтоб уведомить об окончании парсинга (метод onParseFinished)
    IMainActivityPresenter mPresenter;

    private String mUrl = "http://download.cdn.yandex.net/mobilization-2016/artists.json";

    public AsyncTaskParseJson(IMainActivityPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    protected void onPreExecute() { }

    @Override
    protected String doInBackground(String... arg0) {
        try {
            JSONArray json = (new JsonParser(mPresenter.getContext())).getJSONFromUrl(mUrl);

            //по очереди обрабатываем всех исполнителей
            for (int i = 0; i < json.length(); i++) {

                JSONObject c = json.getJSONObject(i);
                Integer id = c.getInt("id");
                String name = c.getString("name");
                JSONArray genresArray = c.getJSONArray("genres");
                List<String> genres = new ArrayList<String>();
                for (int j = 0; j < genresArray.length(); j++) {
                    genres.add(genresArray.getString(j));
                }
                int tracks = c.getInt("tracks");
                int albums = c.getInt("albums");
                String link = c.has("link") ? c.getString("link") : null;
                String description = c.getString("description");
                JSONObject covers = c.getJSONObject("cover");
                String smallCover = covers.getString("small");
                String bigCover = covers.getString("big");
                mPresenter.addArtist(new Artist(id, name, genres, tracks, albums, link, description, smallCover, bigCover));
            }
        } catch (JSONException e) {
            Toast.makeText(mPresenter.getContext(), "ошибка парсинга JSON", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String strFromDoInBg) {
        mPresenter.onParseFinished();
    }
}
