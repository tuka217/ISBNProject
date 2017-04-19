package weeia.isbnapp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import weeia.isbnapp.LbModels.BookDetailsDto;
import weeia.isbnapp.LbModels.BookOpinionOpinionsPresenterDto;
import weeia.isbnapp.LbModels.BookGeneralInfo;

interface  ILubimyCzytacContentProvider
{
    BookGeneralInfo GetBookInfo(IContentProvider contentProvder, String bookName) throws IOException;
    ArrayList<BookOpinionOpinionsPresenterDto> GetBookOpinions(IContentProvider contentProvder) throws MalformedURLException;
    BookDetailsDto GetBookDetails(IContentProvider contentProvder);

}
