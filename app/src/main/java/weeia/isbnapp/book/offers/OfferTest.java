package weeia.isbnapp.book.offers;

/**
 * Created by aga on 29.04.2017.
 */

public class OfferTest implements Offer {
    private final String sourceName;
    private final String url;
    private final String name;
    private final String condition;
    private final Float price;
    private final Float bookAndShipmentPrice;
    private final String currency;
    private final String picturePath;

    OfferTest(String sourceName, String url, String name, String condition, Float price, Float bookAndShipmentPrice, String currency, String picturePath){
        this.sourceName = sourceName;
        this.url = url;
        this.name = name;
        this.condition = condition;
        this.price = price;
        this.bookAndShipmentPrice = bookAndShipmentPrice;
        this.currency = currency;
        this.picturePath = picturePath;
    }

    public String getSourceName(){
        return sourceName;
    }

    public String getUrl(){
        return url;
    }

    public String getName(){
        return name;
    }

    public String getCondition(){
        return condition;
    }

    public Float getPrice(){
        return price;
    }

    public Float getBookAndShipmentPrice(){
        return bookAndShipmentPrice;
    }

    public String getCurrency(){
        return currency;
    }

    public String getPicturePath(){
        return picturePath;
    }
}
