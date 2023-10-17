package com.cobedangiu.chatleo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.cobedangiu.chatleo.R;
import com.cobedangiu.chatleo.databinding.ActivityResetBinding;
import com.cobedangiu.chatleo.databinding.ActivitySignInBinding;
import com.cobedangiu.chatleo.utilities.PreferenceManager;

public class ResetActivity extends AppCompatActivity {

    private ActivityResetBinding binding;
    private PreferenceManager preferenceManager;
    private FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.textSignIn.setOnClickListener(view -> startActivity(new Intent(ResetActivity.this, SignInActivity.class)));
        binding.buttonReset.setOnClickListener(view -> {
            if (isValidResetDetails()==true){
                showToast("Change password successfully!");
            }
            else {
                showToast("Please enter full information!");
            }
        });
        binding.textGoBack.setOnClickListener(view -> finish());
    }
    private Boolean isValidResetDetails(){
        if (binding.inputPassword.getText().toString().trim().isEmpty()){
            showToast("Please enter password!");
            return false;
        }else  if (binding.inputConfirmPassword.getText().toString().trim().isEmpty()){
            showToast("Please enter confirm password!");
            return false;
        }
        else  if (!binding.inputConfirmPassword.getText().toString().equals(binding.inputPassword.getText().toString())){
            showToast("Passwords do not match!");
            return false;
        }else {
            return true;
        }
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}