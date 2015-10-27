package io.sweers.rxpalette;

import android.support.v7.graphics.Palette;

import rx.Observable;
import rx.Subscriber;

/**
 * {@link rx.Observable.OnSubscribe} implementation that wraps a Palette builder. Supports
 * synchronous and asynchronous execution.
 */
class GeneratePaletteOnSubscribe implements Observable.OnSubscribe<Palette> {

    private Palette.Builder builder;
    private boolean async;

    public GeneratePaletteOnSubscribe(Palette.Builder builder, boolean async) {
        this.builder = builder;
        this.async = async;
    }

    @Override
    public void call(final Subscriber<? super Palette> subscriber) {
        if (async) {
            builder.generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(palette);
                    }
                }
            });
        } else {
            if (!subscriber.isUnsubscribed()) {
                subscriber.onNext(builder.generate());
            }
        }
    }
}
