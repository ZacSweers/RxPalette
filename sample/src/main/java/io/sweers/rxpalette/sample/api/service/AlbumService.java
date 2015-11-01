package io.sweers.rxpalette.sample.api.service;

import io.sweers.rxpalette.sample.api.ImgurResponse;
import io.sweers.rxpalette.sample.api.model.Album;
import io.sweers.rxpalette.sample.api.model.Image;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * An interface containing all album related services used by the Retrofit REST client.
 *
 * @author Ryan Greene
 */
public interface AlbumService {

    @GET("/album/{id}")
    Observable<ImgurResponse<Album>> getAlbum(@Path("id") String id);

    @GET("/album/{albumId}/{imageId}")
    Observable<ImgurResponse<Image>> getAlbumImage(@Path("albumId") String albumId, @Path("imageId") String imageId);
}
