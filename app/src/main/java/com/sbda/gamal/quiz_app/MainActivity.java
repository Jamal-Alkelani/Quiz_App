package com.sbda.gamal.quiz_app;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Trace;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.TRANSPARENT;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.YELLOW;
import static android.graphics.Color.red;

public class MainActivity extends AppCompatActivity {



    RadioGroup q1;
    RadioGroup q2;
    RadioGroup q3;
    CheckBox q4o1;
    CheckBox q4o2;
    CheckBox q4o3;
    EditText q5o1;
    int Answers[]={R.id.q1o1,R.id.q2o1,R.id.q3o3};
    private boolean startAgain=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         q1=(RadioGroup) findViewById(R.id.q1);
         q2=(RadioGroup) findViewById(R.id.q2);
         q3=(RadioGroup) findViewById(R.id.q3);
         q4o1=(CheckBox) findViewById(R.id.q4o1);
         q4o2=(CheckBox) findViewById(R.id.q4o2);
         q4o3=(CheckBox) findViewById(R.id.q4o3);
         q5o1=(EditText) findViewById(R.id.q5o1);

    }

//    @OnClick(R.id.submit)
    public void submit(View v){


        if(areAllQuestionsAnswered()||startAgain){
            Button button=(Button) v.findViewById(R.id.submit);
            if(!startAgain){
                button.setText("Start Again");
                startAgain=true;
                int res=validate();
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Grade");
                alertDialog.setMessage("Congrats you got "+res+"/"+"5");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }else{
                button.setText("Submit");
                restartEverything();
                startAgain=false;
            }

        }else{
            Toast.makeText(this,"Please Answer All Questions",Toast.LENGTH_SHORT).show();
        }
    }

    private int validate() {
        int counter=0;
        if(q1.getCheckedRadioButtonId()==Answers[0]){
            counter++;
            findViewById(q1.getCheckedRadioButtonId()).setBackgroundColor(GREEN);
        }else{
            findViewById(q1.getCheckedRadioButtonId()).setBackgroundColor(RED);
        }

        if(q2.getCheckedRadioButtonId()==Answers[1]){
            counter++;
            findViewById(q2.getCheckedRadioButtonId()).setBackgroundColor(GREEN);
        }else{
            findViewById(q2.getCheckedRadioButtonId()).setBackgroundColor(RED);
        }

        if(q3.getCheckedRadioButtonId()==Answers[2]){
            counter++;
            findViewById(q3.getCheckedRadioButtonId()).setBackgroundColor(GREEN);
        }else{
            findViewById(q3.getCheckedRadioButtonId()).setBackgroundColor(RED);
        }



        if(q4o1.isChecked() && q4o2.isChecked()){
            counter++;
            q4o1.setBackgroundColor(GREEN);
            q4o2.setBackgroundColor(GREEN);
        }else if(q4o1.isChecked()){
            q4o1.setBackgroundColor(GREEN);
        }else if(q4o2.isChecked()){
            q4o1.setBackgroundColor(GREEN);
        }else if(q4o3.isChecked()){
            q4o1.setBackgroundColor(RED);
        }

        if(q5o1.getText().toString().equals("water") || q5o1.getText().toString().equals("Water")){
            counter++;
            q5o1.setBackgroundColor(GREEN);
        }else{
            q5o1.setBackgroundColor(RED);
        }

        if(counter==5){
            Toast.makeText(this,"Congrats You Answered All the Questions Correcrly",Toast.LENGTH_SHORT).show();
        }

        return counter;
    }

    private void restartEverything() {
        q1.clearCheck();
        for(int i=0;i<3;i++){
            q1.getChildAt(i).setBackgroundColor(TRANSPARENT);
        }
        q2.clearCheck();
        for(int i=0;i<3;i++){
            q2.getChildAt(i).setBackgroundColor(TRANSPARENT);
        }
        q3.clearCheck();
        for(int i=0;i<3;i++){
            q3.getChildAt(i).setBackgroundColor(TRANSPARENT);
        }
        q4o1.setChecked(false);
        q4o1.setBackgroundColor(TRANSPARENT);
        q4o2.setChecked(false);
        q4o2.setBackgroundColor(TRANSPARENT);
        q4o3.setChecked(false);
        q4o3.setBackgroundColor(TRANSPARENT);
        q5o1.setText("");
        q5o1.setBackgroundColor(TRANSPARENT);
    }

    private boolean areAllQuestionsAnswered() {
        int counter=0;
        if(q1.getCheckedRadioButtonId()!=-1){
            counter++;

        }

        if(q2.getCheckedRadioButtonId()!=-1){
            counter++;

        }

        if(q3.getCheckedRadioButtonId()!=-1){
            counter++;

        }

        if(q4o1.isChecked() || q4o2.isChecked() || q4o3.isChecked()) {
            counter++;
        }

        if(!q5o1.getText().equals("")){
            counter++;
        }



        if(counter==5)
        return true;

        return false;
    }
}
