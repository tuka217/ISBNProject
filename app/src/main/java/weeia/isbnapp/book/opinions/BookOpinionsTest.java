package weeia.isbnapp.book.opinions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aga on 19.04.2017.
 */

public class BookOpinionsTest implements BookOpinions {
    private List<BookOpinion> bookOpinions;
    private List<BookGrade> bookGrades;
    private final String bookTitleOrISBN;

    public BookOpinionsTest(String bookTitleOrISBN){
        this.bookTitleOrISBN = bookTitleOrISBN;
        downloadBookGrades();
        downloadBookOpinions();
    }

    private void downloadBookGrades(){
        bookGrades = new ArrayList<>();
        bookGrades.add(new BookGradeTest("4.99", "6.0", "BiblioNetka.pl"));
        bookGrades.add(new BookGradeTest("7.9", "10.0", "LubimyCzytać.pl"));
    }

    private void downloadBookOpinions(){
        bookOpinions = new ArrayList<>();
        String opinion = "Jagielski buduje swoją opowieść – a właściwie opowieść cierpiącej Lary – na wielu poziomach. Udziela bohaterce głosu, ale dba o to, by czytelnik był w stanie zrozumieć wszystkie kulturowo-polityczno-społeczne zawiłości. Z jednej strony to opowieść o życiu na pograniczu i budowaniu tam swojej tożsamości. Lara pochodzi bowiem z górskiej gruzińskiej doliny, w której osiedlali się Czeczeni – mieli blisko. Z drugiej – o migracji. Lara wyjechała za mężem do Groznego, ale wojna niejako zmusiła ją...";
        bookOpinions.add(new BookOpinionTest("BiblioNetka.pl", "Do Europy i z powrotem", opinion, "https://www.biblionetka.pl/art.aspx?id=961961","",""));
        opinion = "W tej kameralnej, niezbyt obszernej książce, zmieściło się wiele, żeby nie powiedzieć wszystko: bezwarunkowa miłość, cierpienie i bezsilność, bezsens i okrucieństwo wojny, destrukcyjny charakter fanatyzmu. Jedna opowieść, która zawiera całe spektrum nieszczęść. „Wszystkie wojny Lary” to bowiem opowieść o kobiecie, która poniosła najgorsze z możliwych konsekwencji, dwóch wielkich, współczesnych wojen: tej czeczeńskiej i tej świętej, syryjskiej. Historia matki, która zrobiła wszystko, żeby uchronić synów przed nieszczęściem, ale poniosła sromotną klęskę. Reportaż Jagielskiego to bardzo rzetelna, wyważona opowieść o życiu i śmierci, statystycznie niemożliwym dramacie.";
        bookOpinions.add(new BookOpinionTest("LubimyCzytać.pl", "Święta wojna świętej matki", opinion, "http://lubimyczytac.pl/oficjalne-recenzje-ksiazek/6243/swieta-wojna-swietej-matki","",""));
    }

    public List<BookOpinion> getBookOpinions(){
        return bookOpinions;
    }
    public void setBookOpinions(List<BookOpinion> _bookOpinions ){
         bookOpinions = _bookOpinions;
    }
    public void setBookGrades(List<BookGrade> _bookGrades)
    {
        bookGrades = _bookGrades;
    }
    public List<BookGrade> getBookGrades(){
        return bookGrades;
    }

    @Override
    public void addBookGrade(BookGrade _grade) {
        bookGrades.add(_grade);
    }
}