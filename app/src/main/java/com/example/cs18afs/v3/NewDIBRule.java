package com.example.cs18afs.v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class NewDIBRule extends AppCompatActivity {
    ArrayList<String> al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dibrule);
        Intent intent = getIntent();
        al = intent.getStringArrayListExtra("Qs");
    }

    public void Done_OnClick(View v){
        EditText txtQuestion = (EditText) findViewById(R.id.txtQuestion);
        String nquestion = txtQuestion.getText().toString();
        Intent intent = new Intent();
        al.add(nquestion);
        intent.putStringArrayListExtra("Qs",al); //value should be your string from the edittext
        setResult(1, intent); //The data you want to send back
        finish();
    }
}
