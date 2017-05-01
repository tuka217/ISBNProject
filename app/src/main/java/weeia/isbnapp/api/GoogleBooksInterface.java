package weeia.isbnapp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by MATEUSZ on 20.04.2017.
 */

public interface GoogleBooksInterface {
       String BASE_ADDRESS = "https://www.googleapis.com";
    @GET("/books/v1/volumes")
    Call<Info> getTitle(@Query("q") String isbn);
}
