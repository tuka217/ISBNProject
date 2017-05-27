package weeia.isbnapp.lbmodule;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import weeia.isbnapp.book.info.BookInfo;
import weeia.isbnapp.book.opinions.BookOpinion;
import weeia.isbnapp.book.opinions.BookOpinionTest;
import weeia.isbnapp.lbmodule.models.BookGeneralInfo;
import weeia.isbnapp.lbmodule.models.BookSuggestion;

public interface IOpinionsPresenter {
    List<BookOpinion> ProvideOpinions(String bookName,BookGeneralInfo generalInfo) throws ExecutionException, InterruptedException, MalformedURLException;
    BookInfo ProvideBookInfo(String bookName,BookGeneralInfo bookGeneralInfo) throws ExecutionException, InterruptedException, MalformedURLException;
    ArrayList<BookSuggestion> ProvideBookSuggestion(String bookName) throws ExecutionException, InterruptedException, MalformedURLException;
 }
