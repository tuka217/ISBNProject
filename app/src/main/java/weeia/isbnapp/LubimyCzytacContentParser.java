package weeia.isbnapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import weeia.isbnapp.LbModels.BookDetailsDto;
import weeia.isbnapp.LbModels.BookOpinionOpinionsPresenterDto;
import weeia.isbnapp.LbModels.BookGeneralInfo;

public class LubimyCzytacContentParser implements  ILubimyCzytacContentProvider {

public BookGeneralInfo GetBookInfo(IContentProvider contentProvder,String bookName) throws IOException {

        JsonParser jParser = new JsonParser();
        JSONArray json = jParser.parseJsonArray(contentProvder.ProvideContent());
        BookGeneralInfo bookSmallInfoTemp=null;
        if(json.length()>=1 ) {
            try {
                JSONObject c = json.getJSONObject(0);
                String suggestedBookName = c.getString("title");
                if(namesNotEqual(suggestedBookName, bookName))
                {
                    //TODO dorobic obsługe blędu braku takiej ksiazki
                    return  null;
                }
                bookSmallInfoTemp = new BookGeneralInfo(
                 c.getString("id"),
                 c.getString("authors"),
                 c.getString("title"),
                 c.getString("cover"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return bookSmallInfoTemp;
    }

    public BookDetailsDto GetBookDetails(IContentProvider contentProvder) {
        BookDetailsDto bookDetailsDto=null;
        try {
            String html = contentProvder.ProvideContent();
            Document doc = Jsoup.parse(clearResponse(html));

            Element bookDescription = doc.getElementById("sBookDescriptionLong");
            Element reviewContents = doc.getElementById("rating-value");
            double rate =  Double.parseDouble(reviewContents.child(0).text().replace(",","."));

            Element bookDetailContent = doc.getElementById("dBookDetails");
            Elements bookInfoElements = bookDetailContent.getElementsByClass("profil-desc-inline");

            Map<String, String> dict = new HashMap<String, String>() ;
            for(int i=0;i<bookInfoElements.size();i++){
                Element element = bookInfoElements.get(i);
                if(element.childNodes().size() >= 2){
                    dict.put(element.child(0).text(), element.child(1).text());
                }
            }
            bookDetailsDto = new BookDetailsDto(rate,dict,bookDescription.text());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return bookDetailsDto;
    }

    public ArrayList<BookOpinionOpinionsPresenterDto> GetBookOpinions(IContentProvider contentProvder) throws MalformedURLException {
            ArrayList<BookOpinionOpinionsPresenterDto> opinionsList = new ArrayList<BookOpinionOpinionsPresenterDto>() ;
             try {
                JsonParser jParser = new JsonParser();
                JSONObject json = jParser.parseJsonObject(contentProvder.ProvideContent());

                String html = json.getString("html");
                Document doc = Jsoup.parse(clearResponse(html));

                Elements reviewContents = doc.getElementsByClass("reviewContent");
                Elements nickContents = doc.getElementsByClass("reviewHeadUser");

                Elements reviewLinks = doc.getElementsByClass("review-footer");
                for (int i =0;i< reviewContents.size();i++) {
                    int rate = nickContents.get(i).parent().getElementsByClass("sprite-horizontal rating-star rating-on").size();
                    String review = getReviewContent(reviewContents.get(i));
                    String nickName = getNickName(nickContents.get(i));
                    String opinionPath = getOpinionPath(reviewLinks.get(i));
                    opinionsList.add(new
                            BookOpinionOpinionsPresenterDto(review,nickName, rate,opinionPath));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        return opinionsList;
    }

    //TODO wyrzucic do innego miejsca, sprawdzanie tytułu czy jest taki sam
    private  boolean namesNotEqual(String  n1, String n2)
    {
        return !(n1.trim().toLowerCase().replace(" ", "").equals(n2.trim().toLowerCase().replace(" ","")));
    }

    private String getNickName(Element element) {
        return element.child(0).text();
    }

    private String getOpinionPath(Element element) {
        return  element.child(3).child(1).attr("href");
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
        String s = response.replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t","");
        return  s;
    }
}
