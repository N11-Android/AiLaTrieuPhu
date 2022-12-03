package haui.android;

import static java.lang.Boolean.FALSE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import haui.android.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AiLaTrieuPhu.db";
    private static final int DATABASE_VERSION = 1;

    private static final String USER_TABLE = "USER";
    private static final String USER_ID = "id";
    private static final String USER_NAME = "username";
    private static final String USER_SCORE = "score";

    private static final String QUESTION_TABLE = "QUESTION";
    private static final String QUESTION_ID = "id";
    private static final String QUESTION_CONTENT = "content";

    private static final String ANSWER_TABLE = "ANSWER";
    private static final String ANSWER_ID = "id";
    private static final String ANSWER_CONTENT = "content";
    private static final String IS_TRUE = "is_true";

    private final Context myContext;

    private final String selectOrderedList = "SELECT * FROM " + USER_TABLE + " ORDER BY " + USER_SCORE + " DESC " + " LIMIT 10 ";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + USER_TABLE + " (" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_NAME + " TEXT, " + USER_SCORE + " INTEGER DEFAULT 0)";
        String createQuestionTable = "CREATE TABLE " + QUESTION_TABLE + " ( " + QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUESTION_CONTENT + " TEXT)";
        String createAnswerTable = "CREATE TABLE " + ANSWER_TABLE + " (" + ANSWER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ANSWER_CONTENT + " TEXT, " + IS_TRUE + " INTEGER, "
                + " question_id INTEGER NOT NULL REFERENCES " + QUESTION_TABLE + "(" + QUESTION_ID + ")" + ")";

        db.execSQL(createUserTable);
        db.execSQL(createQuestionTable);
        db.execSQL(createAnswerTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertData() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues forUser = new ContentValues();
        ContentValues forQuestion = new ContentValues();
        ContentValues forAnswer = new ContentValues();

        forUser.put(USER_NAME, "Nghiep Hoang");
        forUser.put(USER_SCORE, 1000);
        db.insert(USER_TABLE, null, forUser);

        forQuestion.put(QUESTION_CONTENT, "HOW OLD ARE U?");
        db.insert(QUESTION_TABLE, null, forQuestion);

        forAnswer.put(ANSWER_CONTENT, "20");
        forAnswer.put(QUESTION_ID, 0);
        forAnswer.put(IS_TRUE, FALSE);
        db.insert(ANSWER_TABLE, null, forAnswer);
    }

    public void copyDataBase() throws IOException
    {
        InputStream ip = myContext.getAssets().open(DATABASE_NAME);
        String op =  "/data/data/haui.android/databases/"  +  DATABASE_NAME ;
        OutputStream output = new FileOutputStream(op);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = ip.read(buffer))>0){
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();
        ip.close();
    }

    public ArrayList<User> getHighscoreList(){
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectOrderedList, null);
        if(c == null){
            return null;
        }

        int indexId = c.getColumnIndex(USER_ID);
        int indexName = c.getColumnIndex(USER_NAME);
        int indexScore = c.getColumnIndex(USER_SCORE);

        int user_id, user_score;
        String user_name;

        c.moveToFirst();

        ArrayList<User> user_list = new ArrayList<>();

        while (!c.isAfterLast()){
            user_id = c.getInt(indexId);
            user_name = c.getString(indexName);
            user_score = c.getInt(indexScore);

            user_list.add(new User(user_id, user_name, user_score));
            c.moveToNext();
        }
        c.close();
        db.close();
        return user_list;
    }
}
