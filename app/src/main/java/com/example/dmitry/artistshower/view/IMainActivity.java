package com.example.dmitry.artistshower.view;

import android.content.Context;

import com.example.dmitry.artistshower.model.Artist;

import java.util.List;

/**
 * Created by dmitry on 24.04.16.
 */

//для MainAcitivity тоже нужно сделать интерфейс, т.к. презентер не должен работать с конкретным классом

public interface IMainActivity {
    void setArtists(List<Artist> artists);
    Context getContext();

}
