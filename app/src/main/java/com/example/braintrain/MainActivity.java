package com.example.braintrain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    int win,loss;
    Boolean gameIsOn;
    int op1,op2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameIsOn = false;

    }

    public void GO(View view){
        view.setVisibility(View.GONE);

        TextView textView = findViewById(R.id.textView);
        TextView question = findViewById(R.id.question);
        TextView tvScore = findViewById(R.id.tvScore);
        TextView tvTimer = findViewById(R.id.tvTimer);
        Button button =  findViewById(R.id.button);

        textView.setVisibility(View.INVISIBLE);
        question.setVisibility(View.VISIBLE);
        tvScore.setVisibility(View.VISIBLE);
        tvTimer.setVisibility(View.VISIBLE);
        button.setVisibility(View.GONE);
        findViewById(R.id.grid).setVisibility(View.VISIBLE);
        gameIsOn = true;
        win=loss=0;
        score(0,0);
        question();
        start();

    }

    public void start(){

        CountDownTimer ctd = new CountDownTimer(25000+100,1000) {
            @Override
            public void onTick(long l) {
                timer((int)l/1000);
            }

            @Override
            public void onFinish() {
                gameIsOn=false;
                timer(0);
                TextView textView = findViewById(R.id.textView);
                textView.setText("DONE!!");
                Button button =  findViewById(R.id.button);
                button.setVisibility(View.VISIBLE);


            }
        }.start();

    }

    public int random(int min, int max) {

        return (int) (min + (Math.random() * (max - min + 1)));

    }

    public void timer(int a){
        TextView tvTimer = findViewById(R.id.tvTimer);
        tvTimer.setText(a+"s");
    }

    public void question(){

        TextView question = findViewById(R.id.question);;
        op1 = random(1,100);
        op2 = random(1,50);
        question.setText(op1+" + "+op2);
        optionSet();
    }

    public void gameOn(View view){
        if(gameIsOn){
            TextView textView = findViewById(R.id.textView);
            TextView t = (TextView)view;
            if((Integer.valueOf(t.getText().toString())).equals(op1+op2)){
                textView.setText("Correct :D");
                win++;
                question();
            }
            else{
                textView.setText("Wrong :(");
                loss++;
                question();
            }
            textView.setVisibility(View.VISIBLE);

            score(win,loss);

        }

    }
    public void score(int w, int l){
        TextView score = findViewById(R.id.tvScore);
        score.setText(w+"/"+(w+l));
    }

    public void optionSet(){
        TextView tv0 = findViewById(R.id.tv0);
        TextView tv1  = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        int a = random(0,3);
        //Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();
        switch(a)
        {
            case 0: tv0.setText(Integer.toString(op1+op2));tv1.setText(Integer.toString((op1+op2)*random(2,3)));tv2.setText(Integer.toString((op1+op2)*random(4,6)));tv3.setText(Integer.toString((op1+op2)*random(7,9)));break;
            case 1: tv1.setText(Integer.toString(op1+op2));tv0.setText(Integer.toString((op1+op2)+random(1,3)));tv2.setText(Integer.toString((op1+op2)+random(4,6)));tv3.setText(Integer.toString((op1+op2)+random(7,9)));break;
            case 2: tv2.setText(Integer.toString(op1+op2));tv1.setText(Integer.toString(Math.abs((op1+op2)-random(1,3))));tv0.setText(Integer.toString(Math.abs((op1+op2)-random(4,6))));tv3.setText(Integer.toString(Math.abs((op1+op2)-random(7,10))));break;
            case 3: tv3.setText(Integer.toString(op1+op2));tv1.setText(Integer.toString((op1+op2)/random(2,3)));tv2.setText(Integer.toString((op1+op2)/random(5,7)));tv0.setText(Integer.toString((op1+op2)/random(8,10)));break;
        }
    }
}
