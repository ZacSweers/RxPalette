package io.sweers.rxpalette.sample.api;

import io.sweers.rxpalette.sample.api.model.Album;
import io.sweers.rxpalette.sample.api.service.AlbumService;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * The core class of the Imgur API.
 *
 * Note that Most of the Imgur API is adapted from https://github.com/Rabrg/imgur-api
 *
 * @author Ryan Greene
 */
public class ImgurApi {

    private final RestAdapter restAdapter;
    private final AlbumService albumService;

    /**
     * Constructs a new ImgurApi with the specified clientId.
     *
     * @param clientId The client id.
     */
    public ImgurApi(final String clientId) {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.imgur.com/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(final RequestFacade request) {
                        request.addHeader("Authorization", "Client-ID " + clientId);
                    }
                })
                .build();
        albumService = restAdapter.create(AlbumService.class);
    }

    /**
     * Get information about a specific album.
     *
     * @param id The id of the album.
     * @return A response containing information about the album.
     */
    public Observable<ImgurResponse<Album>> getAlbum(final String id) {
        return albumService.getAlbum(id);
    }
}
