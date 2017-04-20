package weeia.isbnapp.lbmodule;

import android.os.AsyncTask;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import weeia.isbnapp.book.info.BookInfo;
import weeia.isbnapp.book.info.BookInfoTest;
import weeia.isbnapp.book.opinions.BookOpinionTest;
import weeia.isbnapp.lbmodule.models.BookDetailsDto;
import weeia.isbnapp.lbmodule.models.BookOpinionOpinionsPresenterDto;
import weeia.isbnapp.lbmodule.models.BookGeneralInfo;

public class OpinionsPresenter implements   IOpinionsPresenter {

    @Override
    public List<BookOpinionTest> ProvideOpinions(String bookName) throws ExecutionException, InterruptedException, MalformedURLException {
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

    class GetLbOpinionsTask extends AsyncTask<String, Void, List<BookOpinionTest>> {

        protected List<BookOpinionTest> doInBackground(String... params) {
            List<BookOpinionTest> bookOpinionsList = new ArrayList<BookOpinionTest>();
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
                    int i =10;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
             return  bookOpinionsList;
            }

            private  List<BookOpinionTest> Map(List<BookOpinionOpinionsPresenterDto> lista)
            {
                List<BookOpinionTest> mappedList = new ArrayList<BookOpinionTest>();
                for (BookOpinionOpinionsPresenterDto  item:lista ) {
                    mappedList.add(new BookOpinionTest("www.lubimyczytac.pl", item.opinionTitle, item.text, item.opinionPath, String.valueOf(item.rate), item.nick));
                }
                return  mappedList;
            }
        }

    class GetLbBookInfoTask extends AsyncTask<String, Void, BookInfo> {

        protected BookInfo doInBackground(String... params) {
            BookInfoTest bookInfo = null;
            try {

                IContentProvider contentProvider = new CustomHttpClient(new URL(params[0]));
                LubimyCzytacContentParser lbContentParser = new LubimyCzytacContentParser();

                BookGeneralInfo bookSmallInfoTemp = lbContentParser.GetBookInfo(contentProvider,params[1]);
                contentProvider  = new CustomHttpClient(new URL(new LbUrlBuilder()
                        .ADetailBookPageUrl()
                        .WithBookId(bookSmallInfoTemp.id)
                        .Build()));

                BookDetailsDto bookDetails = lbContentParser.GetBookDetails(contentProvider);

                bookInfo = new BookInfoTest( bookSmallInfoTemp.title,bookSmallInfoTemp.authors,bookDetails.language,
                        bookDetails.title, bookDetails.publishDate, bookDetails.category, bookDetails.category,
                        bookDetails.category, bookDetails.description, bookSmallInfoTemp.coverUrl);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return  bookInfo;
        }

    }
 }

