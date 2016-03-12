package io.sweers.rxpalette

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.graphics.Palette
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import rx.observers.TestSubscriber
import kotlin.properties.Delegates


@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
public class RxPaletteTest {

    var bitmap: Bitmap by Delegates.notNull<Bitmap>()

    @Before fun setUp() {
        val context: Context = Robolectric.buildActivity(Activity::class.java).create().get()
        bitmap = BitmapFactory.decodeResource(context.resources, android.R.drawable.star_on)
    }

    @Test fun testBuilderGenerate() {
        val testSubscriber = TestSubscriber<Palette>()
        Palette.Builder(bitmap).asSingle().subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
        testSubscriber.assertCompleted()
        val onNextEvents = testSubscriber.onNextEvents
        assertThat<Palette, Iterable<Palette>>(onNextEvents).isNotEmpty()
        assertThat<Palette, Iterable<Palette>>(onNextEvents).hasSize(1)
    }

    @Test fun testNullBuilderGenerate() {
        val testSubscriber = TestSubscriber<Palette>()
        val nullBuilder: Palette.Builder? = null
        nullBuilder?.asSingle()?.subscribe(testSubscriber)
        assertThat(testSubscriber.onNextEvents).isEmpty()
        assertThat(testSubscriber.onCompletedEvents).isEmpty()
        assertThat(testSubscriber.onErrorEvents).isEmpty()
    }
}
