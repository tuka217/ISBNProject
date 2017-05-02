package weeia.isbnapp.book.opinions;

/**
 * Created by aga on 19.04.2017.
 */

public class BookOpinionTest implements BookOpinion {
    private final String sourceSiteName;
    private final String opinionTitle;
    private final String opinionFragment;
    private final String fullOpinionPath;
    private final String rate;
    private final String nick;

    public BookOpinionTest(String sourceSiteName, String opinionTitle, String opinionFragment, String fullOpinionPath, String rate, String nick){
        this.sourceSiteName = sourceSiteName;
        this.opinionTitle = opinionTitle;
        this.opinionFragment = opinionFragment;
        this.fullOpinionPath = fullOpinionPath;
        this.rate = rate;
        this.nick = nick;
    }

    public String getSourceSiteName(){
        return sourceSiteName;
    }

    public String getOpinionTitle(){
        return opinionTitle;
    }

    public String getOpinionFragment(){
        return opinionFragment;
    }

    public String getFullOpinionPath(){
        return fullOpinionPath;
    }

    public String getNick() {
        return  nick;
    }

    public String getRate() {
        return  rate;
    }
}