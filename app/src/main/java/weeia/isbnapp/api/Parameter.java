package weeia.isbnapp.api;

import java.util.List;

/**
 * Created by Anna on 2017-04-07.
 */

public class Parameter {
    private String name;
    private List<String> values;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
