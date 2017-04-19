package weeia.isbnapp.LbModels;
import java.util.ArrayList;

public class BookInfoLbDto {

    private  BookDetailsDto bookDetails;
    public ArrayList<BookOpinionOpinionsPresenterDto> bookOpinions;
    public double bookRate;
    public  String bookId;

    public BookInfoLbDto() {
        bookOpinions = new ArrayList<>();
        bookRate = 0;
        bookId = "";
    }

    public BookInfoLbDto(ArrayList<BookOpinionOpinionsPresenterDto> _bookOpinions, BookDetailsDto _bookDetails) {
        bookDetails =_bookDetails;
        bookOpinions = _bookOpinions;
    }
}
