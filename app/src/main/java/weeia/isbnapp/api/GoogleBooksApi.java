package weeia.isbnapp.api;

import java.util.concurrent.ExecutionException;
import weeia.isbnapp.background.BookTitleTask;

/**
 * Created by MATEUSZ on 20.04.2017.
 */

public class GoogleBooksApi  {

    public  String GetBookTitleByISBN(String isbn) {
        BookTitleTask task = new BookTitleTask();
        String title = null;
        try {
            title = task.execute(isbn).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
        return title;

    }
}
