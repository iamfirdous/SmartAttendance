package com.example.lukhman.smartattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AttendanceActivity extends AppCompatActivity {

    private TextView tvWelcome, tvRollNo;
    private Button buttonPresent, buttonAbsent, buttonBack, buttonSubmit;
    private ListView listViewReport;

    private ArrayList<String> attendance;
    private ArrayAdapter<String> adapter;

    public static final String PRESENT = "Present", ABSENT = "Absent";

    //These values may change for you
    String profName = "Khaja Mohideen";
    int noOfStudents = 29, r = 1;

    boolean isSubmitted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        //Mapping all views from "activity_attendance.xml"
        tvWelcome = findViewById(R.id.textView_welcome);
        tvRollNo = findViewById(R.id.textView_roll_number);
        buttonPresent = findViewById(R.id.button_present);
        buttonAbsent = findViewById(R.id.button_absent);
        buttonBack = findViewById(R.id.button_back);
        buttonSubmit = findViewById(R.id.button_submit);
        listViewReport = findViewById(R.id.listView_attendance_report);

        //Initializing ArrayList and ArrayAdapter
        attendance = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text2, attendance);

        tvWelcome.append(" Mr." + profName + ".");
        tvRollNo.setText("" + r);

        buttonPresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (r < noOfStudents) {
                    attendance.add("Roll no.:  " + tvRollNo.getText().toString() + "  --  " + PRESENT);
                    tvRollNo.setText("" + (++r));
                }
                else if (r == noOfStudents) {
                    attendance.add("Roll no.:  " + tvRollNo.getText().toString() + "  --  " + PRESENT);
                    tvRollNo.setText("--");
                    tvRollNo.setEnabled(false);
                    buttonPresent.setEnabled(false);
                    buttonAbsent.setEnabled(false);

                    buttonSubmit.setEnabled(true);
                }
                else {

                }
            }
        });

        buttonAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (r < noOfStudents) {
                    attendance.add("Roll no.:  " + tvRollNo.getText().toString() + "  --  " + ABSENT);
                    tvRollNo.setText("" + (++r));
                }
                else if (r == noOfStudents) {
                    attendance.add("Roll no.:  " + tvRollNo.getText().toString() + "  --  " + ABSENT);
                    tvRollNo.setText("--");
                    tvRollNo.setEnabled(false);
                    buttonPresent.setEnabled(false);
                    buttonAbsent.setEnabled(false);

                    buttonSubmit.setEnabled(true);
                }
                else {
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //I'm using isSubmitted because after the submission, the value of r(Roll Number) should not be decremented.
                if (isSubmitted) {
                    isSubmitted = false;
                    listViewReport.setAdapter(null);
                    tvRollNo.setText("" + r);

                    //I'm removing (r-1)th item because, index value of the array starts at ZERO.
                    attendance.remove(r - 1);

                    tvRollNo.setEnabled(true);
                    buttonPresent.setEnabled(true);
                    buttonAbsent.setEnabled(true);

                    buttonSubmit.setEnabled(false);
                }
                else {
                    listViewReport.setAdapter(null);
                    r--;
                    tvRollNo.setText("" + r);

                    //I'm removing (r-1)th item because, index value of the array starts at ZERO.
                    attendance.remove(r - 1);

                    tvRollNo.setEnabled(true);
                    buttonPresent.setEnabled(true);
                    buttonAbsent.setEnabled(true);

                    buttonSubmit.setEnabled(false);
                }
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSubmitted = true;
                listViewReport.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
