package com.example.dmitry.artistshower.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context mApplicationContext;

    public ContextModule(Context applicationContext) {
        mApplicationContext = applicationContext;
    }

    @Provides
    public Context provideApplicationContext() {
        return mApplicationContext;
    }
}
