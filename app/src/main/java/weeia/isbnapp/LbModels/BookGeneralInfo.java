package weeia.isbnapp.LbModels;

import org.json.JSONObject;

/**
 * Created by sew on 2017-04-09.
 */

public class BookGeneralInfo {
    public String id ;
    public String authors;
    public String title;
    public String coverUrl;

    public BookGeneralInfo(String _id, String _authors, String _title, String _coverUrl) {
        id = _id;
        authors = _authors;
        title = _title;
        coverUrl = _coverUrl;
    }
}
