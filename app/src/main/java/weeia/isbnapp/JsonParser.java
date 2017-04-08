package weeia.isbnapp;

import android.util.Log;
import org.json.JSONArray;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {
    public JsonParser() {}

    public JSONArray parseJsonArray(String url) throws MalformedURLException {
        URL urlObj = new URL(url);
        JSONArray jarray=null;
        try {
            String result = CustomHttpClient.getPageFromUrl(urlObj);
            jarray = new JSONArray(result);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return jarray;
    }

    public JSONObject parseJsonObject(String url) throws MalformedURLException {
        URL urlObj = new URL(url);
        JSONObject jObject = null;
        try {
            String result = CustomHttpClient.getPageFromUrl(urlObj);
            jObject = new JSONObject(result);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return jObject;
    }


}