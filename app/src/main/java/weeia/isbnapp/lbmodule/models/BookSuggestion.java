package weeia.isbnapp.lbmodule.models;

/**
 * Created by sew on 2017-05-24.
 */

public class BookSuggestion {
    public String id ;
    public String authors;
    public String title;
    public String coverUrl;
    public String rating;
    public String url;


    public BookSuggestion(String _id, String _authors, String _title, String _coverUrl,String _rating, String  _url) {
        id = _id;
        authors = _authors;
        title = _title;
        coverUrl = _coverUrl;
        rating =_rating;
        url =_url;
    }
}
