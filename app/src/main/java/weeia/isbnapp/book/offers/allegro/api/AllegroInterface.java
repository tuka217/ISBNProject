package weeia.isbnapp.book.offers.allegro.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Anna on 2017-04-07.
 */

public interface AllegroInterface {

    @Headers({
        "api-key: eyJjbGllbnRJZCI6IjNjYTFhMDA0LTYzMzYtNDI3My1iMDhhLTUyMmFjOTk4N2FhYiJ9.2-kKd9h9zb-6gjeMoHkP5zc2nYmbJxLvfTpurBgkgcs=",
        "Accept: application/vnd.allegro.public.v1+json",
        "User-Agent: pl.allegro.sale/1.5.0 (Client-Id 01234567-89ab-cdef-0123-456789abcdef) Android/4.0 (Motorola XT1052)"
    })
    @GET("/offers")
    public Call<Offers> booksByPhrase(
          @Query("phrase") String phrase,
          @Query("country.code") String countryCode,
          @Query("category.id") String categoryId
    );
}
