package com.ssu.kisyuksa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;
import com.ssu.kisyuksa.databinding.ActivitySleepOutListDetailBinding;

public class SleepOutListDetailActivity extends AppCompatActivity {

    ActivitySleepOutListDetailBinding binding;

    String user_id = "TEST";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySleepOutListDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String text = intent.getStringExtra("CONTENTS");
        String title = intent.getStringExtra("TITLE");
        binding.contents.setText(text);
        binding.title.setText(title);

        binding.fixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SleepOutListDetailActivity.this, SleepOutApplicationActivity.class);
                intent.putExtra("CONTENTS", text);
                intent.putExtra("TITLE", title);
                intent.putExtra("FIX", true);
                startActivity(intent);
            }
        });
        binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore.getInstance().collection("sleep_out_application").
                        document(user_id).collection("sleep_out_application").document(intent.getStringExtra("TITLE")).delete();
                finish();
            }
        });
    }
}