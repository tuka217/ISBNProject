package weeia.isbnapp;

public class BookOpinionOpinionsPresenterDto {
    public  String text;
    public  String nick;
    public  double rate;

    public BookOpinionOpinionsPresenterDto(String review, String authorMocked, double bookRate) {
        text = review;
        nick = authorMocked;
        rate = bookRate;
    }
}
