package io.sweers.rxpalette;

import android.graphics.Bitmap;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;

import rx.Observable;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link Palette}.
 */
public final class RxPalette {

    /**
     * Generate the {@link Palette} synchronously.
     */
    @CheckResult @NonNull
    public static Observable<Palette> generate(@NonNull Bitmap bitmap) {
        return Observable.create(new GeneratePaletteOnSubscribe(Palette.from(bitmap), false));
    }

    /**
     * Generate the {@link Palette} synchronously.
     */
    @CheckResult @NonNull
    public static Observable<Palette> generate(@NonNull Palette.Builder builder) {
        return Observable.create(new GeneratePaletteOnSubscribe(builder, false));
    }

    /**
     * Generate the {@link Palette} asynchronously.
     */
    @CheckResult @NonNull
    public static Observable<Palette> generateAsync(@NonNull Bitmap bitmap) {
        return Observable.create(new GeneratePaletteOnSubscribe(Palette.from(bitmap), true));
    }

    /**
     * Generate the {@link Palette} asynchronously.
     */
    @CheckResult @NonNull
    public static Observable<Palette> generateAsync(@NonNull Palette.Builder builder) {
        return Observable.create(new GeneratePaletteOnSubscribe(builder, true));
    }
}
