package com.example.dmitry.artistshower;

import android.app.Application;
import android.content.Context;

import com.example.dmitry.artistshower.di.components.AppComponent;
import com.example.dmitry.artistshower.di.components.DaggerAppComponent;
import com.example.dmitry.artistshower.di.modules.ContextModule;


/**
 * Main class for application
 */
public class App extends Application {
    /**
     * Base component for the application
     * To inject providers in your view in one line.
     * App.getAppComponent(getContext()).inject(this)
     */
    private AppComponent mComponent;

    public static AppComponent getAppComponent(Context context) {
        return ((App) context.getApplicationContext()).getComponent();
    }

    private AppComponent getComponent() {
        return mComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerAppComponent.builder().contextModule(new ContextModule(this)).build();
    }

}
