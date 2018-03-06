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

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatePickerActivity extends AppCompatActivity {

    final String TAG = "DatePickerActivity";

    Context context;
    DatePicker datePicker;
    Button btnOk;
    int year, month, day;
    String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        datePicker = (DatePicker)findViewById(R.id.datepicker);
        btnOk = (Button)findViewById(R.id.btnWindow);

        context = this;
        currentDate = "";

        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day= calendar.get(Calendar.DAY_OF_MONTH);


        ////////////////////////////////////////////////////////////////////////////////////////
        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth()
                , new DatePicker.OnDateChangedListener(){
            @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                currentDate = String.format("%d-%d-%d", year, monthOfYear+1, dayOfMonth);
            }
                });
    }

    public void onClickOk(View v){

        //Toast.makeText(this, "현재날짜:" + currentDate, Toast.LENGTH_SHORT).show();
        new DatePickerDialog(this, dateSetListener, year, month, day).show();
    }

    public void updateDate(){
        btnOk.setText(currentDate);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            currentDate = String.format("%d / %d / %d", year,monthOfYear+1, dayOfMonth);
            updateDate();
            Toast.makeText(context, currentDate, Toast.LENGTH_SHORT).show();
        }
    };

}
