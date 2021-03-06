package com.hocheol.dagger2demo;

import android.app.Application;

import com.hocheol.dagger2demo.di.DaggerRetroComponent;
import com.hocheol.dagger2demo.di.RetroComponent;
import com.hocheol.dagger2demo.di.RetroModule;

public class MyApplication extends Application {

    private RetroComponent retroComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        retroComponent = DaggerRetroComponent.builder()
                .retroModule(new RetroModule())
                .build();
    }

    public RetroComponent getRetroComponent() {
        return retroComponent;
    }
}
