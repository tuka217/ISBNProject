package weeia.isbnapp.ShelveModule;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by MATEUSZ on 26.05.2017.
 */
public interface IContentProvider
{
    JsonArray ProvideContent() throws IOException;
}