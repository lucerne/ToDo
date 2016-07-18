package com.example.lucerne.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucerne on 6/28/16.
 */

public class EditActivity extends AppCompatActivity {
    EditText nameEditText, notesEditText, statusEditText, dueDateEditText;
    Todo u;

    private Spinner spinner1;
    private Button btSaveText;
    String priority;

    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Sets the Toolbar to act as the ActionBar for this Activity window.
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        // Get data from todos and display
        u = (Todo) getIntent().getSerializableExtra("Main");

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        notesEditText = (EditText) findViewById(R.id.notesEditText);
        statusEditText = (EditText) findViewById(R.id.statusEditText);
        dueDateEditText = (EditText) findViewById(R.id.dueDateEditText);


        nameEditText.setText(u.getName());
        notesEditText.setText(u.getNotes());
        statusEditText.setText(u.getStatus());
        dueDateEditText.setText(u.getDueDate());

        // Place edit cursor at the end of the text
        nameEditText.setSelection(nameEditText.getText().length());
        notesEditText.setSelection(notesEditText.getText().length());
        statusEditText.setSelection(statusEditText.getText().length());
        dueDateEditText.setSelection(dueDateEditText.getText().length());

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        priority = u.getPriority();

        list.add("HIGH");
        list.add("MEDIUM");
        list.add("LOW");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, list);
        System.out.print(dataAdapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);

        if (priority.equals("HIGH")) {
            spinner1.setSelection(0);
        }
        else if (priority.equals("MEDIUM")){
            spinner1.setSelection(1);
        }
        else{
            spinner1.setSelection(2);
        }

        System.out.print(list);
    }

    // Give data to main
    public void onSaveItem(MenuItem mi){
        // closes the activity and returns to first screen
        Intent data = new Intent();
        u.setName(nameEditText.getText().toString());
        u.setNotes(notesEditText.getText().toString());
        u.setStatus(statusEditText.getText().toString());
        u.setDueDate(dueDateEditText.getText().toString());

        u.setPriority(String.valueOf(spinner1.getSelectedItem()));

        data.putExtra("Edit", u);

        setResult(RESULT_OK, data);
        this.finish();
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

}
