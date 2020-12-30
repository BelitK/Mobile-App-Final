package com.example.myapplication.ui.image;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.Image;
import com.example.myapplication.service.ImageService;
import com.example.myapplication.ui.home.MainActivity;

public class SaveActivity extends AppCompatActivity {

    ImageView imageView;
    TextView imagePathViewer;
    EditText imageComment;
    EditText imageName;
    ImageService imageService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        Button btnChoose = findViewById(R.id.bottomChooseButton);
        Button btnSave = findViewById(R.id.bottomSaveButton);
        imageView = findViewById(R.id.midPicture);
        imagePathViewer = findViewById(R.id.midPicturePath);
        imageName = findViewById(R.id.midPictureName);
        imageComment = findViewById(R.id.midPictureComment);

        imageService = new ImageService(SaveActivity.this);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Image image = new Image();
                image.setPath(imagePathViewer.getText().toString());
                image.setComment(imageComment.getText().toString());
                image.setName(imageName.getText().toString());
                imageService.saveImage(image);
                Intent intent = new Intent(SaveActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100){
            imagePathViewer.setText(getRealPathFromURI(data.getData()));
            imageView.setImageURI(data.getData());

        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String thePath = "no-path-found";
        String[] filePathColumn = {MediaStore.Images.Media.DISPLAY_NAME};
        Cursor cursor = getContentResolver().query(contentURI, filePathColumn, null, null, null);
        if(cursor.moveToFirst()){
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            thePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return  thePath;
    }

}
