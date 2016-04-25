package com.example.dmitry.artistshower.di.modules;


import com.example.dmitry.artistshower.presenter.IPresenter;
import com.example.dmitry.artistshower.presenter.Presenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

//в этом модуле мы описываем, как будет предоставляться (provide) презентер.

@Module
public class PresentersModule {

    @Provides
    @Singleton
    public IPresenter provideMainActivityPresenter() {
        return new Presenter();
    }
}
