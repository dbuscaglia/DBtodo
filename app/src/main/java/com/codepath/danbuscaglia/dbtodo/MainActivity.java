package com.codepath.danbuscaglia.dbtodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.codepath.danbuscaglia.dbtodo.controllers.LocalStore;
import com.codepath.danbuscaglia.dbtodo.models.PriorityLevel;
import com.codepath.danbuscaglia.dbtodo.models.TodoItemTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EditTodoFragment.OnTodoUpdatedListener {

    ArrayList<TodoItemTask> items;
    TodoAdapter itemsAdapter;
    ListView lvItems;
    LocalStore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.deleteDatabase("AA_DB_NAME");
        db = LocalStore.db();
        setContentView(R.layout.activity_main);
        items = (ArrayList) db.all();
        itemsAdapter = new TodoAdapter(this,items);
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.print("TEST");
                Log.i("DEBUG", "TEST");
                return true;
            }
        });

    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String str = etNewItem.getText().toString();
        if(!str.isEmpty()) {
            TodoItemTask newtodo = new TodoItemTask(str, PriorityLevel.REMINDER);
            newtodo.save();
            itemsAdapter.add(newtodo);
            etNewItem.setText("");
        }
    }


    @Override
    public void onTodoUpdated(TodoItemTask updatedTodo) {
        updatedTodo.save();
        itemsAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void refreshLV(){
        itemsAdapter.notifyDataSetChanged();
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

    @Override
    protected void onPause() {

        super.onPause();

    }
}
