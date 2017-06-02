package weeia.isbnapp.book.info;

/**
 * Created by aga on 17.04.2017.
 */

public class BookInfoTest implements BookInfo {
    private final String title;
    private final String author;
    private final String originalLanguage;
    private final String originalTitle;
    private final String yearOfFirsEdition;
    private final String category;
    private final String genre;
    private final String form;
    private final String publisherNote;
    private final String picturePath;
    private final String grade;

    public BookInfoTest(String  title,
                        String author,
                        String originalLanguage,
                        String originalTitle,
                        String yearOfFirsEdition,
                        String category,
                        String  genre,
                        String form,
                        String publisherNote,
                        String picturePath,
                        String grade) {
        this.title = title;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.yearOfFirsEdition = yearOfFirsEdition;
        this.genre = genre;
        this.form = form;
        this.author =author;
        this.category =category;
        this.publisherNote = publisherNote;
        this.picturePath = picturePath;
        this.grade= grade;
    }

    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getOriginalLanguage(){
        return originalLanguage;
    }
    public String getOriginalTitle(){
        return originalTitle;
    }
    public String getYearOfFirsEdition(){
        return yearOfFirsEdition;
    }
    public String getCategory(){
        return category;
    }
    public String getGenre(){
        return genre;
    }
    public String getForm(){
        return form;
    }
    public String getPublisherNote(){
        return publisherNote;
    }
    public String getPicturePath(){
        return picturePath;
    }

    @Override
    public String getGrade() {
        return grade;
    }
}
