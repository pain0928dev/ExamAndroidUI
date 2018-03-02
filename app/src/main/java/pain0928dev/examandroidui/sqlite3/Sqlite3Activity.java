package pain0928dev.examandroidui.sqlite3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import pain0928dev.examandroidui.R;

public class Sqlite3Activity extends AppCompatActivity {

    final String TAG = "MainActivity";

    TextView tvResult;
    EditText etInput;
    TextView tvQuery;


    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite3);

        tvResult = (TextView)findViewById(R.id.tvResult);
        etInput = (EditText) findViewById(R.id.etInput);
        tvQuery = (TextView)findViewById(R.id.tvQuery);

        databaseHelper = new DatabaseHelper(this);

        // 외래키 사용을 위해서 환경 설정이 필요함
        databaseHelper.onConfigure(databaseHelper.getWritableDatabase());
        databaseHelper.onUpgrade(databaseHelper.getWritableDatabase(), 1, 1);

        //insertUserInfoData();
        insertExerciseResult();

        setExamQuery();
    }

    public void insertUserInfoData(){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // id, name, birthday, sex, part
        sqLiteDatabase.execSQL("INSERT INTO user_info VALUES(null, 'park', '1980-1-28', 'M', 'LR')");
        sqLiteDatabase.execSQL("INSERT INTO user_info VALUES(null, 'hong', '1980-2-28', 'M', 'LR')");
        sqLiteDatabase.execSQL("INSERT INTO user_info VALUES(null, 'jung', '1980-3-28', 'F', 'LR')");
        sqLiteDatabase.execSQL("INSERT INTO user_info VALUES(null, 'lee', '1980-4-28', 'F', 'LR')");
        sqLiteDatabase.close();
    }

    public void insertExerciseResult(){

        // idx, emgAvr1, emgAvr2, pre_exercise_idx_fk, user_info_id_fk
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        try {
            sqLiteDatabase.execSQL("INSERT INTO exercise_result VALUES(null, '100', '100', null, 1)");
            sqLiteDatabase.execSQL("INSERT INTO exercise_result VALUES(null, '101', '101', 1, 1)");
            sqLiteDatabase.execSQL("INSERT INTO exercise_result VALUES(null, '102', '102', null, 1)");
            sqLiteDatabase.execSQL("INSERT INTO exercise_result VALUES(null, '103', '103', 3, 1)");
            //sqLiteDatabase.execSQL("INSERT INTO exercise_result VALUES(null, '105', '105', null, 6)");
        } catch (RuntimeException e) {
            Log.e(TAG,"Msg: " + e);
        }

        sqLiteDatabase.close();
    }

    public void onClickRunQuery(View v){

        String inputQuery = etInput.getText().toString();

        if(!inputQuery.isEmpty()){
            SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

            Cursor cursor;
            cursor = sqLiteDatabase.rawQuery(inputQuery, null);

            /*
            // user_info
            cursor.getColumnIndex("id")
            cursor.getColumnIndex("name")
            cursor.getColumnIndex("birthday")
            cursor.getColumnIndex("sex")
            cursor.getColumnIndex("part");
            */

            /*
            // exercise_result
            cursor.getColumnIndex("idx")
            cursor.getColumnIndex("emg_avr1")
            cursor.getColumnIndex("emg_avr2")
            cursor.getColumnIndex("pre_exercise_idx_fk")
            cursor.getColumnIndex("user_info_id_fk")
            */

            String resultQuery = "";

            try {
            /*
            while (cursor.moveToNext()) {
                resultQuery += cursor.getString(0);
                resultQuery += " ";
                resultQuery += cursor.getString(1);
                resultQuery += " ";
                resultQuery += cursor.getString(2);
                resultQuery += " ";
                resultQuery += cursor.getString(3);
                resultQuery += " ";
            }
            */


                while (cursor.moveToNext()) {
                    /*
                    resultQuery += cursor.getString(cursor.getColumnIndex("idx"));
                    resultQuery += " ";
                    resultQuery += cursor.getString(cursor.getColumnIndex("emg_avr1"));
                    resultQuery += " ";
                    resultQuery += cursor.getString(cursor.getColumnIndex("emg_avr2"));
                    resultQuery += " ";
                    resultQuery += cursor.getString(cursor.getColumnIndex("pre_exercise_idx_fk"));
                    resultQuery += " ";
                    resultQuery += cursor.getString(cursor.getColumnIndex("user_info_id_fk"));
                    resultQuery += "\n";
                    */
                    resultQuery += cursor.getString(0);
                    resultQuery += " ";
                    resultQuery += cursor.getString(1);
                    resultQuery += " ";
                    resultQuery += cursor.getString(2);
                    resultQuery += " ";
                    resultQuery += cursor.getString(3);
                    resultQuery += " ";
                    resultQuery += cursor.getString(4);
                    resultQuery += "\n";
                }
            } catch (IllegalStateException e){
                Log.e(TAG, "Msg: " + e);
            }

            if(resultQuery.isEmpty()){
                tvResult.setText("There is no Data");
            }

            Log.i(TAG, "result:" + resultQuery);
            tvResult.setText(resultQuery);
        }
    }

    public void getFiled(){
        // SELECT * FROM exercise_result join exercise on exercise.idx = exercise.pre_exercise;
    }

    public void setExamQuery(){
        String query = "select * from exercise_result where idx = pre_exercise_idx_fk;\n" +
                "select * from user_info;\n";

        tvQuery.setText(query);
    }
}
