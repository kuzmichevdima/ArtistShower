package com.example.dmitry.artistshower.presenter;

import android.os.Bundle;

import com.example.dmitry.artistshower.model.Artist;
import com.example.dmitry.artistshower.view.IMainActivity;

/**
 * Created by dmitry on 24.04.16.
 */
public interface IMainActivityPresenter {
    void onCreate(Bundle state, IMainActivity v);
    void loadData();
    void addArtist(Artist artist);
    void onArtistSelected(Artist artist);
    void onParseFinished();
}
