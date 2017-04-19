package weeia.isbnapp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

interface IContentProvider
{
     String ProvideContent() throws IOException;
}

public class CustomHttpClient implements IContentProvider {

    private  URL urlObj;

    CustomHttpClient(URL _urlObj){
        urlObj = _urlObj;
    }

    public  String ProvideContent() throws IOException {
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
