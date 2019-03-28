package elitlabs.com.cebbillcalculator;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    MaterialSpinner mCategory;
    EditText mUnits;
    TextView mFromdate, mTodate;
    Button mCalculate;

    Calendar calendar, calendar1;
    DatePickerDialog datePickerDialog, datePickerDialog1;
    int day, month, year, day1, month1, year1;
    String from_date, to_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mCategory = (MaterialSpinner)findViewById(R.id.category);
        mUnits = (EditText) findViewById(R.id.units);
        mFromdate = (TextView) findViewById(R.id.fromdate);
        mTodate = (TextView) findViewById(R.id.todate);
        mCalculate = (Button)findViewById(R.id.calculate);

        mCategory.setItems("Choose Category", "Domestic", "Industrial 1", "General Purpose 1", "Gov. Universities", "Gov. Hosp n Schools", "Hotel 1", "Religious & Charity");

        mFromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                day = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(HomeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {

                        Date d = new Date(mYear, mMonth, mDay);
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("yy-MM-dd");
                        from_date = dateFormatter.format(d);
                        mFromdate.setText(from_date);
                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        });

        mTodate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar1 = Calendar.getInstance();
                day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                month1 = calendar1.get(Calendar.MONTH);
                year1 = calendar1.get(Calendar.YEAR);

                datePickerDialog1 = new DatePickerDialog(HomeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {

                        Date d = new Date(mYear, mMonth, mDay);
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("yy-MM-dd");
                        to_date = dateFormatter.format(d);
                        mTodate.setText(to_date);
                    }
                }, day1, month1, year1);
                datePickerDialog1.show();
            }
        });


        mCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate_bill();
            }
        });
    }

    private void calculate_bill() {

        String iUnits = mUnits.getText().toString();
        
        if (TextUtils.isEmpty(iUnits)){
            Toast.makeText(this, "Please enter the Number of Units", Toast.LENGTH_SHORT).show();
        }

        System.out.println("From date : " + from_date);
        System.out.println("To date   : " + to_date);

        if (Integer.parseInt(from_date) > Integer.parseInt(to_date)){
            System.out.println("Please select a valid date range");
        }


    }
}
