package com.example.lucerne.todo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditItemActivity extends AppCompatActivity {
    EditText etDetailedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Intent intent = getIntent();
        String original_text = intent.getStringExtra("original_text");
//        TodoTask t = (TodoTask) intent.getSerializableExtra("todoTask");

        etDetailedText = (EditText) findViewById(R.id.etDetailedText);

        etDetailedText.setText(original_text);
        etDetailedText.setSelection(etDetailedText.getText().length());
        System.out.println("etDetailedText ");
        System.out.println(etDetailedText);

//        System.out.println("t ");
//        System.out.println(t.priorityLevel);
    }



    // ActivityTwo.java
    public void onSaveItem(View v) {
        // closes the activity and returns to first screen
        Intent data = new Intent();
        String text = etDetailedText.getText().toString();
        data.putExtra("item", text);
//        data.setData(Uri.parse(text));
        setResult(RESULT_OK, data);
        this.finish();
    }

}
