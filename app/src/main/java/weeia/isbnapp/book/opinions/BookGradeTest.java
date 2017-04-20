package weeia.isbnapp.book.opinions;

/**
 * Created by aga on 19.04.2017.
 */

public class BookGradeTest implements BookGrade {
    private final Double bookGrade;
    private final Double maxGrade;
    private final String sourceSite;

    public BookGradeTest(Double bookGrade, Double maxGrade, String sourceSite){
        this.bookGrade = bookGrade;
        this.maxGrade = maxGrade;
        this.sourceSite = sourceSite;
    }

    public Double getBookGrade(){
        return bookGrade;
    }

    public Double getMaxGrade(){
        return maxGrade;
    }

    public String getSourceSite(){
        return sourceSite;
    }
}
