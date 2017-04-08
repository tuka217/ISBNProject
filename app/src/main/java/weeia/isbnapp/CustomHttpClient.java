package weeia.isbnapp;

import android.support.annotation.NonNull;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CustomHttpClient {
    @NonNull
    public static String getPageFromUrl(URL urlObj) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}
