//271113 - MtpA -	Just added a comment
//271113 - MtpA -        Added pass parameter to destination list
//241113 - MtpA -        Created class
package com.liftme.liftmeclient;

import java.util.Calendar;

import com.liftme.liftmeclient.MainActivity.destBtnListener;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Lifter extends Activity {

        private Button destinationButton;
        private TextView tvDisplayDate;
        private DatePicker dpResult;
        private Button btnChangeDate;

        private int year;
        private int month;
        private int day;
        
        private TextView tvDisplayTime;
        private TimePicker timePicker1;
        private Button btnChangeTime;
 
        private int hour;
        private int minute;
 
        static final int TIME_DIALOG_ID = 999;

        static final int DATE_DIALOG_ID = 998;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.lifter_details);
                Toast.makeText(getBaseContext(),"Looking to give someone a lift", Toast.LENGTH_LONG).show();


                destinationButton = (Button) findViewById(R.id.destinationsButton);
                destinationButton.setOnClickListener(new destBtnListener());

                btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
                btnChangeDate.setOnClickListener(new dateBtnListener());
                
                btnChangeTime = (Button) findViewById(R.id.btnChangeTime);
                btnChangeTime.setOnClickListener(new timeBtnListener());

                setCurrentDateOnView();
                setCurrentTimeOnView();
        }


        @Override
        protected void onResume() {
                super.onResume();
        } // method onResume

        class destBtnListener implements View.OnClickListener {
                @Override
                public void onClick(View v) {
                        Intent goIntent = new Intent(getApplicationContext(), DestinationList.class);
                        goIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        String str="Lifter";
                        goIntent.putExtra("Activity", str);
                        startActivity(goIntent);
                }
        }

        class dateBtnListener implements View.OnClickListener {
                @SuppressWarnings("deprecation")
                @Override
                public void onClick(View v) {                         
                        showDialog(DATE_DIALOG_ID);
                }
        }
        
        class timeBtnListener implements View.OnClickListener{

                @SuppressWarnings("deprecation")
                @Override
                public void onClick(View v) {
                        // TODO Auto-generated method stub
                        showDialog(TIME_DIALOG_ID);
                }
                
        }


        public void setCurrentDateOnView() {

                tvDisplayDate = (TextView) findViewById(R.id.tvDate);
                dpResult = (DatePicker) findViewById(R.id.dpResult);

                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                // set current date into textview
                tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

                // set current date into datepicker
                dpResult.init(year, month, day, null);

        }
        
        public void setCurrentTimeOnView() {
                 
                tvDisplayTime = (TextView) findViewById(R.id.tvTime);
                timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
 
                final Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);
 
                // set current time into textview
                tvDisplayTime.setText(
                    new StringBuilder().append(pad(hour))
                                       .append(":").append(pad(minute)));
 
                // set current time into timepicker
                timePicker1.setCurrentHour(hour);
                timePicker1.setCurrentMinute(minute);
 
        }

        @Override
        protected Dialog onCreateDialog(int id) {
                switch (id) {
                case DATE_DIALOG_ID:
                        // set date picker as current date
                        return new DatePickerDialog(this, datePickerListener, 
                                        year, month,day);
                }
                
                switch (id) {
                case TIME_DIALOG_ID:
                        // set time picker as current time
                        return new TimePickerDialog(this, 
                                        timePickerListener, hour, minute,false);
 
                }
                return null;
        }

        private DatePickerDialog.OnDateSetListener datePickerListener 
        = new DatePickerDialog.OnDateSetListener() {

                // when dialog box is closed, below method will be called.
                public void onDateSet(DatePicker view, int selectedYear,
                                int selectedMonth, int selectedDay) {
                        year = selectedYear;
                        month = selectedMonth;
                        day = selectedDay;

                        // set selected date into textview
                        tvDisplayDate.setText(new StringBuilder().append(month + 1)
                                        .append("-").append(day).append("-").append(year)
                                        .append(" "));

                        // set selected date into datepicker also
                        dpResult.init(year, month, day, null);

                }
        };
 
        private TimePickerDialog.OnTimeSetListener timePickerListener = 
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                int selectedMinute) {
                        hour = selectedHour;
                        minute = selectedMinute;
 
                        // set current time into textview
                        tvDisplayTime.setText(new StringBuilder().append(pad(hour))
                                        .append(":").append(pad(minute)));
 
                        // set current time into timepicker
                        timePicker1.setCurrentHour(hour);
                        timePicker1.setCurrentMinute(minute);
 
                }
        };
 
        private static String pad(int c) {
                if (c >= 10)
                   return String.valueOf(c);
                else
                   return "0" + String.valueOf(c);
        }
}
