package weeia.isbnapp.book.offers;

/**
 * Created by aga on 17.04.2017.
 */

public interface Offer {
    String getSourceName();
    String getUrl();
    String getName();
    String getCondition();
    Float getPrice();
    Float getBookAndShipmentPrice();
    String getCurrency();
    String getPicturePath();
}