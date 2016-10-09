package com.shnaboo.troll.watsonvr;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.shnaboo.troll.watsonvr.System.GlobalManager;
import com.shnaboo.troll.watsonvr.System.JsonManager;
import com.shnaboo.troll.watsonvr.System.SingleChoiceDialogManager;
import com.shnaboo.troll.watsonvr.System.TakePhotoManager;
import com.shnaboo.troll.watsonvr.System.WatsonManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {


    private ImageView imageView;
    TakePhotoManager takePhotoManager = new TakePhotoManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        imageView = (ImageView) findViewById(R.id.imageView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.image);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhotoManager.TakePhoto();
            }
        });



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String string;
        File file;
        try {
            file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.png");
            file.createNewFile();

            OutputStream outputStream = new FileOutputStream(file);
            Bitmap bitmap=(takePhotoManager.getPhotoBitmap(requestCode, resultCode, data));



            if (bitmap!=null){

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.flush();
                outputStream.close();

                string = new WatsonManager().execute(file.getPath()).get();
                JsonManager.findObject(this, string);

                //
                imageView.setImageBitmap(bitmap);
                file.delete();
            }


        }catch (Exception e){}



    }




}
