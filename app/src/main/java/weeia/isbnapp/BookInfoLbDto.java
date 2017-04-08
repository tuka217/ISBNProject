package weeia.isbnapp;
import java.util.ArrayList;

public class BookInfoLbDto {

    public ArrayList<BookOpinionOpinionsPresenterDto> bookOpinions;
    public double bookRate;
    public  String bookId;

    public BookInfoLbDto(String _bookId, ArrayList<BookOpinionOpinionsPresenterDto> _bookOpinions, double _bookRate) {
        bookId = _bookId;
        bookOpinions = _bookOpinions;
        bookRate = _bookRate;
    }

    public BookInfoLbDto() {
        bookOpinions = new ArrayList<>();
        bookRate = 0;
        bookId = "";
    }
}
