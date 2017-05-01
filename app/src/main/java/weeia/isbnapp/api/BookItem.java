package weeia.isbnapp.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MATEUSZ on 20.04.2017.
 */

public class BookItem {
    @SerializedName("volumeInfo")
    public VolumeInfo volumeInfo;

}
