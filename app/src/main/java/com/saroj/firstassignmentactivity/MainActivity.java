package com.saroj.firstassignmentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {
    TextView tvCheckin, tvCheckOut,result;
    Spinner spinroom;
    EditText etRoom,etAdult,etChildren;
    Button btnCalculate;
    DatePickerDialog datePickerDialog;
    Calendar c1,c2; @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCheckin = findViewById(R.id.checkinDate);
        tvCheckOut = findViewById(R.id.checkoutDate);
        spinroom=findViewById(R.id.roomType);
        btnCalculate=findViewById(R.id.calculate);
        etRoom=findViewById(R.id.room);
        etChildren=findViewById(R.id.children);
        etAdult=findViewById(R.id.adult);
        result=findViewById(R.id.total);

        String rooms[]={"Deluxe","Professional","Premium"};
        ArrayAdapter adapter=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                rooms
        );
        spinroom.setAdapter(adapter);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (TextUtils.isEmpty(tvCheckin.getText())) {
                        tvCheckin.setError("Please Select Check In Date");
                        Toast.makeText(getApplicationContext(), "Please Select Check In Date", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(tvCheckOut.getText())) {
                        tvCheckOut.setError("Please Select Check Out Date");
                        Toast.makeText(getApplicationContext(), "Please Select Check Out Date", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(etAdult.getText())) {
                        etAdult.setError("Please Enter Number Of Adult");
                        Toast.makeText(getApplicationContext(), "Please Enter Number Of Adult", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(etChildren.getText())) {
                        etChildren.setError("Please Enter Number Of Child");
                        Toast.makeText(getApplicationContext(), "Please Enter Number Of Child", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(etRoom.getText())) {
                        etRoom.setError("Please Enter Number Of Room");
                        Toast.makeText(getApplicationContext(), "Please Enter Number Of Room", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String checkin = tvCheckin.getText ().toString ();
                    String checkout = tvCheckOut.getText ().toString ();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                    Date in = simpleDateFormat.parse(checkin);
                    Date out = simpleDateFormat.parse(checkout);
                    long difference = Math.abs(in.getTime() - out.getTime());

                    long difftDays = difference / (24 * 60 * 60 * 1000);

                    Log.i("Testing","days" +difftDays);

                    String typeOfRoom = spinroom.getSelectedItem().toString();

                    float price=0;
                    if(typeOfRoom=="Deluxe"){
                        price=2000;

                    }
                    else if(typeOfRoom=="Premium"){
                        price=4000;

                    }
                    else {
                        price=5000;
                    }
                    int room=1;

                    room=Integer.parseInt(etRoom.getText().toString());

                    float total=difftDays*price*room;
                    float vat=	total*13/100;
                    float Gross=vat+total;

                    result.setText("total: " +total+"\n VAT: "+vat+"\n Grand Total: "+Gross);


                }
                catch(Exception ex)
                {

                    ex.printStackTrace();
                }
            }
        });


        tvCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                c1 = Calendar.getInstance();
                int mYear = c1.get(Calendar.YEAR); // current year
                int mMonth = c1.get(Calendar.MONTH); // current month
                int mDay = c1.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this,new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {


                        String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                        String mon=MONTHS[monthOfYear];

                        tvCheckin.setText(dayOfMonth + "-"
                                + (mon) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        tvCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                c2 = Calendar.getInstance();
                int mYear = c2.get(Calendar.YEAR); // current year
                int mMonth = c2.get(Calendar.MONTH); // current month
                int mDay = c2.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                        String mon=MONTHS[monthOfYear];
                        tvCheckOut.setText(dayOfMonth + "-" + (mon) + "-" + year);



                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });




    }
}