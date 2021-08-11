package com.hocheol.dagger2demo.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetroModule.class})
public interface RetroComponent {

    public void inject(MainActivityViewModel mainActivityViewModel);

}
