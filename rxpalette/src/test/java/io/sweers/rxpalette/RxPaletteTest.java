package io.sweers.rxpalette;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import io.reactivex.subscribers.TestSubscriber;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RxPaletteTest {

    private Bitmap bitmap;

    @Before
    public void setUp() {
        Context context = Robolectric.buildActivity(Activity.class).create().get();
        bitmap = BitmapFactory.decodeResource(context.getResources(), android.R.drawable.star_on);
    }

    @Test
    public void testGenerate() {
        RxPalette.generate(bitmap)
                .test()
                .assertNoErrors()
                .assertComplete()
                .assertValueCount(1);
    }

    @Test
    public void testBuilderGenerate() {
        RxPalette.generate(Palette.from(bitmap))
                .test()
                .assertNoErrors()
                .assertComplete()
                .assertValueCount(1);
    }

    @Test
    public void testNullGenerate() {
        Bitmap nullBitmap = null;
        //noinspection ConstantConditions
        RxPalette.generate(nullBitmap)
                .test()
        		.assertError(IllegalArgumentException.class);
    }

    @Test
    public void testNullBuilderGenerate() {
        TestSubscriber<Palette> testSubscriber = new TestSubscriber<>();
        Palette.Builder nullBuilder = null;
        //noinspection ConstantConditions
        RxPalette.generate(nullBuilder)
                .test()
				.assertError(NullPointerException.class);
    }
}
