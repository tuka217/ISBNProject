package weeia.isbnapp.book.info;

/**
 * Created by aga on 17.04.2017.
 */

public interface BookInfo {
    String getTitle();
    String getAuthor();
    String getOriginalLanguage();
    String getOriginalTitle();
    String getYearOfFirsEdition();
    String getCategory();
    String getGenre();
    String getForm();
    String getPublisherNote();
    String getPicturePath();
}
