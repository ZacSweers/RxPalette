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

import java.util.List;

import rx.observers.TestSubscriber;

import static com.google.common.truth.Truth.assertThat;

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
        TestSubscriber<Palette> testSubscriber = new TestSubscriber<>();
        RxPalette.generate(bitmap)
                .subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        List<Palette> onNextEvents = testSubscriber.getOnNextEvents();
        assertThat(onNextEvents).isNotEmpty();
        assertThat(onNextEvents).hasSize(1);
    }

    @Test
    public void testBuilderGenerate() {
        TestSubscriber<Palette> testSubscriber = new TestSubscriber<>();
        RxPalette.generate(Palette.from(bitmap))
                .subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        List<Palette> onNextEvents = testSubscriber.getOnNextEvents();
        assertThat(onNextEvents).isNotEmpty();
        assertThat(onNextEvents).hasSize(1);
    }

    @Test
    public void testNullGenerate() {
        TestSubscriber<Palette> testSubscriber = new TestSubscriber<>();
        Bitmap nullBitmap = null;
        //noinspection ConstantConditions
        RxPalette.generate(nullBitmap)
                .subscribe(testSubscriber);
        testSubscriber.assertError(IllegalArgumentException.class);
    }

    @Test
    public void testNullBuilderGenerate() {
        TestSubscriber<Palette> testSubscriber = new TestSubscriber<>();
        Palette.Builder nullBuilder = null;
        //noinspection ConstantConditions
        RxPalette.generate(nullBuilder)
                .subscribe(testSubscriber);
        testSubscriber.assertError(NullPointerException.class);
    }
}
