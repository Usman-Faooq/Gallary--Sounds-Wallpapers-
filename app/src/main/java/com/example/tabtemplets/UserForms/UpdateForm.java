package com.example.tabtemplets.UserForms;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tabtemplets.MainActivity;
import com.example.tabtemplets.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateForm extends AppCompatActivity {

    ImageView backbtn;
    CircleImageView pickimage;
    Uri imageuri;

    public static final int PICK_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_form);
        getSupportActionBar().hide();

        backbtn = findViewById(R.id.backbtn);
        pickimage = findViewById(R.id.profile_image);
        pickimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pick = new Intent();
                pick.setType("image/*");
                pick.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(pick, "Select Image"), PICK_IMAGE);
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageuri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);
                pickimage.setImageBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}