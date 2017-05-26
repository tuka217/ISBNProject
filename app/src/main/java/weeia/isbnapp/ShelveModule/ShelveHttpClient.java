package weeia.isbnapp.ShelveModule;

import com.google.gson.JsonElement;
import com.google.gson.*;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.http.HTTP;

/**
 * Created by MATEUSZ on 25.05.2017.
 */
public class ShelveHttpClient implements IContentProvider, IPutContent {
    private URL urlObj;
    private ShelveItemDto shelveItem;

    public ShelveHttpClient(URL _urlObj) {
        urlObj = _urlObj;
    }

    public ShelveHttpClient(URL urlObj, ShelveItemDto shelveItem) {
        this.urlObj = urlObj;
        this.shelveItem = shelveItem;
    }

    public JsonArray ProvideContent() throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
        urlConnection.setRequestMethod("GET");
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

        JsonArray jelement = new JsonParser().parse(result.toString()).getAsJsonArray();

        return jelement;
    }

    @Override
    public Integer putContent() throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
        urlConnection.setRequestMethod("POST");
        Gson gson = new Gson();
        String json = gson.toJson(shelveItem);
        try {
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            DataOutputStream wr = new DataOutputStream(out);
            try {
                wr.writeBytes(json);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                wr.close();
                out.close();
            }

        } finally {
            urlConnection.disconnect();
        }


        return 1;
    }
}
