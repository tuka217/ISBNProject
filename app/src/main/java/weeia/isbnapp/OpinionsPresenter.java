package weeia.isbnapp;

import android.os.AsyncTask;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import weeia.isbnapp.LbModels.BookDetailsDto;
import weeia.isbnapp.LbModels.BookInfoLbDto;
import weeia.isbnapp.LbModels.BookOpinionOpinionsPresenterDto;
import weeia.isbnapp.LbModels.BookGeneralInfo;

public class OpinionsPresenter implements   IOpinionsPresenter {

    @Override
    public BookInfoLbDto ProvideOpinions(String bookName) throws ExecutionException, InterruptedException, MalformedURLException {
        String url = new LbUrlBuilder()
                .AnSuggesionUrl()
                .WithBookName(bookName)
                .Build();
        return new GetLbDataTask().execute(url,bookName).get();
    }

    class GetLbDataTask extends AsyncTask<String, Void, BookInfoLbDto> {

        protected BookInfoLbDto doInBackground(String... params) {
            BookInfoLbDto bookInfoLbDto = new BookInfoLbDto();
                try {
                    ArrayList<BookOpinionOpinionsPresenterDto> bookOpinions = new ArrayList<>();
                    IContentProvider contentProvider = new CustomHttpClient(new URL(params[0]));
                    LubimyCzytacContentParser lbContentParser = new LubimyCzytacContentParser();

                    BookGeneralInfo bookSmallInfoTemp = lbContentParser.GetBookInfo(contentProvider,params[1]);
                    contentProvider  = new CustomHttpClient(new URL(new LbUrlBuilder()
                            .ADetailBookPageUrl()
                            .WithBookId(bookSmallInfoTemp.id)
                            .Build()));

                    BookDetailsDto bookDetails = lbContentParser.GetBookDetails(contentProvider);
                    contentProvider = new CustomHttpClient(new URL(new LbUrlBuilder()
                            .ABookReviewUrl()
                            .WithBookId(bookSmallInfoTemp.id)
                            .WithReviewCount("10")
                            .Build()));
                    bookOpinions.addAll(lbContentParser.GetBookOpinions(contentProvider));

                    bookInfoLbDto = new BookInfoLbDto(bookOpinions, bookDetails);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            return  bookInfoLbDto;
            }
        }
 }

