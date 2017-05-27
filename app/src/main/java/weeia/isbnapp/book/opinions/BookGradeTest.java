package weeia.isbnapp.book.opinions;

/**
 * Created by aga on 19.04.2017.
 */

public class BookGradeTest implements BookGrade {
    private final String bookGrade;
    private final String maxGrade;
    private final String sourceSite;

    public BookGradeTest(String bookGrade, String maxGrade, String sourceSite){

        this.bookGrade = bookGrade;
        this.maxGrade = maxGrade;
        this.sourceSite = sourceSite;
    }

    public String getBookGrade(){
        return bookGrade;
    }

    public String getMaxGrade(){
        return maxGrade;
    }

    public String getSourceSite(){
        return sourceSite;
    }
}
