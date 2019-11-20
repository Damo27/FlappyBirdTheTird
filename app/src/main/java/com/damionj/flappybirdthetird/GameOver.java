package com.damionj.flappybirdthetird;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;


public class GameOver extends AppCompatActivity {

    int score;
    TextView txt_Score;

    public static void setName(String name) {
        GameOver.name = name;
    }

    static String name;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("HighScores");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        score = getIntent().getIntExtra("Score", 0);
        txt_Score = (TextView)findViewById(R.id.txt_Score2);
        txt_Score.setText(""+score);

        //Upload the players score as value and Name as key
        myRef.child(name).setValue(score);

        //Timer starts the next activity with scores
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                Intent i = new Intent(GameOver.this,Scores.class);
                startActivity(i);
                System.exit(0);
            }
        }, 2500);
    }

    //Override the back button to prevent the user from going back
    @Override
    public void onBackPressed()
    {

    }


}
