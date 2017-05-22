package weeia.isbnapp.lbmodule;

import android.os.AsyncTask;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import weeia.isbnapp.book.info.BookInfo;
import weeia.isbnapp.book.info.BookInfoTest;
import weeia.isbnapp.book.info.EmptyBookInfo;
import weeia.isbnapp.book.opinions.BookOpinion;
import weeia.isbnapp.book.opinions.BookOpinionTest;
import weeia.isbnapp.lbmodule.models.BookDetailsDto;
import weeia.isbnapp.lbmodule.models.BookOpinionOpinionsPresenterDto;
import weeia.isbnapp.lbmodule.models.BookGeneralInfo;

public class OpinionsPresenter implements   IOpinionsPresenter {

    @Override
    public List<BookOpinion> ProvideOpinions(String bookName) throws ExecutionException, InterruptedException, MalformedURLException {
        String url = new LbUrlBuilder()
                .AnSuggesionUrl()
                .WithBookName(bookName)
                .Build();
        return new GetLbOpinionsTask().execute(url,bookName).get();
    }

    public BookInfo ProvideBookInfo(String bookName) throws ExecutionException, InterruptedException, MalformedURLException {
        String url = new LbUrlBuilder()
                .AnSuggesionUrl()
                .WithBookName(bookName)
                .Build();
        return new GetLbBookInfoTask().execute(url,bookName).get();
    }

    class GetLbOpinionsTask extends AsyncTask<String, Void, List<BookOpinion>> {

        protected List<BookOpinion> doInBackground(String... params) {
            List<BookOpinion> bookOpinionsList = new ArrayList<BookOpinion>();
                try {
                    ArrayList<BookOpinionOpinionsPresenterDto> bookOpinions = new ArrayList<>();
                    IContentProvider contentProvider = new CustomHttpClient(new URL(params[0]));
                    LubimyCzytacContentParser lbContentParser = new LubimyCzytacContentParser();

                    BookGeneralInfo bookSmallInfoTemp = lbContentParser.GetBookInfo(contentProvider,params[1]);

                    contentProvider = new CustomHttpClient(new URL(new LbUrlBuilder()
                            .ABookReviewUrl()
                            .WithBookId(bookSmallInfoTemp.id)
                            .WithReviewCount("10")
                            .Build()));
                    bookOpinions.addAll(lbContentParser.GetBookOpinions(contentProvider));
                    bookOpinionsList = this.Map(bookOpinions);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
             return  bookOpinionsList;
            }

            private  List<BookOpinion> Map(List<BookOpinionOpinionsPresenterDto> lista)
            {
                List<BookOpinion> mappedList = new ArrayList<BookOpinion>();
                for (BookOpinionOpinionsPresenterDto  item:lista ) {
                    mappedList.add(new BookOpinionTest("www.lubimyczytac.pl", item.opinionTitle, item.text, item.opinionPath, String.valueOf(item.rate), item.nick));
                }
                return  mappedList;
            }
        }

    class GetLbBookInfoTask extends AsyncTask<String, Void, BookInfo> {

        protected BookInfo doInBackground(String... params) {
            BookInfo bookInfo = new EmptyBookInfo();
            try {

                IContentProvider contentProvider = new CustomHttpClient(new URL(params[0]));
                LubimyCzytacContentParser lbContentParser = new LubimyCzytacContentParser();

                BookGeneralInfo bookGeneralInfo = lbContentParser.GetBookInfo(contentProvider,params[1]);
                contentProvider  = new CustomHttpClient(new URL(new LbUrlBuilder()
                        .ADetailBookPageUrl()
                        .WithBookId(bookGeneralInfo.id)
                        .Build()));

                BookDetailsDto bookDetails = lbContentParser.GetBookDetails(contentProvider);

                bookInfo = new BookInfoTest( bookGeneralInfo.title,bookGeneralInfo.authors,bookDetails.language,
                        bookDetails.title, bookDetails.publishDate, bookDetails.category, bookDetails.category,
                        bookDetails.category, bookDetails.description, bookGeneralInfo.coverUrl, Double.toString(bookDetails.rate));

            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return  bookInfo;
        }

    }
 }

