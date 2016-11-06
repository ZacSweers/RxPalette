package io.sweers.rxpalette.sample.api.service;

import io.reactivex.Single;
import io.sweers.rxpalette.sample.api.ImgurResponse;
import io.sweers.rxpalette.sample.api.model.Album;
import io.sweers.rxpalette.sample.api.model.Image;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * An interface containing all album related services used by the Retrofit REST client.
 *
 * @author Ryan Greene
 */
public interface AlbumService {

    @GET("album/{id}")
    Single<ImgurResponse<Album>> getAlbum(@Path("id") String id);

    @GET("album/{albumId}/{imageId}")
    Single<ImgurResponse<Image>> getAlbumImage(@Path("albumId") String albumId, @Path("imageId") String imageId);
}
