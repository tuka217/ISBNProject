package weeia.isbnapp.client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import weeia.isbnapp.model.GoodreadsResponse;

/**
 * Created by Anna on 2017-03-22.
 */
public interface GoodreadsClient {

    @GET("isbn/{isbn}")
    Call<GoodreadsResponse> bookByISBN(
           @Path("isbn") String isbn,
           @Query("key") String key,
           @Query("format") String format
    );
}
