package weeia.isbnapp.book.info;

/**
 * Created by aga on 17.04.2017.
 */

public interface BookInfo {
    public String getTitle();
    public String getAuthor();
    public String getOriginalLanguage();
    public String getOriginalTitle();
    public String getYearOfFirsEdition();
    public String getCategory();
    public String getGenre();
    public String getForm();
    public String getPublisherNote();
    public String getPicturePath(); //in phone storage
}
