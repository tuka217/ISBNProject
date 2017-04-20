package weeia.isbnapp.lbmodule;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import weeia.isbnapp.book.info.BookInfo;
import weeia.isbnapp.book.opinions.BookOpinionTest;

interface IOpinionsPresenter {
    List<BookOpinionTest> ProvideOpinions(String bookName) throws ExecutionException, InterruptedException, MalformedURLException;
    BookInfo ProvideBookInfo(String bookName) throws ExecutionException, InterruptedException, MalformedURLException;
 }
