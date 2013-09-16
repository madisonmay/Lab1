package com.evansimpson.mobpro.navigationdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mmay on 9/12/13.
 */

public class TaskAdapter extends ArrayAdapter {

    Context context;
    int layoutResourceId;
    ArrayList<String> data = null;

    public TaskAdapter(Context context, int layoutResourceId, ArrayList<String> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RowHolder holder;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RowHolder();
            holder.delete_button = (Button)row.findViewById(R.id.button_del);
            holder.task_text = (TextView)row.findViewById(R.id.row_text);
            row.setTag(holder);
        }

        else
        {
            holder = (RowHolder) row.getTag();
        }

        String task = data.get(position);
        holder.task_text.setText(task);
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            data.remove(position);
            notifyDataSetChanged();
            }
        });

        return row;
    }

    static class RowHolder
    {
        TextView task_text;
        Button delete_button;
    }
}
