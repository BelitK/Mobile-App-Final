package com.example.myapplication.ui.home;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    LayoutInflater inflater;
    List<Image> listImages ;
    Context context;

    public CustomAdapter(Context applicationContext, List<Image> listImages) {
        inflater = (LayoutInflater.from(applicationContext));
        this.context = applicationContext;
        this.listImages = listImages;
    }

    @Override
    public int getCount() {
        return listImages.size();
    }

    @Override
    public Object getItem(int i) {
        return listImages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listImages.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_item, null);
        ImageView image = view.findViewById(R.id.image);
        TextView name = view.findViewById(R.id.imageName);
        TextView comment = view.findViewById(R.id.imageComment);


        File imageFile = new  File("/sdcard/DCIM/Camera/"+listImages.get(i).getPath());
        Bitmap bmp = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

        image.setImageBitmap(bmp);
        name.setText(listImages.get(i).getName());
        comment.setText(listImages.get(i).getComment());


        return view;
    }
}
