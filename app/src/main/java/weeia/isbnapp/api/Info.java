package weeia.isbnapp.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MATEUSZ on 20.04.2017.
 */

public class Info {
    public int totalItems;

    @SerializedName("items")
    public List<BookItem> items;
}
