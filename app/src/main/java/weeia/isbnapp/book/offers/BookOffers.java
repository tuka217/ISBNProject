package weeia.isbnapp.book.offers;

import java.util.List;

/**
 * Created by aga on 17.04.2017.
 */

public interface BookOffers {
    public List<Offer> getOffers(String bookTitle);
}
