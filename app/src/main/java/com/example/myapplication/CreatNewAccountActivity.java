package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Customer.CustomerFragment;
import com.example.myapplication.Mechanical.MechanicalFragment;

public class CreatNewAccountActivity extends AppCompatActivity {

    Fragment fragment=null;
    Button customer;
    Button mechanical;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        findView();
    }


    public void changeFragmnet(final View view) {
        int id = view.getId();
        if (id == R.id.customer) {
            fragment = new CustomerFragment();
        }
        else {
            fragment = new MechanicalFragment();

        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit();
    }

    public void findView (){
        customer=findViewById(R.id.customer);
        mechanical=findViewById(R.id.mechanical);
        password=findViewById(R.id.customer_password1_data);
    }


}
