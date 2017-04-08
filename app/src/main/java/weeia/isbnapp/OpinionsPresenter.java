package weeia.isbnapp;

import android.os.AsyncTask;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class OpinionsPresenter implements   IOpinionsPresenter {


    @Override
    public BookInfoLbDto ProvideOpinions(String bookName) throws ExecutionException, InterruptedException, MalformedURLException {
        String url = new LbUrlBuilder()
                .AnSuggesionUrl()
                .WithBookName(bookName)
                .Build();
        return new GetLbDataTask().execute(url).get();
    }

    class GetLbDataTask extends AsyncTask<String, Void, BookInfoLbDto> {

        protected BookInfoLbDto doInBackground(String... urls) {
            BookInfoLbDto bookInfoLbDto = new BookInfoLbDto();
                try {
                    ArrayList<BookOpinionOpinionsPresenterDto> bookOpinions = new ArrayList<>();
                    LubimyCzytacHttpResponseParser client = new LubimyCzytacHttpResponseParser();
                    String bookId = client.GetBookId(urls[0]);

                    double bookRate = client.GetBookRate(new LbUrlBuilder()
                            .ADetailBookPageUrl()
                            .WithBookId(bookId)
                            .Build());

                    bookOpinions.addAll(client.GetBookOpinions(new LbUrlBuilder()
                            .ABookReviewUrl()
                            .WithBookId(bookId)
                            .WithReviewCount("10")
                            .Build()));
                    bookInfoLbDto = new BookInfoLbDto(bookId, bookOpinions, bookRate);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            return  bookInfoLbDto;
            }
        }
 }

