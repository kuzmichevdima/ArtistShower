package com.example.dmitry.artistshower.di.components;

import com.example.dmitry.artistshower.di.modules.ContextModule;
import com.example.dmitry.artistshower.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ContextModule.class,
        }
)
public interface AppComponent {
    void inject(MainActivity view);
}
