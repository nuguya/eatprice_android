package ourincheon.com.project;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kim on 2018-02-02.
 */

public class ListViewAdapter extends BaseAdapter {
    Context context;
    private LayoutInflater inflater;
    private ArrayList<SearchList> data;
    int layout;

    public ListViewAdapter(Context context, ArrayList<SearchList> data, int layout) {
        this.context = context;
        this.data = data;
        this.layout = layout;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(layout,null);
        }
        SearchList item = data.get(i);
        ImageView icon = (ImageView)view.findViewById(R.id.logo);
        icon.setImageResource(item.getIcon());
        TextView menu = (TextView)view.findViewById(R.id.search_menu);
        menu.setText(data.get(i).getMenu());
        TextView price = (TextView)view.findViewById(R.id.search_price);
        price.setText(data.get(i).getPrice());
        return view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
