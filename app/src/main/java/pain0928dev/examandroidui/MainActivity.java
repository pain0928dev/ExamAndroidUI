package pain0928dev.examandroidui;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import pain0928dev.examandroidui.userinfo.ManageConfiguration;

public class MainActivity extends AppCompatActivity {

    final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview;
        ListViewAdapter listViewAdapter;

        listViewAdapter = new ListViewAdapter();
        listview = (ListView)findViewById(R.id.mainListview);
        listview.setAdapter(listViewAdapter);

        // add list item
        listViewAdapter.addItem(ContextCompat.getDrawable(this, R.mipmap.ic_done_white_36dp), "Sqlite3 Test", "Android Sqlite3 테스트 UI 화면 입니다");
        listViewAdapter.addItem(ContextCompat.getDrawable(this, R.mipmap.ic_done_white_36dp), "Notification Test", "Android Notification 테스트 UI 화면 입니다");

        ManageConfiguration.getInstance().init(getApplicationContext());

        Log.v(TAG, "Name:" + ManageConfiguration.getInstance().getUserName() );
    }
}
