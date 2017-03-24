package weeia.isbnapp.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Anna on 2017-03-22.
 */
@Root(strict = false)
public class Book {
    @Element(name = "description", data = true)
    private String description;

    @Element(name = "reviews_widget", data = true)
    private String reviewsWidget;

    public Book(){}

    public String getDescription()
    {
        return description;
    }

    public String getReviewsWidget()
    {
        return reviewsWidget;
    }
}
