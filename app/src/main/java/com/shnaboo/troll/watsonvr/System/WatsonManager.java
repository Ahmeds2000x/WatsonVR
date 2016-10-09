package com.shnaboo.troll.watsonvr.System;

import android.os.AsyncTask;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

import java.io.File;

/**
 * Created by troll on 08.10.16.
 */

public class WatsonManager extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... img) {



        VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
        service.setApiKey("0778118a4bfc1cf24345c428c89725fc2cb15243");
        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
                .images(new File(img[0]))
                .build();
        VisualClassification result = new VisualClassification();
        result = service.classify(options).execute();

        return result.toString();



    }


    protected void onPostExecute(String... string) {

        //
    }
}
