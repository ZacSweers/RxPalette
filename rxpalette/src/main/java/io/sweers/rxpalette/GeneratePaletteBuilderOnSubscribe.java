package io.sweers.rxpalette;

import android.support.v7.graphics.Palette;

import rx.Observable;
import rx.Subscriber;

/**
 * {@link rx.Observable.OnSubscribe} implementation that wraps a Palette builder.
 */
class GeneratePaletteBuilderOnSubscribe implements Observable.OnSubscribe<Palette> {

    private Palette.Builder builder;

    public GeneratePaletteBuilderOnSubscribe(Palette.Builder builder) {
        this.builder = builder;
    }

    @Override
    public void call(final Subscriber<? super Palette> subscriber) {
        if (!subscriber.isUnsubscribed()) {
            subscriber.onNext(builder.generate());
            subscriber.onCompleted();
        }
    }
}
