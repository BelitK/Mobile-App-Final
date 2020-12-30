package com.example.myapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.Image;
import com.example.myapplication.service.ImageService;
import com.example.myapplication.ui.image.ImageActivity;
import com.example.myapplication.ui.image.SaveActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.mainListView);
        ImageService imageService = new ImageService(MainActivity.this);
        Button btnAdd = findViewById(R.id.addImage);
        final List<Image> imageList = imageService.getImages();
        for (Image m:imageList) {
            System.out.println("yüklendi mi ? :"+m.toString());
        }
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this,imageList);

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                intent.putExtra("id",imageList.get(i).getId());
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SaveActivity.class);
                startActivity(intent);

            }
        });


    }
}
