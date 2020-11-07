package com.lwazir.aad.notetakingapp.SQLiteDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.lwazir.aad.notetakingapp.SQLiteDB.NoteDatabase.CourseInfoEntry;
import com.lwazir.aad.notetakingapp.SQLiteDB.NoteDatabase.NoteInfoEntry;

public class NoteKeeperOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="NoteDatabase.db";
    public static final int DATABASE_VERSION = 2;

    public NoteKeeperOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CourseInfoEntry.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(NoteInfoEntry.SQL_CREATE_TABLE);

        sqLiteDatabase.execSQL(CourseInfoEntry.SQL_CREATE_INDEX);
        sqLiteDatabase.execSQL(NoteInfoEntry.SQL_CREATE_INDEX);
        //Adding Sample Data
        DatabaseDataWorker worker = new DatabaseDataWorker(sqLiteDatabase);
        worker.insertCourses();
        worker.insertSampleNotes();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            sqLiteDatabase.execSQL(CourseInfoEntry.SQL_CREATE_INDEX);
            sqLiteDatabase.execSQL(NoteInfoEntry.SQL_CREATE_INDEX);

        }
    }
}
