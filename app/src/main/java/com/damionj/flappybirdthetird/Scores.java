package com.damionj.flappybirdthetird;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Scores extends AppCompatActivity {

    private ListView lv;
    private Button btn;
    List<String> scores;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("HighScores");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        lv = (ListView)findViewById(R.id.list_scores);
        btn = (Button)findViewById(R.id.btn_playAgain);
        scores = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                Iterable<DataSnapshot> storedScores = dataSnapshot.getChildren();

                for(DataSnapshot score: storedScores)
                {
                    String name = score.getKey();
                    String scoreString = score.getValue().toString();
                    scores.add(name + " : " + scoreString);

                    lv.setAdapter(new ArrayAdapter(
                            getApplicationContext(),
                            android.R.layout.simple_list_item_1,
                            scores));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
                System.exit(0);
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(getBaseContext(), MainActivity.class);
        startActivity(i);
        System.exit(0);
    }
}
