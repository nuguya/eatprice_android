package ourincheon.com.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static ourincheon.com.project.MainActivity.bongus;
import static ourincheon.com.project.MainActivity.bongus_click;
import static ourincheon.com.project.MainActivity.gongssi;
import static ourincheon.com.project.MainActivity.gongssi_click;
import static ourincheon.com.project.MainActivity.gozip;
import static ourincheon.com.project.MainActivity.gozip_click;
import static ourincheon.com.project.MainActivity.kimcheon;
import static ourincheon.com.project.MainActivity.kimcheon_click;
import static ourincheon.com.project.MainActivity.papaies;
import static ourincheon.com.project.MainActivity.papaies_click;
import static ourincheon.com.project.MainActivity.salady;
import static ourincheon.com.project.MainActivity.salady_click;
import static ourincheon.com.project.MainActivity.shanchi;
import static ourincheon.com.project.MainActivity.shanchi_click;
import static ourincheon.com.project.MainActivity.tomato;
import static ourincheon.com.project.MainActivity.tomato_click;

/**
 * Created by kim on 2018-02-05.
 */

public class SearchListActivity extends Activity {
    ListView listsearcah;
    private ListViewAdapter Adapter;
    private ArrayList<SearchList> al_search = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_list);
        String min,max;

        Intent intent = new Intent(this.getIntent());
        min = intent.getStringExtra("min");
        max = intent.getStringExtra("max");

        listsearcah = (ListView)findViewById(R.id.search_list);
        find(Integer.parseInt(min),Integer.parseInt(max));
        Adapter = new ListViewAdapter(this,al_search,R.layout.search_list_item);
        listsearcah.setAdapter(Adapter);


    }
    public void find(int min, int max){
        if(MainActivity.sodam_click)
            for(int i=0; i<MainActivity.sodam.size();++i){
                if(Integer.parseInt(MainActivity.sodam.get(i).getPrice())<=max&&Integer.parseInt(MainActivity.sodam.get(i).getPrice())>=min) {
                    SearchList data = new SearchList(MainActivity.sodam.get(i).getMenu(),MainActivity.sodam.get(i).getPrice(),R.drawable.sodam_logo);
                    al_search.add(data);
                }
            }
        if(tomato_click)
            for(int i=0; i<tomato.size();++i){
                if(Integer.parseInt(tomato.get(i).getPrice())<=max&&Integer.parseInt(tomato.get(i).getPrice())>=min) {
                    SearchList data = new SearchList(MainActivity.tomato.get(i).getMenu(),MainActivity.tomato.get(i).getPrice(),R.drawable.tomato_logo);
                    al_search.add(data);
                }
            }
        if(gozip_click)
            for(int i=0; i<gozip.size();++i){
                if(Integer.parseInt(gozip.get(i).getPrice())<=max&&Integer.parseInt(gozip.get(i).getPrice())>=min) {
                    SearchList data = new SearchList(MainActivity.gozip.get(i).getMenu(),MainActivity.gozip.get(i).getPrice(),R.drawable.gozip_logo);
                    al_search.add(data);
                }
            }
        if(salady_click)
            for(int i=0; i<salady.size();++i){
                if(Integer.parseInt(salady.get(i).getPrice())<=max&&Integer.parseInt(salady.get(i).getPrice())>=min) {
                    SearchList data = new SearchList(MainActivity.salady.get(i).getMenu(),MainActivity.salady.get(i).getPrice(),R.drawable.salady_logo);
                    al_search.add(data);
                }
            }
        if(kimcheon_click)
            for(int i=0; i<kimcheon.size();++i){
                if(Integer.parseInt(kimcheon.get(i).getPrice())<=max&&Integer.parseInt(kimcheon.get(i).getPrice())>=min) {
                    SearchList data = new SearchList(MainActivity.kimcheon.get(i).getMenu(),MainActivity.kimcheon.get(i).getPrice(),R.drawable.kimcheon_logo);
                    al_search.add(data);
                }
            }
        if(papaies_click)
            for(int i=0; i<papaies.size();++i){
                if(Integer.parseInt(papaies.get(i).getPrice())<=max&&Integer.parseInt(papaies.get(i).getPrice())>=min) {
                    SearchList data = new SearchList(MainActivity.papaies.get(i).getMenu(),MainActivity.papaies.get(i).getPrice(),R.drawable.papayes_logo);
                    al_search.add(data);
                }
            }
        if(gongssi_click)
            for(int i=0; i<gongssi.size();++i){
                if(Integer.parseInt(gongssi.get(i).getPrice())<=max&&Integer.parseInt(gongssi.get(i).getPrice())>=min) {
                    SearchList data = new SearchList(MainActivity.gongssi.get(i).getMenu(),MainActivity.gongssi.get(i).getPrice(),R.drawable.gongssi_logo);
                    al_search.add(data);
                }
            }
        if(shanchi_click)
            for(int i=0; i<shanchi.size();++i){
                if(Integer.parseInt(shanchi.get(i).getPrice())<=max&&Integer.parseInt(shanchi.get(i).getPrice())>=min) {
                    SearchList data = new SearchList(MainActivity.shanchi.get(i).getMenu(),MainActivity.shanchi.get(i).getPrice(),R.drawable.shangchi_logo);
                    al_search.add(data);
                }
            }
        if(bongus_click)
            for(int i=0; i<bongus.size();++i){
                if(Integer.parseInt(bongus.get(i).getPrice())<=max&&Integer.parseInt(bongus.get(i).getPrice())>=min) {
                    SearchList data = new SearchList(MainActivity.bongus.get(i).getMenu(),MainActivity.bongus.get(i).getPrice(),R.drawable.bongus_logo);
                    al_search.add(data);
                }
            }
    }
}


