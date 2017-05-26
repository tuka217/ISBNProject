package weeia.isbnapp.lbmodule;

import android.os.AsyncTask;

import java.lang.reflect.Array;
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
import weeia.isbnapp.lbmodule.models.BookSuggestion;

public class OpinionsPresenter implements   IOpinionsPresenter {

    @Override
    public List<BookOpinion> ProvideOpinions(String bookName,BookGeneralInfo bookGeneralInfo) throws ExecutionException, InterruptedException, MalformedURLException {
        String url = new LbUrlBuilder()
                .AnSuggesionUrl()
                .WithBookName(bookName)
                .Build();
        return new GetLbOpinionsTask().execute(url,bookName,bookGeneralInfo).get();
    }

    public BookInfo ProvideBookInfo(String bookName,BookGeneralInfo bookGeneralInfo) throws ExecutionException, InterruptedException, MalformedURLException {
        String url = new LbUrlBuilder()
                .AnSuggesionUrl()
                .WithBookName(bookName)
                .Build();
        return new GetLbBookInfoTask().execute(url,bookName,bookGeneralInfo).get();
    }

    @Override
    public ArrayList<BookSuggestion> ProvideBookSuggestion(String bookName) throws ExecutionException, InterruptedException, MalformedURLException {
        String url = new LbUrlBuilder()
                .AnSuggesionUrl()
                .WithBookName(bookName)
                .Build();
        ArrayList<BookSuggestion> suggestions = new GetLbBookInfoTaskWithManyBooks().execute(url,bookName).get();
        return suggestions;
    }

    class GetLbOpinionsTask extends AsyncTask<Object, Void, List<BookOpinion>> {

        protected List<BookOpinion> doInBackground(Object... params) {
            List<BookOpinion> bookOpinionsList = new ArrayList<BookOpinion>();
                try {
                    ArrayList<BookOpinionOpinionsPresenterDto> bookOpinions = new ArrayList<>();
                    IContentProvider contentProvider = new CustomHttpClient(new URL((String)params[0]));
                    LubimyCzytacContentParser lbContentParser = new LubimyCzytacContentParser();

                    BookGeneralInfo bookSmallInfoTemp = (BookGeneralInfo)params[2];

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

    class GetLbBookInfoTask extends AsyncTask<Object, Void, BookInfo> {

        protected BookInfo doInBackground(Object... params) {
            BookInfo bookInfo = new EmptyBookInfo();
            try {

                IContentProvider contentProvider;
                LubimyCzytacContentParser lbContentParser = new LubimyCzytacContentParser();

                //BookGeneralInfo bookGeneralInfo = lbContentParser.GetBookInfo(contentProvider,params[1]);

                BookGeneralInfo bookGeneralInfo = BookGeneralInfo.class.cast(params[2]);
                contentProvider  = new CustomHttpClient(new URL(new LbUrlBuilder()
                        .ADetailBookPageUrl()
                        .WithBookId(bookGeneralInfo.id)
                        .Build()));

                BookDetailsDto bookDetails = lbContentParser.GetBookDetails(contentProvider);

                bookInfo = new BookInfoTest( bookGeneralInfo.title,bookGeneralInfo.authors,bookDetails.language,
                        bookDetails.title, bookDetails.publishDate, bookDetails.category, bookDetails.category,
                        bookDetails.category, bookDetails.description, bookGeneralInfo.coverUrl, Double.toString(bookDetails.rate));
                return  bookInfo;
            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return  bookInfo;
        }

    }


    class GetLbBookInfoTaskWithManyBooks extends AsyncTask<String, Void,  ArrayList<BookSuggestion>> {

        protected  ArrayList<BookSuggestion> doInBackground(String... params) {
            ArrayList<BookSuggestion> suggestions = new ArrayList<>();
            try {

                IContentProvider contentProvider = new CustomHttpClient(new URL(params[0]));
                LubimyCzytacContentParser lbContentParser = new LubimyCzytacContentParser();
                suggestions.addAll(lbContentParser.GetBookInfoWithManyReponses(contentProvider,params[1]));
            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return  suggestions;
        }

    }
 }

