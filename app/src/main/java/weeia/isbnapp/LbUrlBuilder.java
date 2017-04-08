package weeia.isbnapp;

import android.net.Uri;
import java.net.MalformedURLException;

class LbUrlBuilder {

    Uri.Builder builder = null;
    public LbUrlBuilder ADetailBookPageUrl() throws MalformedURLException {
        builder = new Uri.Builder();
        builder.scheme("http")
                .authority("lubimyczytac.pl")
                .appendPath("ksiazka");
        return  this;
    }

    public LbUrlBuilder ABookReviewUrl() throws MalformedURLException {
        builder = new Uri.Builder();
        builder.scheme("http")
                .authority("lubimyczytac.pl")
                .appendPath("ajax")
                .appendPath("getBookReviews");
        return  this;
    }

    public LbUrlBuilder AnSuggesionUrl() throws MalformedURLException {
        builder = new Uri.Builder();
        builder.scheme("http")
                .authority("lubimyczytac.pl")
                .appendPath("searcher")
                .appendPath("getsuggestions");
        return  this;
    }

    public LbUrlBuilder WithBookName(String bookName) {
        if(builder== null)
            throw new IllegalArgumentException();
        builder.appendQueryParameter("phrase", bookName);
        return  this;
    }

    public LbUrlBuilder WithBookId(String bookId) {
        if(builder== null)
            throw new IllegalArgumentException();
        builder.appendPath(bookId);
        return  this;
    }

    public LbUrlBuilder WithReviewCount(String numOfReviews) {
        if(builder== null)
            throw new IllegalArgumentException();
        builder.appendPath(numOfReviews);
        return  this;
    }

    public String Build(){
        return  builder.build().toString();
    }
}
