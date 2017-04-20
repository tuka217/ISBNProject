package weeia.isbnapp.book.offers;

/**
 * Created by aga on 17.04.2017.
 */

public interface Offer {
    public String getSourceName();
    public String getUrl();
    public String getName();
    public Float getPrice();
    public Float getBookAndShipmentPrice();
    public String getCurrency();
    public String getPicturePath();
}