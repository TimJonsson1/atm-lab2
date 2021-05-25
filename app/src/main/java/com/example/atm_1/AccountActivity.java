package com.example.atm_1;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.math.RoundingMode;

public class AccountActivity extends AppCompatActivity {

    TextView textWelcome, textSumAmount, textInputAmount, textToEuro;

    Button btnDeposit, btnWithdraw, btnToEuro;

    static String username;
    double sum = 500.5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        textWelcome = findViewById(R.id.text_welcome_message);
        textSumAmount = findViewById(R.id.text_sum_amount);
        textInputAmount = findViewById(R.id.text_input_amount);
        textToEuro = findViewById(R.id.text_to_euro);

        btnDeposit = findViewById(R.id.btn_deposit);
        btnWithdraw = findViewById(R.id.btn_withdraw);
        btnToEuro = findViewById(R.id.btn_to_euro);

        textWelcome.setText("Hello " + username + " nice to see you!");
        textSumAmount.setText("You have: " + sum + "kr");

        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textInputAmount.getText().toString().equals("")) {
                    Toast.makeText(AccountActivity.this, "Need a amount", Toast.LENGTH_SHORT).show();
                } else {
                    try {

                        double tempSum = Double.parseDouble(textInputAmount.getText().toString());
                        sum += tempSum;

                        textInputAmount.setText("");
                        textSumAmount.setText("You have: " + sum + "kr");

                        closeKeyboard(btnDeposit.getWindowToken());

                    } catch (NumberFormatException ne) {
                        ne.getMessage();
                        Toast.makeText(AccountActivity.this, "You can only use numbers", Toast.LENGTH_SHORT).show();
                        textInputAmount.setText("");

                        closeKeyboard(btnDeposit.getWindowToken());
                    }

                }

            }
        });

        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (textInputAmount.getText().toString().equals("")) {
                    Toast.makeText(AccountActivity.this, "Need a amount", Toast.LENGTH_SHORT).show();
                } else {
                    try {

                        double tempSum = Double.parseDouble(textInputAmount.getText().toString());

                        if (tempSum > sum) {
                            Toast.makeText(AccountActivity.this, "You don't have this amount to withdraw", Toast.LENGTH_SHORT).show();
                        }else {
                            sum -= tempSum;
                        }
                        textInputAmount.setText("");
                        textSumAmount.setText("You have: " + sum + "kr");

                        closeKeyboard(btnDeposit.getWindowToken());

                    } catch (NumberFormatException ne) {
                        ne.getMessage();
                        Toast.makeText(AccountActivity.this, "You can only use numbers", Toast.LENGTH_SHORT).show();
                        textInputAmount.setText("");

                        closeKeyboard(btnDeposit.getWindowToken());
                    }

                }

            }

        });

        btnToEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (textInputAmount.getText().toString().equals("")) {
                    Toast.makeText(AccountActivity.this, "Need a amount", Toast.LENGTH_SHORT).show();
                } else {
                    try {

                        double tempSum = Double.parseDouble(textInputAmount.getText().toString());

                        textToEuro.setText(String.format("%.2f Kr,   is  %.3f Euro " ,tempSum, valueToEuro(tempSum)));

                        textInputAmount.setText("");
                        textSumAmount.setText("You have: " + sum + "kr");

                        closeKeyboard(btnDeposit.getWindowToken());

                    } catch (NumberFormatException ne) {
                        ne.getMessage();
                        Toast.makeText(AccountActivity.this, "You can only use numbers", Toast.LENGTH_SHORT).show();
                        textInputAmount.setText("");

                        closeKeyboard(btnDeposit.getWindowToken());
                    }

                }

            }
        });


    }

    private void closeKeyboard(IBinder WindowToken) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(WindowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }

    private double valueToEuro(double value){
        // code to turn SEK to EURO
        // I am going on Euro is a increase of Kurs 1013.42 of the Swedish Kr
        // (basing this of the most recent value compare at the time of writing this)
        double tempSum = Double.parseDouble(textInputAmount.getText().toString());

        double kurs = 100D/1013.42D;
        value = tempSum * kurs;

        return value;
    }

}
