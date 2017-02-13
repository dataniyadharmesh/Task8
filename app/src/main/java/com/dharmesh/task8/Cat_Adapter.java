package com.dharmesh.task8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DHARMESH on 13-02-2017.
 */


public class Cat_Adapter extends ArrayAdapter<Cat_Post>{
    Context context;
    int resources;
    ArrayList<Cat_Post> cat_posts;
    private LayoutInflater inflater;
    private View view;
    int layResource;

    public Cat_Adapter(Context context, int resource, List<Cat_Post> objects) {
        super( context, resource, objects );
        this.context = context;
        this.cat_posts= (ArrayList<Cat_Post>) objects;
        this.layResource = resource;

        inflater= LayoutInflater.from(context);
    }

    static class ViewHolder
    {
        TextView  Name;
    }

    public View getView(int position, View contextView, ViewGroup viewGroup) {
        ViewHolder holder;

        if (contextView == null) {

            holder = new ViewHolder();
            contextView = LayoutInflater.from(context).inflate(layResource, viewGroup, false);
            holder.Name = (TextView) contextView.findViewById(R.id.txt_categories);
            contextView.setTag(holder);

        } else {
            holder = (ViewHolder) contextView.getTag();

        }
        Cat_Post p = cat_posts.get(position);
        holder.Name.setText(p.getName());


        return contextView;
    }
}


