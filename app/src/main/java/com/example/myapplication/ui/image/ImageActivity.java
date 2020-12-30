package com.example.myapplication.ui.image;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
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

import java.io.File;

public class ImageActivity extends AppCompatActivity {

    private final static String REAL_PATH = "/sdcard/DCIM/Camera/";

    ImageView imageView;
    TextView imagePathViewer;
    EditText imageComment;
    EditText imageName;
    Image image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Button btnChoose = findViewById(R.id.bottomChooseButton);
        Button btnUpdate = findViewById(R.id.bottomSaveButton);
        Button btnDelete = findViewById(R.id.deleteImage);
        imageView = findViewById(R.id.midPicture);
        imagePathViewer = findViewById(R.id.midPicturePath);
        imageComment = findViewById(R.id.midPictureComment);
        imageName = findViewById(R.id.midPictureName);

        Long id = Long.valueOf(getIntent().getExtras().get("id").toString());

        final ImageService imageService = new ImageService(this);

        image = imageService.getImage(id);

        imageView.setImageBitmap(BitmapFactory.decodeFile(new File(REAL_PATH+image.getPath()).getAbsolutePath()));
        imagePathViewer.setText(image.getPath());
        imageComment.setText(image.getComment());
        imageName.setText(image.getName());

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setPath(imagePathViewer.getText().toString());
                image.setComment(imageComment.getText().toString());
                image.setName(imageName.getText().toString());
                imageService.updateImage(image);
                Intent intent = new Intent(ImageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageService.deleteImage((long) image.getId());
                Intent intent = new Intent(ImageActivity.this, MainActivity.class);
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
