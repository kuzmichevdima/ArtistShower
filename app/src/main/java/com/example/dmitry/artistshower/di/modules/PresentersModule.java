package com.example.dmitry.artistshower.di.modules;


import com.example.dmitry.artistshower.presenter.ArtistInfoActivityPresenter;
import com.example.dmitry.artistshower.presenter.IArtistInfoActivityPresenter;
import com.example.dmitry.artistshower.presenter.IMainActivityPresenter;
import com.example.dmitry.artistshower.presenter.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentersModule {

    @Provides
    @Singleton
    public IMainActivityPresenter provideMainActivityPresenter() {
        return new MainActivityPresenter();
    }

    @Provides
    @Singleton
    public IArtistInfoActivityPresenter provideArtistInfoActivityPresenter() {
        return new ArtistInfoActivityPresenter();
    }
}
