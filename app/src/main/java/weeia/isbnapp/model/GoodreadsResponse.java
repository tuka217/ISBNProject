package weeia.isbnapp.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Anna on 2017-03-22.
 */
@Root(strict = false)
public class GoodreadsResponse {
    @Element(name = "book")
    private Book book;

    public GoodreadsResponse(){}

    public Book getBook()
    {
        return book;
    }
}
