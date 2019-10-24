package com.example.cs18afs.v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        al = new ArrayList<String>();

        String [] listStdIceBrakers = new String[]{
                "If you could go anywhere in the world, where would you go?",
                "If you were stranded on a desert island, what three things would you want to take with you?",
                "If you could eat only one food for the rest of your life, what would that be?",
                "If you won a million dollars, what is the first thing you would buy?",
                "If you could spaned the day with one fictional character, who would it be?",
                "If you found a magic lantern and a genie gave you three wishes, what would you wish?"

        };
        for(int idx=0;idx<listStdIceBrakers.length;idx++)
            al.add(listStdIceBrakers[idx]);
    }

    public void onClickPlayDIB(View v){
        Random rand = new Random();

        int index = rand.nextInt(al.size());

        Toast.makeText(getApplicationContext(),al.get(index),Toast.LENGTH_LONG).show();
    }

    public void btnNewDIB_OnClick(View v){
        Intent intent = new Intent(this, NewDIBRule.class);
        intent.putStringArrayListExtra("Qs", al);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Retrieve data in the intent
        switch(requestCode)
        {
            case 0:
                al = data.getStringArrayListExtra("Qs");
                break;
            default:
                break;
        }

    }
}
