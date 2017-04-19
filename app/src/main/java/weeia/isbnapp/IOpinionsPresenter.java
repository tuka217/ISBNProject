package weeia.isbnapp;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

import weeia.isbnapp.LbModels.BookInfoLbDto;

interface IOpinionsPresenter {
    BookInfoLbDto ProvideOpinions(String bookName) throws ExecutionException, InterruptedException, MalformedURLException;
 }
