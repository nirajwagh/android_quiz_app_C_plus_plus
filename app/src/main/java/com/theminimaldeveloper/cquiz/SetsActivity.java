package com.theminimaldeveloper.cquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class SetsActivity extends AppCompatActivity {

    private int[] setsCounts={3,1,0,0,0,0,0,0,0,0};
    private String chapter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int[] setsScores, setsQueAttempted;
    int i, chapterNo;
    private RecyclerView recycler;
    private TextView txt_chapter_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);

        recycler=findViewById(R.id.recycler);
        txt_chapter_title=findViewById(R.id.txt_chapter_title);

        chapter=getIntent().getStringExtra("chapter");
        Log.d("chappp", chapter);
        chapterNo=getIntent().getIntExtra("chapterNo", 0);

        txt_chapter_title.setText(chapter);

        final int chapterSetsCount=setsCounts[chapterNo-1];

        recycler.setLayoutManager(new LinearLayoutManager(this));

        setsScores=new int[chapterSetsCount];
        setsQueAttempted=new int[chapterSetsCount];

        sharedPreferences=getSharedPreferences("scoresFile", MODE_PRIVATE);
        editor=sharedPreferences.edit();


        for(i=0; i<chapterSetsCount; i++){
            setsScores[i]=sharedPreferences.getInt(chapter+"set"+i+"score", 0);
            setsQueAttempted[i]=sharedPreferences.getInt(chapter+"set"+i+"attempted", 0);
        }

        recycler.setAdapter(new SetsAdapter(setsScores, setsQueAttempted, getApplicationContext(), chapter));





    }


}
