package com.shnaboo.troll.watsonvr.System;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.shnaboo.troll.watsonvr.System.AudioPlayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class WatsonT2SManager extends AsyncTask<String, Void, Uri> {
    AudioPlayer mPlayer = new AudioPlayer();
    Context context;
    @Override
    protected Uri doInBackground(String... texts){
        context = GlobalManager.getAppContext();
        TextToSpeech service = new TextToSpeech();
        service.setUsernameAndPassword("Enter YOUR username here", "Enter YOUR password here");
        InputStream stream;
        InputStream in;
        InputStream inn;
        OutputStream out;

        try {
            String text = "IBM IS THE GREATEST COMPANY IN THE WORLD. IBM IS LIFE";

            stream = service.synthesize(texts[0], Voice.EN_MICHAEL, AudioFormat.OGG_VORBIS).execute();

            //in = WaveUtils.reWriteWaveHeader(stream);


            out = new FileOutputStream(Environment.getExternalStorageDirectory() + File.separator + "test.ogg");


            byte[] buffer = new byte[1024];
            int length;
            while ((length = stream.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.close();
            //in.close();
            stream.close();



        } catch (Exception e) {
            e.printStackTrace();
        }

        Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + File.separator + "test.ogg"));


        return uri;
    }

    @Override
    protected void onPostExecute(Uri uri){
        MediaPlayer mediaPlayer = new MediaPlayer();
        String path = Environment.getExternalStorageDirectory() + File.separator + "test.ogg";
        mediaPlayer = MediaPlayer.create(context, Uri.parse(path));
        //mediaPlayer.setLooping(true);
        mediaPlayer.start();


    }


}
