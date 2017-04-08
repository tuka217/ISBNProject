package weeia.isbnapp;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

interface IOpinionsPresenter {
    BookInfoLbDto ProvideOpinions(String bookName) throws ExecutionException, InterruptedException, MalformedURLException;
 }
