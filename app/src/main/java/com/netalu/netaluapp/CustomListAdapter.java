package com.netalu.netaluapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.netalu.netaluapp.database.Business;

public class CustomListAdapter extends ArrayAdapter<Business> {

    private final Context context;
    private final Business[] values;

    public CustomListAdapter(Context context, Business[] values) {
        super(context, -1, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        TextView title = (TextView) rowView.findViewById(R.id.titleTextView);
        TextView subTitle = (TextView) rowView.findViewById(R.id.subtitleTextView);

        title.setText(values[position].name);
        subTitle.setText(values[position].description);

        return rowView;
    }
}
