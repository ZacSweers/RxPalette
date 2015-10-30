package io.sweers.rxpalette

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.graphics.Palette
import com.google.common.truth.Truth
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config

import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import rx.observers.TestSubscriber
import kotlin.properties.Delegates


@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
public class MyFirstTestClass {

    var bitmap: Bitmap by Delegates.notNull<Bitmap>()

    @Before
    public fun setUp() {
        val context: Context = Robolectric.buildActivity(Activity::class.java).create().get()
        bitmap = BitmapFactory.decodeResource(context.resources, android.R.drawable.star_on)
    }

    @Test
    public fun testBuilderGenerate() {
        val testSubscriber = TestSubscriber<Palette>()
        Palette.Builder(bitmap).asObservable().subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
        testSubscriber.assertCompleted()
        val onNextEvents = testSubscriber.onNextEvents
        Truth.assertThat<Palette, Iterable<Palette>>(onNextEvents).isNotEmpty()
        Truth.assertThat<Palette, Iterable<Palette>>(onNextEvents).hasSize(1)
    }

    @Test
    fun testNullBuilderGenerate() {
        val testSubscriber = TestSubscriber<Palette>()
        val nullBuilder: Palette.Builder? = null
        nullBuilder?.asObservable()?.subscribe(testSubscriber)
        Truth.assertThat(testSubscriber.onNextEvents).isEmpty()
        Truth.assertThat(testSubscriber.onCompletedEvents).isEmpty()
        Truth.assertThat(testSubscriber.onErrorEvents).isEmpty()
    }
}
