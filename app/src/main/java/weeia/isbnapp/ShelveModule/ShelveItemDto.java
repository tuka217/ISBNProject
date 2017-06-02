package weeia.isbnapp.ShelveModule;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by MATEUSZ on 26.05.2017.
 */

public class ShelveItemDto implements Serializable{
    private String ISBN;
    private int ID;
    private String Title;
    private String Author;
    private String Publisher;
    private java.sql.Date  PublishDate;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public Date getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(Date publishDate) {
        PublishDate = publishDate;
    }

    public ShelveItemDto(String ISBN, int ID, String title, String author, String publisher, Date publishDate) {
        this.ISBN = ISBN;
        this.ID = ID;
        Title = title;
        Author = author;
        Publisher = publisher;
        PublishDate = publishDate;
    }
}
