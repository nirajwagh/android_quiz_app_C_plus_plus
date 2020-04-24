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
    private int i;
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
        editor=sharedPreferences.edit();

        final String scoreText=sharedPreferences.getInt("toatlScore",0) + "/" + sharedPreferences.getInt("totalAttempted", 0);
        txt_total_score.setText(scoreText);
        

        for(i=1;i<=10; i++){
            View view=layout.getChildAt(i);
            final int chapterNo=i;
            Log.d("taggg", view.getTag().toString());

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
}
