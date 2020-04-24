package com.theminimaldeveloper.cquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {


    private ConstraintLayout layout;
    private int i, totalScore, totalQuesAttempt;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView txt_total_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        layout=findViewById(R.id.layout);
        txt_total_score=findViewById(R.id.txt_total_score);

        sharedPreferences=getSharedPreferences("scoresFile", MODE_PRIVATE);

        for(i=1;i<=10; i++){
            View view=layout.getChildAt(i);
            final int chapterNo=i;


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(HomeActivity.this, SetsActivity.class);
                    intent.putExtra("chapter", view.getTag().toString());
                    intent.putExtra("chapterNo", chapterNo);
                    startActivity(intent);
                }
            });
        }



    }

    @Override
    protected void onStart() {
        super.onStart();

        totalScore=sharedPreferences.getInt("totalScore",0) ;
        totalQuesAttempt=sharedPreferences.getInt("totalQuesAttempt",0) ;

        String scoreDetails=totalScore+"/"+totalQuesAttempt;
        txt_total_score.setText(scoreDetails);
    }
}
