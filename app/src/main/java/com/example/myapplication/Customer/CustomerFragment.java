package com.example.myapplication.Customer;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Mechanical.MechanecalActivity;
import com.example.myapplication.Modle.CustomerDataBase;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerFragment extends Fragment {
    private static final String TAG = "CustomerFragment";
    View view;
    private EditText customer_first_name;
    private EditText customer_second_name;
    private EditText customer_phone_number;
    private Button customer_mDisplayDate;
    private EditText customer_email;
    private EditText customer_password;
    private EditText customer_re_password;
    private CheckBox customer_checkBox;
    private TextView customer_read_rules;
    private Button customer_register;
    private ImageView customer_hide;
    private String cus_firebase_id;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private FirebaseAuth firebaseAuth;
    DatabaseReference cus_Reference;
    CustomerDataBase customerDataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_customer, container, false);
        findView();
        selectDate();
        vaildateCreatingAccount();



        return view;
    }


    public void pushCustomerData() {
        cus_Reference = FirebaseDatabase.getInstance().getReference("Customer").child("Customer`s Users");
        customerDataBase = new CustomerDataBase(
                customer_first_name.getText().toString(),
                customer_second_name.getText().toString(),
                customer_phone_number.getText().toString(),
                customer_mDisplayDate.getText().toString(),
                customer_email.getText().toString(),
                customer_password.getText().toString(),
                cus_firebase_id
        );
        DatabaseReference databaseReference = cus_Reference.push();
        customerDataBase.setCus_firebase_id(databaseReference.getKey());
        databaseReference.setValue(customerDataBase);

    }


    public void vaildateCreatingAccount() {
        firebaseAuth = FirebaseAuth.getInstance();
        customer_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customer_first_name.getText().toString().isEmpty()) {
                    customer_first_name.setError(getString(R.string.Required));
                } else if (customer_second_name.getText().toString().isEmpty()) {
                    customer_second_name.setError(getString(R.string.Required));
                } else if (customer_phone_number.getText().toString().isEmpty()) {
                    customer_phone_number.setError(getString(R.string.Required));
                } else if (customer_phone_number.getText().toString().length() != 11) {
                    Toast.makeText(getContext(), R.string.please_entre_your_right_phone, Toast.LENGTH_SHORT).show();
                } else if (customer_mDisplayDate.getText().toString().equals("Select Date")) {
                    customer_mDisplayDate.setError("Please select your date");
                } else if (customer_email.getText().toString().isEmpty()) {
                    customer_email.setError(getString(R.string.Required));
                } else if (!Patterns.EMAIL_ADDRESS.matcher(customer_email.getText().toString()).matches()) {
                    customer_email.setError(getString(R.string.please_enter_a_valid_email));
                } else if (customer_password.getText().toString().isEmpty()) {
                    customer_password.setError(getString(R.string.Required));
                } else if (!(customer_password.getText().toString().length() >= 6)) {
                        Toast.makeText(getContext(), "Password should be more than 6 characters", Toast.LENGTH_SHORT).show();
                } else if (customer_re_password.getText().toString().isEmpty()) {
                    customer_re_password.setError(getString(R.string.Required));
                } else if (!customer_password.getText().toString().equals(customer_re_password.getText().toString())) {
                    Toast.makeText(getContext(), R.string.not_match, Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.createUserWithEmailAndPassword(customer_email.getText().toString(), customer_password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    pushCustomerData();
                                    startActivity(new Intent(getContext(), MechanecalActivity.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    if (e.getLocalizedMessage() != null) {
                                        if (e.getLocalizedMessage().contains("email address"))
                                            Toast.makeText(getContext(), "The email is used before", Toast.LENGTH_SHORT).show();
                                        else if (e.getLocalizedMessage().contains("network error")) {
                                            Toast.makeText(getContext(), "No internet connection,Please try again later",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });

                }
            }
        });
    }

    protected void selectDate() {
        customer_mDisplayDate.setOnClickListener(new View.OnClickListener() {
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
                customer_mDisplayDate.setText(date);
            }
        };
    }

    protected void findView() {
        customer_first_name = view.findViewById(R.id.customer_first_name_data);
        customer_second_name = view.findViewById(R.id.customer_second_name_data);
        customer_phone_number = view.findViewById(R.id.customer_phone_number_data);
        customer_mDisplayDate = view.findViewById(R.id.customer_age_data);
        customer_email = view.findViewById(R.id.customer_email_data);
        customer_password = view.findViewById(R.id.customer_password1_data);
        customer_re_password = view.findViewById(R.id.customer_password2_data);
        customer_checkBox = view.findViewById(R.id.checkbox);
        customer_read_rules = view.findViewById(R.id.customer_read_rules);
        customer_register = view.findViewById(R.id.customer_register);


    }

}
