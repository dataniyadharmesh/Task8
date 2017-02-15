package com.dharmesh.task8;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DHARMESH on 14-02-2017.
 */

public class List_Adapter extends ArrayAdapter<List_Post> {

    Context context;
    int resources;
    ArrayList<List_Post> list_posts;
    private LayoutInflater inflater;
    private View view;
    int layResource;

    public List_Adapter(Context context, int resource, List<List_Post> objects) {
        super( context, resource, objects );

        this.context = context;
        this.list_posts = (ArrayList<List_Post>) objects;
        this.layResource = resource;

        inflater = LayoutInflater.from( context );
    }

    static class ViewHolder {
        TextView cat_id,id,quotes;
    }

    public View getView(final int position, View contextView, ViewGroup viewGroup) {
       // Cat_Adapter.ViewHolder holder;
        View view;
        final ViewHolder hold;

        if (contextView == null) {
            hold = new ViewHolder();

            contextView = LayoutInflater.from( context ).inflate( layResource, viewGroup, false );
             hold.id = (TextView) contextView.findViewById(R.id.tv_id);
            hold.cat_id = (TextView) contextView.findViewById(R.id.tv_cat_id);
            hold.quotes = (TextView) contextView.findViewById( R.id.txt_list );

            contextView.setTag( hold );
        } else {
            hold = (ViewHolder) contextView.getTag();
        }

        final List_Post p = list_posts.get(position);
        hold.quotes.setText( p.getQuotes());

        hold.quotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReadAndShare.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("Quote", p.getQuotes());
                v.getContext().startActivity(intent);
            }
        });
     /*   List_Post p = list_posts.get(position);
        hold.quotes.setText(p.getQuotes());*/

        return contextView;
    }
}








   /* public List_Adapter(Context context, int resource, List<List_Post> objects) {
        super( context, resource, objects );
    }
 */


