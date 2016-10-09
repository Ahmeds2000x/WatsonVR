package com.shnaboo.troll.watsonvr.System;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;

/**
 * Created by troll on 08.10.16.
 */

public class TakePhotoManager {


    private Context context ;
    public TakePhotoManager (Context context){
        this.context = context ;
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int RESULT_OK = -1 ;

    public  void TakePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(((Activity) context).getPackageManager()) != null) {
            ((Activity) context).startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public Bitmap getPhotoBitmap(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode ==   RESULT_OK) {
            Bundle extras = data.getExtras();
            return (Bitmap) extras.get("data");
        }
        return null;
    }







}
