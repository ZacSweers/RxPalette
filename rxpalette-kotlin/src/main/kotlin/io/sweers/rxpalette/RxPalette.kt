package io.sweers.rxpalette

import android.graphics.Bitmap
import android.support.v7.graphics.Palette
import rx.Observable

/**
 * Generate the `Palette` synchronously.
 */
public inline fun Palette.generate(bitmap: Bitmap): Observable<Palette> = RxPalette.generate(bitmap)

/**
 * Generate the `Palette` synchronously.
 */
public inline fun Palette.Builder.generate(): Observable<Palette> = RxPalette.generate(this)

/**
 * Generate the `Palette` asynchronously.
 */
public inline fun Palette.generateAsync(bitmap: Bitmap): Observable<Palette> = RxPalette.generateAsync(bitmap)

/**
 * Generate the `Palette` asynchronously.
 */
public inline fun Palette.Builder.generateAsync(): Observable<Palette> = RxPalette.generateAsync(this)
