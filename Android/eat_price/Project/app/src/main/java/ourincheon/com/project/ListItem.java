package ourincheon.com.project;

/**
 * Created by kim on 2018-02-01.
 */
/*두번째 기능인 전체 메뉴 리스트를 펼쳤을 때 들어가는 하위 리스트에 집어 넣기 위한 문자열 값 menu에는 메뉴이름이 price에는 가격이 들어간다.
getmenu와 getprice를 호출하여 각각의 값을 받아 사용하기 위해 생성. */
public class ListItem {
    private String menu,price;
    public ListItem(String menu,String price){
        this.menu = menu;
        this.price = price;
    }

    public String getMenu(){
        return menu;
    }

    public String getPrice(){
        return price;
    }
}
