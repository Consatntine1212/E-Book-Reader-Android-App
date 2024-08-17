package com.unipi.conchro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private Button RegisterButton;
    private EditText UserEmail, UserPassword;
    private TextView AlreadyHaveAnAccountLink;
    private FirebaseAuth authenticator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        authenticator = FirebaseAuth.getInstance();
        InitializeFields();
        AlreadyHaveAnAccountLink.setOnClickListener(v -> SendUserToLoginActivity());
        RegisterButton.setOnClickListener(v -> CreateNewAccount());
    }

    private void CreateNewAccount() {
        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email field is empty", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password field is Empty", Toast.LENGTH_SHORT).show();
        }
        else{
            //progressBar.set
            authenticator.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Registration comlpete", Toast.LENGTH_SHORT).show();
                    SendUserToLoginActivity();
                }
                else {
                    String mesage = task.getException().toString();
                    Toast.makeText(RegisterActivity.this, "ERROR"+mesage, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void InitializeFields() {
        RegisterButton =  (Button) findViewById(R.id.register_button);
        UserEmail =  (EditText) findViewById(R.id.register_email);
        UserPassword =  (EditText) findViewById(R.id.register_password);
        AlreadyHaveAnAccountLink =  (TextView) findViewById(R.id.register_login_link);
    }
    private void SendUserToLoginActivity() {
        Intent RegisterIntent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(RegisterIntent);
    }
}