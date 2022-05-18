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

public class SignUpActivity extends AppCompatActivity {

    EditText signUpFullnameTextEdit, signUpUsernameTextEdit, signUpPasswordTextEdit, signUpConfirmPasswordTextEdit;
    Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpFullnameTextEdit = findViewById(R.id.signUpFullnameTextEdit);
        signUpUsernameTextEdit = findViewById(R.id.signUpUsernameTextEdit);
        signUpPasswordTextEdit = findViewById(R.id.signUpPasswordTextEdit);
        signUpConfirmPasswordTextEdit = findViewById(R.id.signUpConfirmPasswordTextEdit);
        createAccountButton = findViewById(R.id.createAccountButton);

        Database db = new Database(this);

        Intent returnMain = new Intent(this, MainActivity.class);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!signUpFullnameTextEdit.getText().toString().equals("") &&
                        !signUpUsernameTextEdit.getText().toString().equals("") &&
                        !signUpPasswordTextEdit.getText().toString().equals("") &&
                        !signUpConfirmPasswordTextEdit.getText().toString().equals("") &&
                        signUpPasswordTextEdit.getText().toString().equals(signUpConfirmPasswordTextEdit.getText().toString())){
                    Account account = new Account(signUpUsernameTextEdit.getText().toString(),
                            signUpConfirmPasswordTextEdit.getText().toString(),signUpFullnameTextEdit.getText().toString());
                    db.InsertLogin(account);
                    startActivity(returnMain);

                }else if (!signUpPasswordTextEdit.getText().toString().equals(signUpConfirmPasswordTextEdit.getText().toString())){
                    Toast.makeText(SignUpActivity.this, "The passwords need to match", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SignUpActivity.this, "Fill in all the fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}