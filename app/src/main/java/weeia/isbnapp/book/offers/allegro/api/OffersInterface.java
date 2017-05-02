package weeia.isbnapp.book.offers.allegro.api;

import java.util.List;

/**
 * Created by Anna on 2017-04-07.
 */

public interface OffersInterface {
    public List<AllegroOffer> getOffers();

    public void setOffers(List<AllegroOffer> offers);
}
