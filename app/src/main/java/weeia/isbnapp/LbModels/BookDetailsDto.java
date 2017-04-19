package weeia.isbnapp.LbModels;

import java.util.Map;

/**
 * Created by sew on 2017-04-09.
 */

public class BookDetailsDto {

    private final String title;
    private final Object publishDate;
    private final String numOfPages;
    private final String language;
    private final String description;
    private  final  String category;
    private double rate;

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
