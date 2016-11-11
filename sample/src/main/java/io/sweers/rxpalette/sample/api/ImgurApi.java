package io.sweers.rxpalette.sample.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import io.reactivex.Single;
import io.sweers.rxpalette.sample.api.model.Album;
import io.sweers.rxpalette.sample.api.service.AlbumService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * The core class of the Imgur API.
 *
 * Note that Most of the Imgur API is adapted from https://github.com/Rabrg/imgur-api
 *
 * @author Ryan Greene
 */
public class ImgurApi {

    private final AlbumService albumService;

    /**
     * Constructs a new ImgurApi with the specified clientId.
     *
     * @param clientId The client id.
     */
    public ImgurApi(final String clientId) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request request = chain.request();
                        request = request.newBuilder().addHeader("Authorization", "Client-ID " + clientId).build();
                        return chain.proceed(request);
                    }
                })
                .build();
        albumService = new Retrofit.Builder()
                .baseUrl("https://api.imgur.com/3/")
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(AlbumService.class);
    }

    /**
     * Get information about a specific album.
     *
     * @param id The id of the album.
     * @return A response containing information about the album.
     */
    public Single<ImgurResponse<Album>> getAlbum(final String id) {
        return albumService.getAlbum(id);
    }
}
