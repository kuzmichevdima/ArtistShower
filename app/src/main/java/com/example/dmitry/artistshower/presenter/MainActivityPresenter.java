package com.example.dmitry.artistshower.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.dmitry.artistshower.model.Artist;
import com.example.dmitry.artistshower.model.AsyncTaskParseJson;
import com.example.dmitry.artistshower.view.ArtistInfoActivity;
import com.example.dmitry.artistshower.view.IMainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by dmitry on 24.04.16.
 */

//этот презентер, как понятно из названия, занимается коммуникацией MainAcitivity и Model, т.е. служит промежуточным звеном

public class MainActivityPresenter implements IMainActivityPresenter {
    private IMainActivity mView;
    private List<Artist> mArtistsList = null;

    public void onCreate(Bundle state, IMainActivity view) {
        mView = view;
    }

    public void loadData() {
        new AsyncTaskParseJson(this).execute();
    }

    @Override
    public void addArtist(Artist artist) {
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

    @Override
    public Context getContext() {
        return mView.getContext();
    }
}

