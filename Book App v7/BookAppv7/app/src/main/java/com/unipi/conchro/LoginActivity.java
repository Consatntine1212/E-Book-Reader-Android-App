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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseUser current_user;
    private Button login_button;
    private EditText UserEmail, UserPassword;
    private TextView NeedNewAcousticLink;
    private FirebaseAuth authenticator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        authenticator = FirebaseAuth.getInstance();
        current_user = authenticator.getCurrentUser();
        InitializeFields();


        NeedNewAcousticLink.setOnClickListener(v -> SendUserToRegisterActivity());

        login_button.setOnClickListener(v -> AllowUserToLogin());
    }

    private void AllowUserToLogin() {

        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email field is Empty", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password field is Empty", Toast.LENGTH_SHORT).show();
        }
        else{
            authenticator.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            SendUserToMainActivity();
                        }
                        else
                        {
                            String message = task.getException().toString();
                            Toast.makeText(LoginActivity.this, "ERROR"+message, Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
    private void InitializeFields() {
        login_button =  (Button) findViewById(R.id.login_button);
        UserEmail =  (EditText) findViewById(R.id.login_email);
        UserPassword =  (EditText) findViewById(R.id.login_password);
        NeedNewAcousticLink =  (TextView) findViewById(R.id.make_acount_link);
    }

    @Override
    protected void onStart(){
        super.onStart();
        if (current_user != null){
            Toast.makeText(LoginActivity.this, current_user.getEmail().toString(), Toast.LENGTH_SHORT).show();
            //SendUserToMainActivity();
        }
    }
    private void SendUserToMainActivity() {
        Intent LoginIntent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(LoginIntent);
    }
    private void SendUserToRegisterActivity() {
        Intent RegisterIntent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(RegisterIntent);
    }
}