package com.example.dmitry.artistshower.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.dmitry.artistshower.model.Artist;
import com.example.dmitry.artistshower.model.AsyncTaskParseJson;
import com.example.dmitry.artistshower.view.ArtistInfoActivity;
import com.example.dmitry.artistshower.view.IMainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 24.04.16.
 */
public class MainActivityPresenter implements IMainActivityPresenter {
    private IMainActivity mView;
    private List<Artist> mArtistsList = null;
    private final String tag = "MainActivityPresenter";

    public void onCreate(Bundle state, IMainActivity view) {
        mView = view;
    }

    public void loadData() {
        new AsyncTaskParseJson(this).execute();
    }

    @Override
    public void addArtist(Artist artist) {
        Log.d(tag, "MainAcitvityPresenter add " + artist.toString());
        if (mArtistsList == null) {
            mArtistsList = new ArrayList<Artist>();
        }
        mArtistsList.add(artist);
    }

    @Override
    public void onParseFinished() {
        mView.setArtists(mArtistsList);
    }

    @Override
    public void onArtistSelected(Artist artist) {
        Intent intent = new Intent(mView.getContext(), ArtistInfoActivity.class);
        intent.putExtra("Artist", artist);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mView.getContext().startActivity(intent);
    }
}

