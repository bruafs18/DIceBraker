package com.example.cs18afs.v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> al;

    String sPoints = "Points: ";
    int points;
    final int NewDIB = 0;
    final int ERROR = 0;
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
        for(String line : listStdIceBrakers)
            al.add(line);

        points = 0;
    }

    int roll_the_dice(int bound)
    {
        return new Random().nextInt(bound);
    }

    void ShowToast(String Message, int length)
    {
        Toast.makeText(getApplicationContext(),Message,length).show();
    }

    int getNumberFromUser()
    {
        EditText txtUserNum = (EditText) findViewById(R.id.txtUserNum);
        int user_num = 0;
        try{
            user_num = Integer.parseInt(txtUserNum.getText().toString());
            if(user_num>6||user_num<1)
                throw new Exception("Number needs to be between 1 and 6");
        }
        catch(Exception ex){
            ShowToast(ex.getMessage(),Toast.LENGTH_SHORT);
        }

        return user_num;
    }

    void sameNumber(){
        ShowToast("Congratulations",Toast.LENGTH_SHORT);
        points++;
        UpdatelblPoints();
        try {
            SlackMessage slackMessage = new SlackMessage("Andre", sPoints + points);
            SlackUtils.sendMessage(slackMessage);
        }
        catch (Exception ex)
        {
            ShowToast(ex.getMessage(),Toast.LENGTH_LONG);
        }
    }

    void UpdatelblPoints()
    {
        TextView lblPoints = (TextView)findViewById(R.id.lblPoints);
        lblPoints.setText(sPoints+points);
    }

    public void onClickGenNum(View v)
    {
        int new_num = roll_the_dice(6)+1;

        int user_num = getNumberFromUser();

        if(user_num == ERROR)
            return;

        if(user_num==new_num)
            sameNumber();
        else
            ShowToast("Wrong number", Toast.LENGTH_SHORT);

    }

    public void onClickPlayDIB(View v){
        int index = roll_the_dice(al.size());
        ShowToast(al.get(index),Toast.LENGTH_SHORT);
    }


    public void btnNewDIB_OnClick(View v){
        Intent intent = new Intent(this, NewDIBRule.class);
        intent.putStringArrayListExtra("Qs", al);
        startActivityForResult(intent,NewDIB);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Retrieve data in the intent
        switch(requestCode)
        {
            case NewDIB:
                al = data.getStringArrayListExtra("Qs");
                break;
            default:
                break;
        }

    }
}
