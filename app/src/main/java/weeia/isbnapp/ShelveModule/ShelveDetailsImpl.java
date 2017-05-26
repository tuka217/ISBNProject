package weeia.isbnapp.ShelveModule;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import weeia.isbnapp.book.opinions.BookOpinion;
import weeia.isbnapp.lbmodule.models.BookInfoLbDto;

/**
 * Created by MATEUSZ on 22.05.2017.
 */

public class ShelveDetailsImpl implements IShelveDetails {
    @Override
    public List<BookInfoLbDto> getBooks() {
        return null;
    }

    @Override
    public void putBookToShelve(BookInfoLbDto book) {

    }

    @Override
    public void markBookAsRead(int bookId) {

    }

    @Override
    public void addOpinion(BookOpinion opinion, int bookId) {

    }

    @Override
    public void removeFromShelf(int bookId) {

    }
}
