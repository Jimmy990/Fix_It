package com.example.myapplication.Mechanical;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Modle.MechanicalDataBase;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MechanicalFragment extends Fragment {
    private static final String TAG = "CustomerFragment";


    View view;
    private EditText mechanical_first_name;
    private EditText mechanical_second_name;
    private EditText mechanical_phone_number;
    private Button mechanical_mDisplayDate;
    private EditText mechanical_email;
    private EditText mechanical_password;
    private EditText mechanical_re_password;
    private CheckBox mechanical_checkBox;
    private TextView mechanical_read_rules;
    private Button mechanical_register;
    private String mech_firebase_id;
    MechanicalDataBase mechanicalDataBase;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    DatabaseReference mech_Reference;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public MechanicalFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mechanical, container, false);
        findView();
        selectDate();
        vaildateCreatingAccount();
        return view;
    }

    private void pushMechanicalData() {
        mech_Reference = FirebaseDatabase.getInstance().getReference("Mechanical").child("Mechanical`s Users");
        MechanicalDataBase mechanicalDataBase = new MechanicalDataBase(
                mechanical_first_name.getText().toString(),
                mechanical_second_name.getText().toString(),
                mechanical_phone_number.getText().toString(),
                mechanical_mDisplayDate.getText().toString(),
                mechanical_email.getText().toString(),
                mechanical_password.getText().toString(),
                mech_firebase_id);
        DatabaseReference databaseReference = mech_Reference.push();
        mechanicalDataBase.setMech_firebase_id(databaseReference.getKey());
        databaseReference.setValue(mechanicalDataBase);

    }

    private void vaildateCreatingAccount() {

        mechanical_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mechanical_first_name.getText().toString().isEmpty()) {
                    mechanical_first_name.setError(getString(R.string.Required));
                } else if (mechanical_second_name.getText().toString().isEmpty()) {
                    mechanical_second_name.setError(getString(R.string.Required));
                } else if (mechanical_phone_number.getText().toString().isEmpty()) {
                    mechanical_phone_number.setError(getString(R.string.Required));
                } else if ((mechanical_phone_number.getText().toString().length() != 11)) {
                    Toast.makeText(getContext(), R.string.please_entre_your_right_phone, Toast.LENGTH_SHORT).show();
                } else if (mechanical_email.getText().toString().isEmpty()) {
                    mechanical_email.setError(getString(R.string.Required));
                } else if (!Patterns.EMAIL_ADDRESS.matcher(mechanical_email.getText().toString()).matches()) {
                    mechanical_email.setError(getString(R.string.please_enter_a_valid_email));
                } else if (mechanical_password.getText().toString().isEmpty()) {
                    mechanical_password.setError(getString(R.string.Required));
                } else if (!(mechanical_password.getText().toString().length() >= 6)) {
                        Toast.makeText(getContext(), "Password should be more than 6 characters", Toast.LENGTH_SHORT).show();
                } else if (mechanical_re_password.getText().toString().isEmpty()) {
                    mechanical_re_password.setError(getString(R.string.Required));
                } else if (!mechanical_password.getText().toString().equals(mechanical_re_password.getText().toString())) {
                    Toast.makeText(getContext(), R.string.not_match, Toast.LENGTH_SHORT).show();
                } else {

                    firebaseAuth.createUserWithEmailAndPassword(mechanical_email.getText().toString(), mechanical_password.
                            getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            pushMechanicalData();
                            startActivity(new Intent(getContext(),MechanecalActivity.class));
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    if (e.getLocalizedMessage() != null) {
                                        if (e.getLocalizedMessage().contains("email address"))
                                            Toast.makeText(getContext(), "The email is used before", Toast.LENGTH_SHORT).show();
                                        else if (e.getLocalizedMessage().contains("network error")) {
                                            Toast.makeText(getContext(), "No internet connection,Please try again later", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });


                }
            }
        });
    }

    private void selectDate() {
        mechanical_mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mechanical_mDisplayDate.setText(date);
            }
        };
    }

    private void findView() {
        mechanical_first_name = view.findViewById(R.id.mechanical_first_name_data);
        mechanical_second_name = view.findViewById(R.id.mechanical_second_name_data);
        mechanical_phone_number = view.findViewById(R.id.mechanical_phone_number_data);
        mechanical_mDisplayDate = view.findViewById(R.id.mechanical_age_data);
        mechanical_email = view.findViewById(R.id.mechanical_email_data);
        mechanical_password = view.findViewById(R.id.mechanical_password1_data);
        mechanical_re_password = view.findViewById(R.id.mechanical_password2_data);
        mechanical_checkBox = view.findViewById(R.id.mechanical_checkbox);
        mechanical_read_rules = view.findViewById(R.id.mechanical_read_rules);
        mechanical_register = view.findViewById(R.id.mechanical_register);
    }
}
