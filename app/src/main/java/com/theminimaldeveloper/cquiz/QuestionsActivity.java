package com.theminimaldeveloper.cquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {

    private int currQueNo=0, totalQues;
    private TextView txt_question, txt_optionA, txt_optionB, txt_optionC, txt_optionD, txt_que_count, txt_score;
    private Button btn_quit, btn_next;
    private ArrayList<QuestionModel> arrayList=new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        txt_question=findViewById(R.id.txt_question);
        txt_optionA=findViewById(R.id.txt_optionA);
        txt_optionB=findViewById(R.id.txt_optionB);
        txt_optionC=findViewById(R.id.txt_optionC);
        txt_optionD=findViewById(R.id.txt_optionD);
        txt_que_count=findViewById(R.id.txt_que_count);
        txt_score=findViewById(R.id.txt_score);

        final String setDetails=getIntent().getStringExtra("setDetails");

        databaseHelper helper=new databaseHelper(this);
        Cursor cursor=helper.getSetQuestions(setDetails);


        do{
            arrayList.add(new QuestionModel(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)));
        }while (cursor.moveToNext());

        totalQues=arrayList.size();

        setQuestion();
    }

    public void setQuestion(){
        txt_question.setText(arrayList.get(currQueNo).getQuestion());
        txt_optionA.setText(arrayList.get(currQueNo).getOptionA());
        txt_optionB.setText(arrayList.get(currQueNo).getOptionB());
        txt_optionC.setText(arrayList.get(currQueNo).getOptionC());
        txt_optionD.setText(arrayList.get(currQueNo).getOptionD());
        txt_que_count.setText("Que: "+(currQueNo+1)+"/"+totalQues);
    }


    public void btnQuit(View view) {
    }

    public void btnNextQuestion(View view) {

        currQueNo++;
        setQuestion();
    }
}
