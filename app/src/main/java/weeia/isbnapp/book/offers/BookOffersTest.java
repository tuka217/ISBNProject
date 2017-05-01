package weeia.isbnapp.book.offers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aga on 29.04.2017.
 */

public class BookOffersTest implements BookOffers {
    private List<Offer> offers;

    public BookOffersTest(String bookTitle){
        offers = new ArrayList<>();
        offers.add(new OfferTest("Allegro.pl", "http://allegro.pl/wszystkie-wojny-lary-hit-i6761527609.html", "Wszystkie wojny Lary - HIT", "Nowy", 27.47f, 31.46f, "zł", "https://5.allegroimg.com/s400/01b4ee/847ba8d245399dd0271f59343e95"));
        offers.add(new OfferTest("Allegro.pl", "http://allegro.pl/wszystkie-wojny-lary-w-jagielski-i5855329460.html", "WSZYSTKIE WOJNY LARY W. Jagielski", "Nowy", 29.90f, 39.40f, "zł", "https://e.allegroimg.com/s400/01fb45/9f7ddc4046b88ef54153481bfcce"));
    }

    public List<Offer> getOffers(){
        return offers;
    }
}
