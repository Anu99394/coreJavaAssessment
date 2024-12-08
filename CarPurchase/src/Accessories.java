import java.util.*;

public class Accessories {
    private String category;
    private Map<String, String> items;

    public Accessories(String category, Map<String, String> items) {
        this.category = category;
        this.items = items;
    }

    public Map<String, String> getItems() {
        return items;
    }

}
