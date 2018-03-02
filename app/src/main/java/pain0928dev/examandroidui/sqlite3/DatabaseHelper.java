package pain0928dev.examandroidui.sqlite3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/**
 * Created by ljh0928 on 2018. 3. 2..
 */

public class DatabaseHelper extends SQLiteOpenHelper implements SqlImp {

    private Context context;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String userInfoQuery =
                "       CREATE TABLE IF NOT EXISTS user_info (\n" +
                        "            id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "            name TEXT NOT NULL,\n" +
                        "            birthday DATE NOT NULL,\n" +
                        "            sex TEXT NOT NULL,\n" +
                        "            part TEXT NOT NULL\n" +
                        "            );";


        String exerciseResultQuery =
                "        CREATE TABLE IF NOT EXISTS exercise_result (\n" +
                        "                idx INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "                emg_avr1 TEXT,\n" +
                        "                emg_avr2 TEXT,\n" +
                        "                pre_exercise_idx_fk INTEGER REFERENCES exercise_result(idx) on delete cascade, \n" +
                        "                user_info_id_fk INTEGER NOT NULL REFERENCES user_info(id) on delete cascade);";

        sqLiteDatabase.execSQL(userInfoQuery);
        sqLiteDatabase.execSQL(exerciseResultQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS exercise_result");
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user_info");
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if(!db.isReadOnly()) {
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                String query = String.format("PRAGMA foreign_keys = %s", "ON");
                db.execSQL(query);
            } else {
                db.setForeignKeyConstraintsEnabled(true);
            }
        }
    }
}
