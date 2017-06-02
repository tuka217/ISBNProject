package weeia.isbnapp.lbmodule;

import android.util.Log;
import org.json.JSONArray;
import java.net.MalformedURLException;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

class JsonParser
{

    public JsonParser() {
    }

    public JSONArray parseJsonArray(String jsonArray) throws JSONException {
        JSONArray jarray=null;
        try {
            jarray = new JSONArray(jsonArray);
        }  catch (JSONException e) {
             JSONObject jsonObject = new JSONObject(jsonArray);
             String firstObject = jsonObject.getString("0");
             String array =  "[ " + jsonObject.getString("0") + "]";
             jarray = new JSONArray(array);
             Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return jarray;
    }


    public JSONObject parseJsonObject(String jsonObject)  {
        JSONObject jObject = null;
        try {
            jObject = new JSONObject(jsonObject);

        }  catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return jObject;
    }

    public JSONArray parseJsonArrayWithManyReposnes(String jsonArray) throws JSONException {
        JSONArray jarray=null;
        try {
            jarray = new JSONArray(jsonArray);
        }  catch (JSONException e) {
            JSONObject jsonObject = new JSONObject(jsonArray);
            String array =  "[ " ;
            Iterator<String> iter = jsonObject.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                try {
                    array += jsonObject.get(key) + ",";
                } catch (JSONException ee) {
                    // Something went wrong!
                }
            }
            array = array.substring(0, array.length()-1);
            array += "]";
            jarray = new JSONArray(array);
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return jarray;
    }

}