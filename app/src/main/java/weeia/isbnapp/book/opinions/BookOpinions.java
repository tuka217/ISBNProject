package weeia.isbnapp.book.opinions;

import java.util.List;

/**
 * Created by aga on 17.04.2017.
 */

public interface BookOpinions {
    List<BookOpinion> getBookOpinions();
    List<BookGrade> getBookGrades();

    void addBookGrade(BookGrade _grade);
}
