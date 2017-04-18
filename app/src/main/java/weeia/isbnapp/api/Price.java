package weeia.isbnapp.api;

/**
 * Created by Anna on 2017-04-07.
 */

public class Price {
    private Float amount;
    private String currency;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
