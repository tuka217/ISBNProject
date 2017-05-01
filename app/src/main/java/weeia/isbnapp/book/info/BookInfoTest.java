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

    public BookInfoTest(String  bookTitleOrISBN){
        title = "Wszystkie wojny Lary";
        author = "Wojciech Jagielski";
        originalLanguage = "polski";
        originalTitle = "Wszystkie wojny Lary";;
        yearOfFirsEdition = "2015";
        category = "Literatura piękna";
        genre = "literatura faktu";
        form = "reportaż / zbiór reportaży";
        publisherNote = "Opowieści słuchamy z ust Lary. Jej synowie, gruzińscy Czeczeni, ruszają do Polski. Tutaj Szamil i Raszid zostają Europejczykami. Potem wyjeżdżają dalej na Zachód, gdzie zakładają rodziny. Po latach europejski raj zaczyna ich jednak przerażać obcością i duchową pustką. Wciąga ich święta wojna – wojna o wartości ich zdaniem największe. Tak trafiają do Syrii…Lara wciąż walczy o to, by wrócili do domu. To kolejna po „Nocnych wędrowcach” i „Wypalaniu traw” wciągająca opowieść, której sens wykracza poza wydarzenia w niej opisywane i która pozbawia nas komfortu stereotypowego myślenia. [Znak, 2015]";
        picturePath = "http://s.znak.com.pl/files/covers/card/b3/Jagielski_Lara_500pcx.jpg";
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
}
