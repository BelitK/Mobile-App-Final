package com.example.myapplication.service;

import android.content.Context;

import com.example.myapplication.model.Image;
import com.example.myapplication.repository.ImageRepository;

import java.util.ArrayList;
import java.util.List;

public class ImageService {

    private ImageRepository imageRepository;

    public ImageService(Context context){
        imageRepository = new ImageRepository(context);
    }

    public List<Image> getImages(){
        return imageRepository.findAll();
    }

    public Image getImage(Long id){
        return imageRepository.findById(id);
    }

    public boolean saveImage(Image image){
        imageRepository.save(image);
        return true;
    }

    public boolean updateImage(Image image){
        imageRepository.updateCountry(image);
        return true;
    }

    public boolean deleteImage(Long id){
        imageRepository.deleteCountry(id);
        return false;
    }


}
