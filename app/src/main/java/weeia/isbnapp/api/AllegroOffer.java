package weeia.isbnapp.api;

import java.util.List;

/**
 * Created by Anna on 2017-04-07.
 */

public class AllegroOffer {
    private String url;
    private String name;
    private Prices prices;
    private List<Parameter> parameters;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Prices getPrices() {
        return prices;
    }

    public void setPrices(Prices prices) {
        this.prices = prices;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString()
    {
        return "name: " + this.name + "url: " + this.url;
    }

}
