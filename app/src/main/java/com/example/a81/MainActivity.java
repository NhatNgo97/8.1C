package com.example.a81;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a81.db.Database;
import com.example.a81.model.Account;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button loginButton, toSignUpButton;
    EditText textboxUsername, textboxPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.loginButton);
        toSignUpButton = findViewById(R.id.toSignUpButton);
        textboxUsername = findViewById(R.id.usernameTextEdit);
        textboxPassword = findViewById(R.id.passwordTextEdit);

        Database db = new Database(this);

        Intent Login = new Intent(this, YoutubeUrlActivity.class);
        Intent SignUp = new Intent(this, SignUpActivity.class);

        ArrayList<Account> Logins = db.FetchAllLogins();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isVerified = false;
                for (int i = 0; i < Logins.size(); i++){
                    if (Logins.get(i).getUsername().equals(textboxUsername.getText().toString()) &&
                            Logins.get(i).getPassword().equals(textboxPassword.getText().toString())){
                        isVerified = true;
                    }
                }
                if (isVerified){
                    startActivity(Login);
                }else{
                    Toast.makeText(MainActivity.this, "The username or password is incorrect.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        toSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(SignUp);
            }
        });
    }
}