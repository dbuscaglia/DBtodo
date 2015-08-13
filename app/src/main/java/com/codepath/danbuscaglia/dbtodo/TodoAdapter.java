package com.codepath.danbuscaglia.dbtodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.codepath.danbuscaglia.dbtodo.models.Todo;

import java.util.ArrayList;

public class TodoAdapter extends ArrayAdapter<Todo> {

    private static ArrayList<Todo> todoItems;

    private static class ViewHolder {
        TextView name;
        ImageButton delete;
        CheckBox checked;
        int position;
        Todo item;

    }

    public TodoAdapter(Context context, ArrayList<Todo> items) {
        super(context, R.layout.todo_layout, items);
        this.todoItems = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Todo item = getItem(position);
        final ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.todo_layout, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.txtTaskName);
            viewHolder.delete = (ImageButton) convertView.findViewById(R.id.btn_remove);
            viewHolder.checked = (CheckBox) convertView.findViewById(R.id.ckFinishTodo);
            viewHolder.position = position;
            viewHolder.item = item;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // default values
        viewHolder.name.setText(item.toString());
        viewHolder.checked.setChecked(item.finished);
        /**
         * Handle the trash button
         */
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoItems.remove(viewHolder.position);
                viewHolder.item.delete();
                notifyDataSetChanged();
            }
        });
        /**
         * Handle checkbox
         */
        viewHolder.checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {;
                viewHolder.item.finished = viewHolder.checked.isChecked();
                viewHolder.item.save();
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
