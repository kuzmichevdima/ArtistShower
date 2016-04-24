package com.example.dmitry.artistshower.presenter;

import android.os.Bundle;

import com.example.dmitry.artistshower.model.AsyncTaskParseJson;
import com.example.dmitry.artistshower.view.IMainActivity;

/**
 * Created by dmitry on 24.04.16.
 */
public class MainActivityPresenter implements IMainActivityPresenter {
    private IMainActivity mView;

    public void onCreate(Bundle state, IMainActivity view) {
        mView = view;
    }

    public void loadData() {
        new AsyncTaskParseJson().execute();
    }
}

