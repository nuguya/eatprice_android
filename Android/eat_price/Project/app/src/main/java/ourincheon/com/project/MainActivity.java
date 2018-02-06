package ourincheon.com.project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    /*위젯 초기화*/
    EditText input_min,input_max;
    Button sodam_btn,tomato_btn,gozip_btn,salady_btn,kimcheon_btn,papaies_btn,gongssi_btn,shanchi_btn,bongus_btn;
    ExpandableListView listmain;

    private ExpandableListAdapter listAdapter; //2번째 tab화면을 구성하는 ExpandableList를 만들어주는 Adapter변수 선언
    protected static List<ListItem> sodam,tomato,gozip,salady,kimcheon,papaies,gongssi,shanchi,bongus;
    private ArrayList<String> listDataHeader; //expandable listview에서 parent리스트를 구성하는 배열리스트
    private HashMap<String,List<ListItem>> listHash; //HashMap을 설정하여 키 값을 통해 하위리스트를 찾을 수 있게함.
    //버튼 클릭여부를 판별하기 위한 boolean형 변수 모두 false로 초기화 후 클릭 시 true값을 받아서 사용
    protected static boolean sodam_click=false,tomato_click=false,gozip_click=false,salady_click=false,kimcheon_click=false,papaies_click=false,gongssi_click=false,shanchi_click=false,bongus_click=false;

    @Override //메뉴바를 설정하기 위한 메소드
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_1,menu);
        return true;
    }

    @Override //oncreate메소드에서는 위젯 인스턴스화 및 리스너 등록 화면을 구성하는 탭을 생성한다.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*초기화 ... */
        sodam_btn = (Button)findViewById(R.id.sodam);
        tomato_btn= (Button)findViewById(R.id.tomato);
        gozip_btn = (Button)findViewById(R.id.gozip);
        salady_btn = (Button)findViewById(R.id.salady);
        kimcheon_btn= (Button)findViewById(R.id.kimcheon);
        papaies_btn = (Button)findViewById(R.id.papaies);
        gongssi_btn = (Button)findViewById(R.id.gong);
        shanchi_btn = (Button)findViewById(R.id.shanchi);
        bongus_btn = (Button)findViewById(R.id.bongus);
        Button search = (Button)findViewById(R.id.search);
        input_min = (EditText)findViewById(R.id.minprice);
        input_max = (EditText)findViewById(R.id.maxprice);


        /*button onClick구현 ... */
        sodam_btn.setOnClickListener(this);
        tomato_btn.setOnClickListener(this);
        gozip_btn.setOnClickListener(this);
        salady_btn.setOnClickListener(this);
        kimcheon_btn.setOnClickListener(this);
        papaies_btn.setOnClickListener(this);
        gongssi_btn.setOnClickListener(this);
        bongus_btn.setOnClickListener(this);
        shanchi_btn.setOnClickListener(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SearchListActivity.class);
                intent.putExtra("min",input_min.getText().toString());
                intent.putExtra("max",input_max.getText().toString());
                startActivity(intent);
            }
        });

        listmain = (ExpandableListView)findViewById(R.id.menu);
        initData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        listmain.setAdapter(listAdapter);




        /*탭을 생성하는 코드 (32~53)*/
        TabHost tbhost1 = (TabHost)findViewById(R.id.tabhost1);
        tbhost1.setup(); //findViewById() 함수를 사용해 TabHost의 참조를 가져온 다음, TabHost.setup() 함수를 호출한 것에 주의. 만약 setup() 함수를 호출하지 않으면 TabWidget이 정상적으로 표시되지 않는다.


        //첫 번째 tab. (탭 표시 텍스트: tab1), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tbhost1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("",getResources().getDrawable(R.drawable.selection));
        tbhost1.addTab(ts1);

        //두 번째 tab. (탭 표시 텍스트: tab2), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tbhost1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("",getResources().getDrawable(R.drawable.all_menu));
        tbhost1.addTab(ts2);

        //세 번째 tab. (탭 표시 텍스트: tab3), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tbhost1.newTabSpec("Tab Spec 3");
        ts3.setContent(R.id.content3);
        ts3.setIndicator("",getResources().getDrawable(R.drawable.game));
        tbhost1.addTab(ts3);
    }

    /* ExpandableList에 들어가는 데이터를 설정하는 코드 */
    protected void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        //parent list 값을 생성.
        listDataHeader.add("소담국밥");
        listDataHeader.add("토마토 도시락");
        listDataHeader.add("고기굽는 집");
        listDataHeader.add("샐러디");
        listDataHeader.add("김밥천국");
        listDataHeader.add("파파이스");
        listDataHeader.add("공씨네 주먹밥");
        listDataHeader.add("샹차이");
        listDataHeader.add("봉구스");

        //child list 값을 생성.
        /* 데이터 추가시 아래에 나오는 것처럼 ListItem 객체를 만들어 준 후
        ArrayList에 add메소드를 통해 연결해주면 됨
        ex) ListItem 가게이름_번호 = new ListItem("메뉴","가격");
            가게ArrayList(ex.sodam).add(만들어 준 ListItem객체)

            ListItem sodam_5 = new ListItem("우거지해장국","5000")
            sodam.add(sodam_5);
            이러면 자동적으로 1번 검색 메뉴와 2번 모든메뉴 리스트에 추가가됨
         */
        ListItem sodam_1 = new ListItem("사골순대국밥","4800");
        ListItem sodam_2 = new ListItem("뼈해장국","4800");
        ListItem sodam_3 = new ListItem("얼큰사골순대국밥","4800");
        ListItem sodam_4 = new ListItem("소내장탕","5000");
        sodam = new ArrayList<>();
        sodam.add(sodam_1);
        sodam.add(sodam_2);
        sodam.add(sodam_3);
        sodam.add(sodam_4);

        tomato = new ArrayList<>();
        ListItem tomato_1 = new ListItem("토마토 메뉴1","3500");
        ListItem tomato_2 = new ListItem("토마토 메뉴2","4500");
        ListItem tomato_3 = new ListItem("토마토 메뉴3","5500");
        tomato.add(tomato_1);
        tomato.add(tomato_2);
        tomato.add(tomato_3);


        gozip = new ArrayList<>();
        ListItem gozip_1 = new ListItem("제육쌈밥","7000");
        ListItem gozip_2 = new ListItem("삼겹살쌈밥","7000");
        gozip.add(gozip_1);
        gozip.add(gozip_2);


        salady = new ArrayList<>();
        ListItem salady_1 = new ListItem("베이컨애그","5500");
        ListItem salady_2 = new ListItem("연어크랩","5500");
        salady.add(salady_1);
        salady.add(salady_2);

        kimcheon = new ArrayList<>();
        ListItem gimcheon_1 = new ListItem("참치김밥","2000");
        ListItem gimcheon_2 = new ListItem("라면","2500");
        kimcheon.add(gimcheon_1);
        kimcheon.add(gimcheon_2);

        papaies = new ArrayList<>();
        ListItem papa_1 = new ListItem("치킨휠레","5500");
        ListItem papa_2 = new ListItem("케이준치킨버거","5500");
        papaies.add(papa_1);
        papaies.add(papa_2);

        gongssi = new ArrayList<>();
        ListItem gongssi_1 = new ListItem("공씨네주먹밥","2000");
        ListItem gongssi_2 = new ListItem("라면세트","3500");
        gongssi.add(gongssi_1);
        gongssi.add(gongssi_2);

        shanchi = new ArrayList<>();
        ListItem shangchai_1 = new ListItem("짜장면","5000");
        ListItem shangchai_2 = new ListItem("짬뽕","8000");
        shanchi.add(shangchai_1);
        shanchi.add(shangchai_2);

        bongus = new ArrayList<>();
        ListItem bongus_1 = new ListItem("봉구스밥버거","2000");
        ListItem bongus_2 = new ListItem("쏘야버거","2000");
        bongus.add(bongus_1);
        bongus.add(bongus_2);

        listHash.put(listDataHeader.get(0),sodam);
        listHash.put(listDataHeader.get(1),tomato);
        listHash.put(listDataHeader.get(2),gozip);
        listHash.put(listDataHeader.get(3),salady);
        listHash.put(listDataHeader.get(4),kimcheon);
        listHash.put(listDataHeader.get(5),papaies);
        listHash.put(listDataHeader.get(6),gongssi);
        listHash.put(listDataHeader.get(7),shanchi);
        listHash.put(listDataHeader.get(8),bongus);

    }

    @Override //가게이름에 대한 버튼에 대한 onClick메소드 설정 이는 Listener를 메인액티비티에서 임플리먼트 했기때문에 무명클래스로 하나하나 온클릭 메소드를 설정하지 않고 하나로 통일하기 위해서이다
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.sodam:
                sodam_click=!sodam_click;
                sodam_btn.setSelected(sodam_click);
                break;
            case R.id.gozip:
                gozip_click=!gozip_click;
                gozip_btn.setSelected(gozip_click);
                break;
            case R.id.salady:
                salady_click=!salady_click;
                salady_btn.setSelected(salady_click);
                break;
            case R.id.gong:
                gongssi_click=!gongssi_click;
                gongssi_btn.setSelected(gongssi_click);
                break;
            case R.id.kimcheon:
                kimcheon_click=!kimcheon_click;
                kimcheon_btn.setSelected(kimcheon_click);
                break;
            case R.id.shanchi:
                shanchi_click=!shanchi_click;
                shanchi_btn.setSelected(shanchi_click);
                break;
            case R.id.bongus:
                bongus_click=!bongus_click;
                bongus_btn.setSelected(bongus_click);
                break;
            case R.id.tomato:
                tomato_click=!tomato_click;
                tomato_btn.setSelected(tomato_click);
                break;
            case R.id.papaies:
                papaies_click=!papaies_click;
                papaies_btn.setSelected(papaies_click);
                break;
        }
    }
}
