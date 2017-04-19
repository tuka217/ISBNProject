package weeia.isbnapp;

import android.util.Log;
import org.json.JSONArray;
import java.net.MalformedURLException;
import org.json.JSONException;
import org.json.JSONObject;

class JsonParser
{

    public JsonParser() {
    }

    public JSONArray parseJsonArray(String jsonArray) throws MalformedURLException {
        JSONArray jarray=null;
        try {
            jarray = new JSONArray(jsonArray);

        }  catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return jarray;
    }

    public JSONObject parseJsonObject(String jsonObject) throws MalformedURLException {
        JSONObject jObject = null;
        try {
            jObject = new JSONObject(jsonObject);

        }  catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return jObject;
    }


}