package com.example.lucerne.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lucerne on 6/19/16.
 */
public class ItemAdapter extends ArrayAdapter<TodoTask> {
    public ItemAdapter(Context context, ArrayList<TodoTask> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TodoTask task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvNotes = (TextView) convertView.findViewById(R.id.tvNotes);
        // Populate the data into the template view using the data object
        tvName.setText(task.name);
        tvNotes.setText(task.notes);
        // Return the completed view to render on screen
        return convertView;
    }
}