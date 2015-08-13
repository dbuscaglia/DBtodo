package com.codepath.danbuscaglia.dbtodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.codepath.danbuscaglia.dbtodo.controllers.LocalStore;
import com.codepath.danbuscaglia.dbtodo.models.PriorityLevel;
import com.codepath.danbuscaglia.dbtodo.models.Todo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Todo> items;
    ArrayAdapter<Todo> itemsAdapter;
    ListView lvItems;
    LocalStore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = LocalStore.db();
        setContentView(R.layout.activity_main);
        readItems();
        itemsAdapter = new ArrayAdapter<Todo>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();

    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);

        Todo newtodo = new Todo(etNewItem.getText().toString(), PriorityLevel.REMINDER);
        itemsAdapter.add(newtodo);
        etNewItem.setText("");
        writeItems();
    }

    public void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        items.get(pos).delete();
                        items.remove(pos);
                        itemsAdapter.notifyDataSetChanged();
                        return true;
                    }

                });

    }

    private void readItems() {
        items = (ArrayList) db.all();
    }

    private void writeItems() {
        for (Todo todo : items) {
            todo.save();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
