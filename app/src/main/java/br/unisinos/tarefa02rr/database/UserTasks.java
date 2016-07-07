package br.unisinos.tarefa02rr.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.unisinos.tarefa02rr.models.User;

/**
 * Created by dennerevaldtmachado on 06/07/16.
 */
public class UserTasks {

    public void saveUsers(ArrayList<User> userList, Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (int i = 0; i < userList.size(); i++) {

            ContentValues values = new ContentValues();
            values.put("name", userList.get(i).getName());
            values.put("username", userList.get(i).getUsername());
            values.put("email", userList.get(i).getEmail());

            db.insert("User", null, values);
        }

        db.close();
    }

    public ArrayList<User> getUsers(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<User> userArrayList = new ArrayList<>();

        Cursor cursor = db.query("User", new String[]{"ID", "name", "username", "email"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            userArrayList.add(new User(
                    cursor.getString(cursor.getColumnIndex("ID")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("username")),
                    cursor.getString(cursor.getColumnIndex("email"))
            ));
        }
        db.close();
        return userArrayList;
    }

    public void cleanUsers(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete("User", null, null);
    }
}
