package io.sweers.rxpalette

import android.support.v7.graphics.Palette
import io.reactivex.Single

/**
 * Returns a [single][Single] that emits a [palette][Palette] from the source `bitmap`
 */
public inline fun Palette.Builder.asSingle(): Single<Palette> = RxPalette.generate(this)
