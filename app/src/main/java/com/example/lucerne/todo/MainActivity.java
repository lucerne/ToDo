package com.example.lucerne.todo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<String> todoItems;

    ArrayList<TodoTask> todoTasks;
//    ArrayList<ItemAdapter> itemAdapter;
    ItemAdapter itemAdapter;

    ArrayAdapter<String> todoAdapter;
    ListView lvItems;
    EditText etEditText;
    int idxText;
    private final int REQUEST_CODE = 1;
//    Priority high;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArrayItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(todoAdapter);
        etEditText = (EditText) findViewById(R.id.etEditText);

//        todoTasks = new ArrayList<TodoTask>();

//        System.out.println("etEditText ");
//        System.out.println(etEditText);

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                todoAdapter.notifyDataSetChanged();
//                itemAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idxText = position;
                launchComposeView();
            }
        });


// Create a category
//        high = new Priority();
//        high.remoteId = 1;
//        high.name = "High";
//        high.save();

    }

    public void populateArrayItems() {
        readItems();
        todoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);

//        itemAdapter = new ItemAdapter(this, todoTasks);
//        ListView listView = (ListView) findViewById(R.id.lvItems);
//        listView.setAdapter(itemAdapter);
//
//        itemAdapter.addAll(todoTasks);
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try {
            todoItems = new ArrayList<String>(FileUtils.readLines(file));
        } catch (IOException e) {

        }
    }


    private void writeItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(file, todoItems);
        } catch (IOException e) {

        }
    }


    public void onAddItem(View view) {
//        TodoTask t = new TodoTask("a", "b", "c", "d", 1);
//        itemAdapter.add(t);
        todoAdapter.add(etEditText.getText().toString());
        etEditText.setText("");
        writeItems();

//// Create an item
//        TodoItem item = new TodoItem();
//        item.remoteId = 1;
////        item.priority = high;
//        item.name = "Outback Steakhouse";
//        item.save();


    }


    // ActivityOne.java
    public void launchComposeView() {
        // first parameter is the context, second is the class of the activity to launch
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra("original_text", todoItems.get(idxText).toString());

//        TodoTask t = new TodoTask(0, "a", "b", "c");
//        intent.putExtra("TodoTask", t);
        startActivityForResult(intent,REQUEST_CODE); // brings up the second activity

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
//            System.out.println(data.getData().toString());
//            todoItems.set(idxText, data.getData().toString());
            todoItems.set(idxText, data.getExtras().getString("item"));
            todoAdapter.notifyDataSetChanged();
//            itemAdapter.notifyDataSetChanged();
            writeItems();
        }
    }

}



