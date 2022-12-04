package haui.android.manager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import haui.android.model.playActivity.Question;


public class DatabaseManager {
    private String DATABASE_NAME = "AiLaTrieuPhu.db";
    private String DATABASE_PATH =
            Environment.getDataDirectory().getAbsolutePath()
                    + "/data/haui.android/databases/";

    private static final String SQL_GET_15_QUESTION = "select * from (select* from QUESTION order by random()) limit 15";

    private final Context context;

    private SQLiteDatabase sqLiteDatabase;

    public DatabaseManager(Context context) {
        this.context = context;
        copyDatabases();
    }

    private void copyDatabases() {
        try {
            File file = new File(DATABASE_PATH + DATABASE_NAME);
            if (file.exists()) {
                return;
            }
            AssetManager asset = context.getAssets();
            DataInputStream in = new DataInputStream(asset.open(DATABASE_NAME));
            DataOutputStream out = new DataOutputStream(
                    new FileOutputStream(file));
            byte[] b = new byte[1024];
            int length;
            while ((length = in.read(b)) != -1) {
                out.write(b, 0, length);
            }
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDatabase() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME,
                    null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    private void closeDatabase() {
        if (sqLiteDatabase != null
                && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public void writeScore() {
        ContentValues values = new ContentValues();
        values.put("Name", "player5");
        values.put("Score", 200000);
        insert("HighScore", values);
    }


    public void insert(String tableName, ContentValues values) {
        openDatabase();
        sqLiteDatabase.insert(tableName, null, values);
        closeDatabase();
    }

    public void delete(String tableName, String whereClause, String[] whereArgs) {
        openDatabase();
        sqLiteDatabase.delete(tableName, whereClause, whereArgs);
        closeDatabase();
    }

    public void update(String tableName, ContentValues values, String whereClause, String[] whereArgs) {
        openDatabase();
        sqLiteDatabase.update(tableName, values, whereClause, whereArgs);
        closeDatabase();
    }

    @SuppressLint("Range")
    public Question getQuestionByLevel(int lv) {
        openDatabase();
        String sql = "select * from Question where level = '" + lv + "' order by random()  limit 1";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor == null
                || cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();

        String question, caseA, caseB, caseC, caseD;
        int level, trueCase;

        question = cursor.getString(cursor.getColumnIndex("question"));
        caseA = cursor.getString(cursor.getColumnIndex("casea"));
        caseB = cursor.getString(cursor.getColumnIndex("caseb"));
        caseC = cursor.getString(cursor.getColumnIndex("casec"));
        caseD = cursor.getString(cursor.getColumnIndex("cased"));
        level = cursor.getInt(cursor.getColumnIndex("level"));
        trueCase = cursor.getInt(cursor.getColumnIndex("truecase"));
        Question question1 = new Question(question, caseA, caseB, caseC, caseD, trueCase);
        closeDatabase();
        return question1;
    }

    @SuppressLint("Range")
    public ArrayList<Question> get15Questions() {
        openDatabase();
        ArrayList<Question> questions = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_GET_15_QUESTION, null);

        if (cursor == null
                || cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            String q = cursor.getString(cursor.getColumnIndex("content"));
            String questionId = cursor.getString(cursor.getColumnIndex("id"));
            Cursor cursorAnswer = sqLiteDatabase.rawQuery("select * from ANSWER where question_id = '" + questionId + "'", null);
            if (cursorAnswer == null
                    || cursorAnswer.getCount() == 0) {
                return null;
            }
            List<String> answer = new ArrayList<>();
            int is_true = 0;
            cursorAnswer.moveToFirst();
            while (cursorAnswer.isAfterLast() == false) {
                answer.add(cursorAnswer.getString(cursorAnswer.getColumnIndex("content")));
                int checkTrue = cursorAnswer.getInt(cursorAnswer.getColumnIndex("is_true"));
                if (checkTrue == 0) {
                    is_true ++;
                }
                cursorAnswer.moveToNext();
            }
            questions.add(new Question(q, answer.get(0), answer.get(1), answer.get(2), answer.get(3), is_true));
            cursor.moveToNext();
        }
        closeDatabase();
        return questions;
    }
}