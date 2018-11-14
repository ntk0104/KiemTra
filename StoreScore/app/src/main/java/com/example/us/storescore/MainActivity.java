package com.example.us.storescore;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtScore;
    Button btnAdd;
    ListView listViewHighScore;

    ArrayList<Profile> profileArrayList;
    ScoreAdapter adapter;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Anh xa
        edtUsername = findViewById(R.id.edtUsername);
        edtScore = findViewById(R.id.edtScore);
        btnAdd = findViewById(R.id.btnAddScore);
        listViewHighScore = findViewById(R.id.listviewHighestScore);

        profileArrayList = new ArrayList<>();

        adapter = new ScoreAdapter(this, R.layout.row_high_score, profileArrayList);
        listViewHighScore.setAdapter(adapter);

        //create database
        db = new Database(this, "highestscore.sqlite", null, 1);
        db.QueryData("CREATE TABLE IF NOT EXISTS HighestScore(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(200), Score INTEGER)");

        Log.d("AAAA", "Starting project");
//        addData("Nguyen Tuan Kiet", 169);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                int score = Integer.parseInt(edtScore.getText().toString().trim());
                addData(username, score);
                edtScore.setText("");
                edtUsername.setText("");
            }
        });
        getData();



    }

    private void getData(){
        profileArrayList.clear();
        Cursor dataScore = db.GetData("SELECT * FROM HighestScore ORDER BY Score DESC");
        while (dataScore.moveToNext()){
            String username = dataScore.getString(1);
            int score = dataScore.getInt(2);
            Log.d("AAAA", username + " " + score);
            profileArrayList.add(new Profile(dataScore.getInt(0), dataScore.getString(1), dataScore.getInt(2)));
        }
        adapter.notifyDataSetChanged();
    }

    private void addData(String username, int score){
        String sql = "INSERT INTO HighestScore VALUES(null, '" + username + "', " + score + " )";
        db.QueryData(sql);
        Toast.makeText(this, "Added " + username + " Score: " + score, Toast.LENGTH_SHORT).show();
        getData();
    }
}
