package io.sweers.rxpalette

import android.support.v7.graphics.Palette
import rx.Observable

/**
 * Generate the `Palette` synchronously.
 */
public inline fun Palette.Builder.asObservable(): Observable<Palette> = RxPalette.generate(this)
