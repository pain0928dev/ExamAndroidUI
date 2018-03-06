package pain0928dev.examandroidui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class DatePickerActivity extends AppCompatActivity {

    final String TAG = "DatePickerActivity";

    Context context;
    DatePicker datePicker;
    Button btnOk;
    String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);


        datePicker = (DatePicker)findViewById(R.id.datepicker);

        context = this;
        currentDate = "";

            datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                    currentDate = String.format("%d-%d-%d", i, i1+1, i2);
                    Log.v(TAG, "date:" + currentDate);
                    //Toast.makeText(context, "현재날짜:" + date, Toast.LENGTH_SHORT).show();
                }
            });
    }

    public void onClickOk(View v){

        Toast.makeText(this, "현재날짜:" + currentDate, Toast.LENGTH_SHORT).show();
    }
}
