package com.example.atm_1;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    EditText textName, textPassword;

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btn_login);

        textName = findViewById(R.id.text_username);
        textPassword = findViewById(R.id.text_password);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (textPassword.getText().toString().equals("")){

                    Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                    AccountActivity.username = textName.getText().toString();
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong Password please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
