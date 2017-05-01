package weeia.isbnapp.book.opinions;

/**
 * Created by aga on 19.04.2017.
 */

public class BookOpinionTest implements BookOpinion{
    private final String sourceSiteName;
    private final String opinionTitle;
    private final String opinionFragment;
    private final String fullOpinionPath;

    public BookOpinionTest(String sourceSiteName, String opinionTitle, String opinionFragment, String fullOpinionPath){
        this.sourceSiteName = sourceSiteName;
        this.opinionTitle = opinionTitle;
        this.opinionFragment = opinionFragment;
        this.fullOpinionPath = fullOpinionPath;
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
}