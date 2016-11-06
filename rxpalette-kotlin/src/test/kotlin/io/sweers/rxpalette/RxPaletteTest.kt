package io.sweers.rxpalette

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.graphics.Palette
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
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
        Palette.Builder(bitmap).asSingle()
            .test()
            .assertNoErrors()
            .assertComplete()
            .assertValueCount(1)
    }

    @Test fun testNullBuilderGenerate() {
        val nullBuilder: Palette.Builder? = null
        nullBuilder?.asSingle()?.test()?.assertNoErrors()?.assertNoValues()?.assertNotComplete()
    }
}
