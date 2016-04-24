package com.example.dmitry.artistshower.di.modules;


import com.example.dmitry.artistshower.presenter.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentersModule {

    @Provides
    @Singleton
    public MainActivityPresenter provideMainActivityPresenter() {
        return new MainActivityPresenter();
    }
}