package io.sweers.rxpalette.sample;

import android.app.Application;

import io.sweers.rxpalette.sample.api.ImgurApi;

public final class RxPaletteSampleApplication extends Application {

    private static RxPaletteSampleApplication instance;

    private ImgurApi imgurApi;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        imgurApi = new ImgurApi("4e2bb69101e4b13");
    }

    public static RxPaletteSampleApplication get() {
        return instance;
    }

    public ImgurApi getImgurApi() {
        return imgurApi;
    }
}
