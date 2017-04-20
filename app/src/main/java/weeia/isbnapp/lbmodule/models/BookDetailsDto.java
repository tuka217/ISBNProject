package weeia.isbnapp.lbmodule.models;

import java.util.Map;

/**
 * Created by sew on 2017-04-09.
 */

public class BookDetailsDto {

    public final String title;
    public final String publishDate;
    public final String numOfPages;
    public final String language;
    public final String description;
    public final  String category;
    public double rate;

    public BookDetailsDto(double _rate, Map<String, String> dict, String _description) {
      rate = _rate;
        title = tryGetValue(dict, "tytuł oryginału");
        publishDate = tryGetValue(dict,"data wydania");
        numOfPages = tryGetValue(dict,"liczba stron");
        language = tryGetValue(dict, "język");
        category = tryGetValue(dict, "kategoria");
        description = _description;
    }

    private String tryGetValue(Map<String, String> dict, String key)
    {
        String returnValue = dict.get(key);
        if(returnValue == null)
            returnValue ="";
        return  returnValue;
    }
}
