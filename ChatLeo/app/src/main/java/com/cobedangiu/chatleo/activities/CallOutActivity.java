package com.cobedangiu.chatleo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.cobedangiu.chatleo.R;
import com.cobedangiu.chatleo.databinding.ActivityCallBinding;
import com.cobedangiu.chatleo.databinding.ActivityCallInBinding;
import com.cobedangiu.chatleo.databinding.ActivityCallOutBinding;
import com.cobedangiu.chatleo.models.User;
import com.cobedangiu.chatleo.utilities.Constants;
import com.cobedangiu.chatleo.utilities.RatingDialog;

public class CallOutActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private User receiverUser;
    private ActivityCallOutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCallOutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadReceiverDetails();
        new Handler().postDelayed(() -> {
            showDialog();
        },1000);
        findViewById(R.id.viewBackground).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CallOutActivity.this, MainActivity.class));
            }
        });
    }

    private void showDialog() {
        final RatingDialog ratingDialog = new RatingDialog.Builder(this)
                //.session(3)
                .threshold(3)
                .ratingBarColor(R.color.primary)
                .onRatingBarFormSumbit(new RatingDialog.Builder.RatingDialogFormListener() {
                    @Override
                    public void onFormSubmitted(String feedback) {
                        Log.i(TAG,"Feedback:" + feedback);
                    }
                })
                .build();
        ratingDialog.show();
    }

    @Override
    public void onBackPressed() {

    }

    private void loadReceiverDetails() {
        receiverUser = (User) getIntent().getSerializableExtra(Constants.KEY_USER);
        byte[] bytes = Base64.decode(receiverUser.image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        binding.imageAvt.setImageBitmap(bitmap);
        binding.viewBackground.setImageBitmap(bitmap);
        binding.textviewUser.setText(receiverUser.name);
    }
}