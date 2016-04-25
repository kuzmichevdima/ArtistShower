package com.example.dmitry.artistshower.di.components;

import com.example.dmitry.artistshower.di.modules.ContextModule;
import com.example.dmitry.artistshower.di.modules.PresentersModule;
import com.example.dmitry.artistshower.view.ArtistInfoActivity;
import com.example.dmitry.artistshower.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

//здесь мы описываем компонент приложения - AppComponent. Это нужно для inject

@Singleton
@Component(
        modules = {
                PresentersModule.class,
                ContextModule.class,
        }
)
public interface AppComponent {
    void inject(MainActivity view);
    void inject(ArtistInfoActivity view);
}
