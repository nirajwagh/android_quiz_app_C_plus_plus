package com.theminimaldeveloper.cquiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class databaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "quizQuesDB.db";
    private static final int DATABASE_VERSION = 1;

    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getSetQuestions(String setDetails){
        SQLiteDatabase db=getReadableDatabase();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        String table="newQuiz";

        String[] columns={"question", "optionA", "optionB", "optionC", "optionD", "answerKey"};
        String[] key={setDetails};


        qb.setTables(table);

        Cursor cursor=qb.query(db, columns, "setDetails=?", key, null,null,null);

        cursor.moveToFirst();
        return cursor;
    }
}
