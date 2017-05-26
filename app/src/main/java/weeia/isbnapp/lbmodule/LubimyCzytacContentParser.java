package weeia.isbnapp.lbmodule;

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

import weeia.isbnapp.lbmodule.models.BookDetailsDto;
import weeia.isbnapp.lbmodule.models.BookOpinionOpinionsPresenterDto;
import weeia.isbnapp.lbmodule.models.BookGeneralInfo;
import weeia.isbnapp.lbmodule.models.BookSuggestion;

public class LubimyCzytacContentParser implements  ILubimyCzytacContentProvider {

public BookGeneralInfo GetBookInfo(IContentProvider contentProvder,String bookName) throws IOException, JSONException {

        JsonParser jParser = new JsonParser();
        JSONArray json = jParser.parseJsonArray(contentProvder.ProvideContent());
        BookGeneralInfo bookSmallInfoTemp=null;
        if(json.length()>= 1 ) {
            try {
                JSONObject c = json.getJSONObject(0);
                String suggestedBookName = c.getString("title");
                /*if(namesNotEqual(suggestedBookName, bookName)==true)
                {
                    //TODO dorobic obsługe blędu braku takiej ksiazki
                    return  null;
                }*/
                String authors = parseAuthors(jParser, c.getJSONArray("authors"));
                bookSmallInfoTemp = new BookGeneralInfo(
                 c.getString("id"),
                 authors,
                 c.getString("title"),
                 c.getString("cover"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return bookSmallInfoTemp;
    }

    public ArrayList<BookSuggestion> GetBookInfoWithManyReponses(IContentProvider contentProvder, String bookName) throws IOException, JSONException {

        ArrayList<BookSuggestion> returnList = new
                ArrayList<>();
        JsonParser jParser = new JsonParser();
        JSONArray json = jParser.parseJsonArrayWithManyReposnes(contentProvder.ProvideContent());
      //  BookGeneralInfo bookSmallInfoTemp=null;
        if(json.length()>= 1 ) {
            try {
                for(int i =0;i<json.length();i++){
                    JSONObject c = json.getJSONObject(i);
                    String category = c.getString("category");
                    if(category.equals("book") ) {
                        String suggestedBookName = c.getString("title");
                        String url = c.getString("url");
                        String cover = c.getString("cover");
                        String rating = c.getString("rating");
                        String  id = c.getString("id");
                        String auhtors = parseAuthors(jParser,c.getJSONArray("authors"));
                        BookSuggestion suggestion = new
                                BookSuggestion(id,auhtors, suggestedBookName,cover,rating, url);
                        returnList.add(suggestion);
                    }
                }


                /*if(namesNotEqual(suggestedBookName, bookName)==true)
                {
                    //TODO dorobic obsługe blędu braku takiej ksiazki
                    return  null;
                }*/
                /*String authors = parseAuthors(jParser, c.getString("authors"));
                bookSmallInfoTemp = new BookGeneralInfo(
                        c.getString("id"),
                        authors,
                        c.getString("title"),
                        c.getString("cover"));
                        */

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return returnList;
    }

    private String parseAuthors( JsonParser jParser, JSONArray authorsArray) throws JSONException {
        //JSONArray authorsArray = jParser.parseJsonArray(authors);
        if(authorsArray.length() ==0)
            return "";
        StringBuilder authorsStringBuilder = new StringBuilder();
        for(int i =0;i<authorsArray.length();i++)
        {
            authorsStringBuilder.append(authorsArray.getJSONObject(i).getString("fullname") + ", ");
        }

        return authorsStringBuilder.toString().trim().substring(0,authorsStringBuilder.length()-2);
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
    //TODO narazie zakladamy ze pierwszy tytul ksiazki jest tym oczekiwanym od uzytkownika
    private  boolean namesNotEqual(String  n1, String n2)
    {
        return  false;
        //return !(n1.trim().toLowerCase().replace(" ", "").equals(n2.trim().toLowerCase().replace(" ","")));
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
