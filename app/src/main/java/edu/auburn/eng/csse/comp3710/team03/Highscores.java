package edu.auburn.eng.csse.comp3710.team03;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jon on 4/23/2015.
 */
public class Highscores extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Score_DB";
    protected static final String FIRST_TABLE_NAME = "USER_TABLE";
    protected static final String SECOND_TABLE_NAME = "SCORE_TABLE";

    public static final String CREATE_FIRST_TABLE = "CREATE TABLE IF NOT EXISTS "
            + FIRST_TABLE_NAME
            + " ( _id integer primary key autoincrement, COL1 TEXT NOT NULL);";

    public static final String CREATE_SECOND_TABLE = "CREATE TABLE IF NOT EXISTS "
            + SECOND_TABLE_NAME
            + " ( _id integer primary key autoincrement, COL1 INTEGER NOT NULL);";

    public Highscores(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //onUpgrade(this.getWritableDatabase(), 0, 0);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FIRST_TABLE);
        db.execSQL(CREATE_SECOND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FIRST_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SECOND_TABLE_NAME);
        onCreate(db);
    }

    public void addInformation(String username, int score) {

        ContentValues val1 = new ContentValues();
        ContentValues val2 = new ContentValues();
        Log.i("Highscores", "1");
        val1.put("COL1", username);
        Log.i("Highscores", "2");
        val2.put("COL1", score);
        Log.i("Highscores", "3");

        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("Highscores", "4");
        db.insert(FIRST_TABLE_NAME, null, val1);
        Log.i("Highscores", "5");
        db.insert(SECOND_TABLE_NAME, null, val2);
        Log.i("Highscores", "6");

        db.close();
    }

    public ArrayList<String> pullUsers() {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"COL1"};

        Cursor cursor = db.query(FIRST_TABLE_NAME, columns, null, null, null, null, null);

        ArrayList<String> usernames = new ArrayList<String>();

        cursor.moveToFirst();
        if (!cursor.isNull(0)) {
            do {
                usernames.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return usernames;
    }

    public ArrayList<Integer> pullScores() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"COL1"};

        Cursor cursor = db.query(SECOND_TABLE_NAME, columns, null, null, null, null, null);

        ArrayList<Integer> scores = new ArrayList<Integer>();
        /**/
        cursor.moveToFirst();
        if (!cursor.isNull(0)) {
            do {
                scores.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        /**/
        /*
        int i = 0;
        cursor.moveToFirst();
        while(!cursor.isNull(0) && cursor.moveToNext()) {
            if (i == 0) {
                cursor.moveToFirst();
                scores.add(cursor.getInt(0));
                i++;
            }
            else {
                scores.add(cursor.getInt(0));
                i++;
            }
        }
        */
        cursor.close();
        db.close();
        return scores;
    }

    public boolean deleteProduct() {

        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(FIRST_TABLE_NAME, null, null, null, null, null, null);
        Cursor cursor2 = db.query(SECOND_TABLE_NAME, null, null, null, null, null, null);

        cursor.moveToFirst();
        cursor2.moveToFirst();
        String user = cursor.getString(0);
        int score = cursor2.getInt(1);

        db.delete(FIRST_TABLE_NAME, "Id="+user, null);
        db.delete(SECOND_TABLE_NAME, "Id="+score, null);
        result = true;
        db.close();
        return result;
    }
}
