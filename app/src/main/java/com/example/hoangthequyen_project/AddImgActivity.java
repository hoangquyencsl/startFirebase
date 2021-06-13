package com.example.hoangthequyen_project;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hoangthequyen_project.Fragment.HomeFrg;
import com.example.hoangthequyen_project.model.Images;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddImgActivity extends AppCompatActivity {

    private EditText titleimg, topicimg;
    private Button btnShareImage, btnCancel;
    private ImageView imageView;
    //private ProgressBar progressBar;

    private Button btnSelectImg;

    private static final int PICK_IMAGE_REQUEST = 123;
    private   Uri FilePathUri;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_img);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();

        storageReference = FirebaseStorage.getInstance().getReference("Images");
        databaseReference = FirebaseDatabase.getInstance().getReference("Images");

        btnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent galleryintent = new Intent();
                galleryintent.setAction(Intent.ACTION_GET_CONTENT);
                galleryintent.setType("image/*");
                startActivityForResult(galleryintent,PICK_IMAGE_REQUEST);
            }
        });

        btnShareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topic = topicimg.getText().toString();
                String title = titleimg.getText().toString();
                if (topic != null && title != null && FilePathUri != null ) {
                    UploadImage(FilePathUri);
                }else {
                    Toast.makeText(AddImgActivity.this,"Please Select Your Image, Enter Topic and Title",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddImgActivity.this, HomeFrg.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            FilePathUri = data.getData();
            imageView.setImageURI(FilePathUri);

        }
    }
    public void UploadImage(Uri uri){
//        System.currentTimeMillis() + "." +
        StorageReference fileRef = storageReference.child(getFileExtenion(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String topic = topicimg.getText().toString();
                        String title = titleimg.getText().toString();
                        Images images = new Images(topic ,title ,uri.toString());
                        String imageId = databaseReference.push().getKey();
                        databaseReference.child(imageId).setValue(images);

                        Toast.makeText(AddImgActivity.this,"Uploaded Successfully !!" , Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }
//        })
//                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                progressBar.setVisibility(View.VISIBLE);
//            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(AddImgActivity.this,"Uploading Failed" , Toast.LENGTH_LONG).show();
            }
        });

    }
    private String getFileExtenion(Uri mUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mtm= MimeTypeMap.getSingleton();
        return mtm.getExtensionFromMimeType(contentResolver.getType(mUri));
    }


    private void initView() {
        titleimg = findViewById(R.id.addtitle);
        topicimg = findViewById(R.id.addtopic);
        btnSelectImg=findViewById(R.id.btnselectImg);
        imageView=findViewById(R.id.imageAdd);
        btnShareImage = findViewById(R.id.btnShare);
        btnCancel = findViewById(R.id.btnCancel);
       // progressBar = findViewById(R.id.progressBar);

    }
}
