package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Mechanical.MechanecalActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView login;
    TextView create_account;
    EditText email;
    EditText password;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        changeActivity();
        validatingSignIn();


    }

    protected void validatingSignIn(){

    }

    private void changeActivity(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty()) {
                    email.setError(getString(R.string.Required));
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    email.setError(getString(R.string.please_enter_a_valid_email));
                } else if (password.getText().toString().isEmpty()) {
                    password.setError(getString(R.string.Required));
                } else {
                    mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    startActivity(new Intent(LoginActivity.this, MechanecalActivity.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    if (e.getLocalizedMessage() != null) {
                                        if (e.getLocalizedMessage().contains("email address"))
                                            Toast.makeText(LoginActivity.this, "Can`t find this email,Please create new account", Toast.LENGTH_SHORT).show();
                                        else if (e.getLocalizedMessage().contains("network error")) {
                                            Toast.makeText(LoginActivity.this, "No internet connection,Please try again later", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                }

            }
        });

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,CreatNewAccountActivity.class));
            }
        });
    }

    protected void findView (){
        login=findViewById(R.id.login);
        create_account=findViewById(R.id.create_account);
        email=findViewById(R.id.username_data);
        password=findViewById(R.id.password_data);
    }

}
