package com.codepath.danbuscaglia.dbtodo;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.codepath.danbuscaglia.dbtodo.models.TodoItemTask;

import java.util.ArrayList;

public class TodoAdapter extends ArrayAdapter<TodoItemTask> {

    private static ArrayList<TodoItemTask> todoItems;
    private static FragmentManager fm;

    private static class ViewHolder {
        TextView name;
        ImageButton delete;
        Button edit_item;
        int position;
        TodoItemTask item;
        android.app.FragmentManager fm;
    }

    public TodoAdapter(Context context, ArrayList<TodoItemTask> items) {
        super(context, R.layout.todo_layout, items);
        this.todoItems = items;
        this.fm = ((Activity) context).getFragmentManager();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TodoItemTask item = getItem(position);
        final ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.todo_layout, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.txtTaskName);
            viewHolder.delete = (ImageButton) convertView.findViewById(R.id.btn_remove);
            viewHolder.position = position;
            viewHolder.item = item;
            viewHolder.edit_item = (Button) convertView.findViewById(R.id.btn_edit);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // default values
        String taskText = item.toString() + " [" + item.getPriority().toString() + "]\r\n " +
                item.task_date.toString();
        viewHolder.name.setText(taskText);
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
        viewHolder.edit_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("task", viewHolder.item);
                EditTodoFragment editTodoFragment = new EditTodoFragment();
                editTodoFragment.setArguments(bundle);
                editTodoFragment.show(fm, "edit_todo_layout");
            }
        });

        return convertView;
    }
}
