package weeia.isbnapp.lbmodule;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import weeia.isbnapp.lbmodule.models.BookDetailsDto;
import weeia.isbnapp.lbmodule.models.BookOpinionOpinionsPresenterDto;
import weeia.isbnapp.lbmodule.models.BookGeneralInfo;

interface  ILubimyCzytacContentProvider
{
    BookGeneralInfo GetBookInfo(IContentProvider contentProvder, String bookName) throws IOException, JSONException;
    ArrayList<BookOpinionOpinionsPresenterDto> GetBookOpinions(IContentProvider contentProvder) throws MalformedURLException;
    BookDetailsDto GetBookDetails(IContentProvider contentProvder);

}
