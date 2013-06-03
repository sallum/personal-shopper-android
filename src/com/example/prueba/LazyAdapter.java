package com.example.prueba;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.personalshopper.R;

 
public class LazyAdapter extends BaseAdapter {
 
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    final int stub_id = R.drawable.ic_launcher;
 
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
 
    public int getCount() {
        return data.size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);
 
        TextView type = (TextView)vi.findViewById(R.id.type);
        TextView brand = (TextView)vi.findViewById(R.id.brand);
        TextView prize = (TextView)vi.findViewById(R.id.prize);
        //TextView colour = (TextView)vi.findViewById(R.id.colour);
        //TextView shop = (TextView)vi.findViewById(R.id.shop);
        //TextView address = (TextView)vi.findViewById(R.id.address);
        ImageView thumb_image = (ImageView)vi.findViewById(R.id.list_image);

        HashMap<String, String> article = new HashMap<String, String>();
        article = data.get(position);
 
        // Setting all values in listview
        type.setText(article.get(CustomizedListView.KEY_TYPE));
        brand.setText(article.get(CustomizedListView.KEY_BRAND));
        prize.setText(article.get(CustomizedListView.KEY_PRIZE));
               
        //Setting an image
        String url = "drawable/"+ article.get(CustomizedListView.KEY_THUMB_URL);
        int imageResource = vi.getResources().getIdentifier(url, null, vi.getContext().getApplicationContext().getPackageName());
        if(imageResource!=0){
        	thumb_image.setImageResource(imageResource);
        } else {
        	thumb_image.setImageResource(stub_id);
        }
        
        return vi;
    }
    

    
}