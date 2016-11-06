package io.sweers.rxpalette;

import android.graphics.Bitmap;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;

import java.util.concurrent.Callable;

import io.reactivex.Single;

/**
 * Static factory methods for creating {@linkplain Single singles} for {@link Palette}.
 */
public final class RxPalette {

    /**
     * Returns a {@linkplain Single single} that emits a {@linkplain Palette palette} from the source {@code bitmap}
     */
    @CheckResult @NonNull
    public static Single<Palette> generate(@NonNull final Bitmap bitmap) {
        return Single.fromCallable(new Callable<Palette>() {
            @Override
            public Palette call() throws Exception {
                return Palette.from(bitmap).generate();
            }
        });
    }

    /**
     * Returns a {@linkplain Single single} that emits a {@linkplain Palette palette} from the source {@code bitmap}
     */
    @CheckResult @NonNull
    public static Single<Palette> generate(@NonNull final Palette.Builder builder) {
        return Single.fromCallable(new Callable<Palette>() {
            @Override
            public Palette call() throws Exception {
                return builder.generate();
            }
        });
    }
}
