package io.sweers.rxpalette;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;

import rx.Observable;
import rx.Subscriber;

/**
 * {@link rx.Observable.OnSubscribe} implementation that wraps a Palette builder taking a provided bitmap
 */
class GeneratePaletteOnSubscribe implements Observable.OnSubscribe<Palette> {

    private Bitmap bitmap;

    public GeneratePaletteOnSubscribe(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public void call(final Subscriber<? super Palette> subscriber) {
        if (!subscriber.isUnsubscribed()) {
            subscriber.onNext(Palette.from(bitmap).generate());
            subscriber.onCompleted();
        }
    }
}
