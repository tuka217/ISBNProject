package weeia.isbnapp.lbmodule;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import weeia.isbnapp.book.info.BookInfo;
import weeia.isbnapp.book.opinions.BookOpinion;
import weeia.isbnapp.book.opinions.BookOpinionTest;

public interface IOpinionsPresenter {
    List<BookOpinion> ProvideOpinions(String bookName) throws ExecutionException, InterruptedException, MalformedURLException;
    BookInfo ProvideBookInfo(String bookName) throws ExecutionException, InterruptedException, MalformedURLException;
 }
