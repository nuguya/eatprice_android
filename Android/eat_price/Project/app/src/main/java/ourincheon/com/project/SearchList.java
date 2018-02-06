package ourincheon.com.project;

/**
 * Created by kim on 2018-02-02.
 */

public class SearchList {
    private String menu,price;
    private int icon;

    public SearchList(String menu, String price, int icon) {
        this.menu = menu;
        this.price = price;
        this.icon = icon;
    }

    public String getMenu() {
        return menu;
    }

    public String getPrice() {
        return price;
    }

    public int getIcon() {
        return icon;
    }
}
