package weeia.isbnapp.ShelveModule;

import android.media.Image;

import java.util.Date;

/**
 * Created by MATEUSZ on 22.05.2017.
 */

public class ShelveItem {
    String title;
    String author;
    int releaseYear;
    Date addedDate;
    String picturePath;
    boolean isRead;

    public ShelveItem(String title, String author, int releaseYear, Date addedDate, String picturePath, boolean isRead) {
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
        this.addedDate = addedDate;
        this.picturePath = picturePath;
        this.isRead = isRead;
    }

    public ShelveItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
