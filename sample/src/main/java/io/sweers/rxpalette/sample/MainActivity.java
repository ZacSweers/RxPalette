package io.sweers.rxpalette.sample;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.sweers.rxpalette.RxPalette;
import io.sweers.rxpalette.sample.api.ImgurResponse;
import io.sweers.rxpalette.sample.api.model.Album;
import io.sweers.rxpalette.sample.api.model.Image;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    @Bind(R.id.loading)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RxPaletteSampleApplication.get()
                .getImgurApi()
                .getAlbum("jx90V")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<ImgurResponse<Album>>() {
                    @Override
                    public void onSuccess(ImgurResponse<Album> albumImgurResponse) {
                        Adapter adapter = new Adapter(albumImgurResponse.data.images);
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "Error: " + e, Toast.LENGTH_LONG).show();
                        Log.e("ERROR", "OnError", e);
                    }
                });
    }

    public static class Adapter extends RecyclerView.Adapter<ImageHolder> {

        private final List<Image> images;

        private Adapter(List<Image> images) {
            this.images = images;
        }


        @Override
        public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
            return new ImageHolder(view);
        }

        @Override
        public void onBindViewHolder(final ImageHolder holder, int position) {
            Image image = images.get(position);
            Glide.with(holder.imageView.getContext())
                    .load(image.link)
                    .crossFade()
                    .centerCrop()
                    .listener(new BitmapListener() {
                        @Override
                        public void onBitmapReady(Bitmap bitmap) {
                            RxPalette.generate(bitmap)
                                    .subscribeOn(Schedulers.computation())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Action1<Palette>() {
                                        @Override
                                        public void call(Palette palette) {
                                            Palette.Swatch swatch = palette.getVibrantSwatch() != null
                                                    ? palette.getVibrantSwatch()
                                                    : palette.getMutedSwatch();

                                            holder.textView.setTextColor(swatch != null
                                                    ? swatch.getTitleTextColor()
                                                    : Color.BLACK);
                                            holder.textView.setBackgroundColor(swatch != null
                                                    ? swatch.getRgb()
                                                    : Color.WHITE);
                                        }
                                    });
                        }
                    })
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return images.size();
        }
    }

    public static class ImageHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image)
        ImageView imageView;

        @Bind(R.id.text)
        TextView textView;

        public ImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * RequestListener that forwards the bitmap onto a callback
     */
    private abstract static class BitmapListener implements RequestListener<String, GlideDrawable> {

        @Override
        public boolean onException(
                Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            return false;
        }

        @Override
        public boolean onResourceReady(
                GlideDrawable resource,
                String model,
                Target<GlideDrawable> target,
                boolean isFromMemoryCache,
                boolean isFirstResource) {
            onBitmapReady(GlideBitmapDrawable.class.cast(resource).getBitmap());
            return false;
        }

        public void onBitmapReady(Bitmap bitmap) {

        }
    }
}
