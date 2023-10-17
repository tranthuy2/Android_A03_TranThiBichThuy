package com.cobedangiu.chatleo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.cobedangiu.chatleo.R;
import com.cobedangiu.chatleo.databinding.ActivitySecurityAuthenBinding;
import com.cobedangiu.chatleo.databinding.ActivitySignInBinding;
import com.cobedangiu.chatleo.databinding.ActivityUsersBinding;

public class SecurityAuthen extends AppCompatActivity {

    private ActivitySecurityAuthenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecurityAuthenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheckOTP()){
                    if (!binding.inputOTP.getText().toString().equals("123456")){
                        showToast("otp code is incorrect!");
                    }else {
                        showToast("Confirm successful!");
                        startActivity(new Intent(SecurityAuthen.this, ResetActivity.class));
                    }
                }else {

                }
            }
        });
        binding.sendOTP.setOnClickListener(view -> {
            if (isCheckMail()){
                Toast.makeText(getApplicationContext(), "Code sent!", Toast.LENGTH_SHORT).show();
                binding.sendOTP.setClickable(false);
                binding.inputOTP.setEnabled(true);
                CountDownTimer Timer = new CountDownTimer(60000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        binding.countDown.setText(" "+ millisUntilFinished / 1000 + "s");
                    }
                    public void onFinish() {
                        binding.countDown.setText(" 0!");
                        binding.sendOTP.setClickable(true);
                    }
                }.start();
            }else{
                return;
            }
        });
        binding.textGoBack.setOnClickListener(view -> finish());
    }

    public Boolean isCheckOTP(){
        if(binding.inputOTP.getText().toString().trim().isEmpty()) {
            showToast("Please Enter OTP code!");
            return false;
        }else{
            return true;
        }
    }

    public Boolean isCheckMail(){
        if (binding.inputEmail.getText().toString().trim().isEmpty()){
            showToast("Please enter your email!");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.getText().toString()).matches()) {
            showToast("Please enter a suitable your email!");
            return false;
        }else{
            return true;
        }
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}