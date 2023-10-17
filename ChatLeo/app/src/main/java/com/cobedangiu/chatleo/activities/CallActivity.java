package com.cobedangiu.chatleo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import com.cobedangiu.chatleo.R;
import com.cobedangiu.chatleo.databinding.ActivityCallBinding;
import com.cobedangiu.chatleo.databinding.ActivityCallOutBinding;
import com.cobedangiu.chatleo.models.User;
import com.cobedangiu.chatleo.utilities.Constants;

public class CallActivity extends AppCompatActivity {
    private User receiverUser;
    private ActivityCallBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadReceiverDetails();
        findViewById(R.id.layoutEnd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.name = receiverUser.name;
                user.image = receiverUser.image;
                user.id = receiverUser.id;
                Intent intent = new Intent(CallActivity.this, CallOutActivity.class);
                intent.putExtra(Constants.KEY_USER, user);
                startActivity(intent);
            }
        });
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