package weeia.isbnapp.ShelveModule;

import java.util.Date;
import java.util.List;

import weeia.isbnapp.book.info.BookInfo;
import weeia.isbnapp.book.opinions.BookOpinion;
import weeia.isbnapp.lbmodule.models.BookInfoLbDto;

/**
 * Created by MATEUSZ on 02.05.2017.
 */

public interface IShelveDetails {
    List<BookInfoLbDto> getBooks();
    void putBookToShelve(BookInfoLbDto book);
    void markBookAsRead(int bookId);
    void addOpinion(BookOpinion opinion, int bookId);
    void removeFromShelf(int bookId);

}
