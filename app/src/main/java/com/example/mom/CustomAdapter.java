package com.example.mom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter implements AdapterView.OnItemClickListener {

    Context context;
//    LayoutInflater mLayoutInflater = null;
//    ArrayList<MovieItem> sample;
    List list;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
    }
    class ViewHolder{
        public TextView mo_title;
        public TextView mo_rate;
    }

//    @Override
    public CustomAdapter(Context context, ArrayList list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.tab2_listview_item,parent,false);
        }
        viewHolder = new ViewHolder();
        viewHolder.mo_title = (TextView) convertView.findViewById(R.id.movieTitle);
        viewHolder.mo_rate = (TextView)convertView.findViewById(R.id.rateStar);

        final MovieItem movieItem = (MovieItem) list.get(position);
        viewHolder.mo_title.setText(movieItem.getTitle());
        viewHolder.mo_rate.setText(movieItem.getRateString());

//        View view = mLayoutInflater.inflate(R.layout.tab2_listview_item, null);
//
//        TextView movieTitle = (TextView)view.findViewById(R.id.movieTitle);
//        TextView rate = (TextView)view.findViewById(R.id.rateStar);
//
//        movieTitle.setText(sample.get(position).getTitle());
//        rate.setText(sample.get(position).getRateString());

                //아이템 클릭 방법2 - 클릭시 아이템 반전 효과가 안 먹힘
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, " " + movieItem.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}