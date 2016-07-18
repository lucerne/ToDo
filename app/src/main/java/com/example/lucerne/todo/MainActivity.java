package com.example.lucerne.todo;

import com.example.lucerne.todo.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Todo> todos = new ArrayList<>();
    ArrayAdapter<Todo> adapter;
    int idx;
    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        // read saved items from disk
        populateArrayItems();

        //create our new array adapter
        adapter = new todoArrayAdapter(this, R.layout.custom_list, todos);


        //Find list view and bind it with the custom adapter
        ListView listView = (ListView) findViewById(R.id.customListView);
        listView.setAdapter(adapter);

        // Delete
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todos.remove(position);
                adapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });

        // Launch detailed view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idx = position;
                launchComposeView(todos.get(idx));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    public void onAddItem(View view) {
//        Calendar c = Calendar.getInstance();
//        System.out.println("Current time => " + c.getTime());
//
//        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//        String formattedDate = df.format(c.getTime());
//
//        Todo newtodo = new Todo("", "", "", "", formattedDate);
//        adapter.add(newtodo);
//        writeItems();
//        idx = todos.size()-1;
//
//        launchComposeView(newtodo);
//    }

    // To be replaced with SQLite
    private void writeItems() {
    }

    // To be replaced with SQLite
    private void readItems() {
    }

    // To be replaced with SQLite
    public void populateArrayItems() {
    }

    //custom ArrayAdapter
    class todoArrayAdapter extends ArrayAdapter<Todo> {

        private Context context;
        private List<Todo> todos;

        //constructor, call on creation
        public todoArrayAdapter(Context context, int resource, ArrayList<Todo> objects) {
            super(context, resource, objects);

            this.context = context;
            this.todos = objects;
        }

        //called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent) {

            //get the property we are displaying
            Todo todo = todos.get(position);

            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.todo_layout, null);

            TextView name = (TextView) view.findViewById(R.id.name);
            TextView priority = (TextView) view.findViewById(R.id.priority);



            //set address and description
            name.setText(todo.getName());

            String p = todo.getPriority();
            priority.setText(p);
            priority.setTextSize(15);
            priority.setTypeface(null, Typeface.BOLD);

            if (p.equals("HIGH")) {
                priority.setTextColor(Color.parseColor("#e31a1c"));
            }
            else if (p.equals("MEDIUM")){
                priority.setTextColor(Color.parseColor("#fd8d3c"));
            }
            else{
                priority.setTextColor(Color.parseColor("#006d2c"));
            }

            return view;
        }
    }

    public void launchComposeView(Todo t) {
        // first parameter is the context, second is the class of the activity to launch
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("Main", t);
        startActivityForResult(intent,REQUEST_CODE); // brings up the second activity

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Todo u = (Todo) data.getSerializableExtra("Edit");
            // memory leak?
            System.out.print(u);
            todos.set(idx, u);
            adapter.notifyDataSetChanged();
            writeItems();
        }
    }


    public static class CustomOnItemSelectedListener implements
            AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    }

    public void onAddItem(MenuItem mi) {
        // handle click here

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = df.format(c.getTime());

        Todo newtodo = new Todo("", "", "", "", formattedDate);
        adapter.add(newtodo);
        writeItems();
        idx = todos.size()-1;

        launchComposeView(newtodo);
    }

}
