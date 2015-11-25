package io.sweers.rxpalette;

import android.graphics.Bitmap;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;

import java.util.concurrent.Callable;

import rx.Observable;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link Palette}.
 */
public final class RxPalette {

    /**
     * Generate the {@link Palette} synchronously.
     */
    @CheckResult @NonNull
    public static Observable<Palette> generate(@NonNull final Bitmap bitmap) {
        return Observable.fromCallable(new Callable<Palette>() {
            @Override
            public Palette call() throws Exception {
                return Palette.from(bitmap).generate();
            }
        });
    }

    /**
     * Generate the {@link Palette} synchronously.
     */
    @CheckResult @NonNull
    public static Observable<Palette> generate(@NonNull final Palette.Builder builder) {
        return Observable.fromCallable(new Callable<Palette>() {
            @Override
            public Palette call() throws Exception {
                return builder.generate();
            }
        });
    }
}
