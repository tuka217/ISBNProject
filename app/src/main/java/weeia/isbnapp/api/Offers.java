package weeia.isbnapp.api;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Anna on 2017-04-07.
 */

public class Offers implements Serializable, OffersInterface {
    private List<AllegroOffer> offers;

    public List<AllegroOffer> getOffers() {
        return offers;
    }

    public void setOffers(List<AllegroOffer> offers) {
        this.offers = offers;
    }

}
