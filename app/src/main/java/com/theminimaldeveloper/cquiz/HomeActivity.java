package com.theminimaldeveloper.cquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {


    private ConstraintLayout layout;
    private int i, totalScore, totalQuesAttempt;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView txt_total_score;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        layout=findViewById(R.id.layout);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);
        txt_total_score=findViewById(R.id.txt_total_score);

        sharedPreferences=getSharedPreferences("scoresFile", MODE_PRIVATE);

        for(i=2;i<=11; i++){
            View view=layout.getChildAt(i);
            final int chapterNo=i-1;


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

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                Uri uri;
                switch (item.getItemId()){
                    case R.id.about:
                            intent=new Intent(HomeActivity.this, ActivityAbout.class);
                            startActivity(intent);
                        break;

                    case R.id.source_code:
                            uri=Uri.parse("https://github.com/nirajwagh/android_quiz_app_C_plus_plus?files=1");
                            intent=new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        break;

                    case R.id.other_apps:
                            uri=Uri.parse("https://play.google.com/store/apps/developer?id=The+Minimal+Developer");
                            intent=new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        break;

                    case R.id.youtube_channel:
                            uri=Uri.parse("https://www.youtube.com/channel/UCG_1skZUBvz0MOjEe05TECQ/featured");
                            intent=new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        break;

                    case R.id.github:
                            uri=Uri.parse("https://github.com/nirajwagh");
                            intent=new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        break;
                }

                return false;


            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        totalScore=sharedPreferences.getInt("totalScore",0) ;
        totalQuesAttempt=sharedPreferences.getInt("totalQuesAttempt",0) ;

        String scoreDetails=totalScore+" / "+totalQuesAttempt;
        txt_total_score.setText(scoreDetails);
    }

    public void openNavBar(View view) {

        drawerLayout.openDrawer(Gravity.LEFT);
    }
}
