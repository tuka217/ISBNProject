package weeia.isbnapp.LbModels;

public class BookOpinionOpinionsPresenterDto {
    public  String text;
    public  String nick;
    public  double rate;
    public  String opinionTitle;
    public  String opinionPath;

    public BookOpinionOpinionsPresenterDto(String review, String authorMocked, double bookRate,String path) {
        text = review;
        nick = authorMocked;
        rate = bookRate;
        if(text.length()>=10)
            opinionTitle  = text.substring(0,10);
        else
            opinionTitle = "";
        opinionPath = path;
    }
}
