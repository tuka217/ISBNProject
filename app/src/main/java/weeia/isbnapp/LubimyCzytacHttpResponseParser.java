package weeia.isbnapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LubimyCzytacHttpResponseParser {

    public String GetBookId(String url) throws MalformedURLException {

        JsonParser jParser = new JsonParser();
        JSONArray json = jParser.parseJsonArray(url);
        String bookId="";
        //TODO narazie pobieram pierwszy wynik bo jest natrafniejszy ,ale trzeba sprawdzi czy rzeczywiscie to ta ksiazka o ktora chodzilo userowi
        if(json.length()>=1) {
            try {
                JSONObject c = json.getJSONObject(0);
                String id = c.getString("id");
                bookId = id;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return bookId;
    }

    public ArrayList<BookOpinionOpinionsPresenterDto> GetBookOpinions(String url) throws MalformedURLException {
            ArrayList<BookOpinionOpinionsPresenterDto> opinionsList = new ArrayList<BookOpinionOpinionsPresenterDto>() ;
             try {
                JsonParser jParser = new JsonParser();
                JSONObject json = jParser.parseJsonObject(url);

                String html = json.getString("html");
                Document doc = Jsoup.parse(clearResponse(html));

                Elements reviewContents = doc.getElementsByClass("reviewContent");
                Elements nickContents = doc.getElementsByClass("reviewHeadUser");

                for (int i =0;i<reviewContents.size();i++) {
                    String review = getReviewContent(reviewContents.get(i));
                    String nickName = getNickName(nickContents.get(i));
                    opinionsList.add(new
                            BookOpinionOpinionsPresenterDto(review,nickName, 4));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        return opinionsList;
    }

    public double GetBookRate(String url) throws MalformedURLException {
        double rate =0;
        try {
            String html = CustomHttpClient.getPageFromUrl(new URL(url));
            Document doc = Jsoup.parse(clearResponse(html));
            Element reviewContents = doc.getElementById("rating-value");
            rate =  Double.parseDouble(reviewContents.child(0).text().replace(",","."));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return rate;
    }

    private String getNickName(Element element) {
        return element.child(0).text();
    }

    private String getReviewContent(Element element) {
        if(element.childNodes().size()>2) {
            return element.child(1).child(0).text();
        }
        else if(element.childNodes().size()==2) {
            return  element.child(0).text();
        }else {
            return "";
        }
    }

    private String clearResponse(String response){
        return response.replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t","");
    }
}
