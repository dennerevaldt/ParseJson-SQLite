package br.unisinos.tarefa02rr.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by dennerevaldtmachado on 06/07/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tarefa2_rr.sqlite";
    private static final String MESSAGE_CREATE_TABLE = "CREATE TABLE" +
            " IF NOT EXISTS User ("+
            " ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " name TEXT NOT NULL, " +
            " username TEXT NOT NULL, " +
            " email TEXT NOT NULL " +
            ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MESSAGE_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("version", Integer.toString(oldVersion) + ">" + Integer.toString(newVersion));
    }
}
