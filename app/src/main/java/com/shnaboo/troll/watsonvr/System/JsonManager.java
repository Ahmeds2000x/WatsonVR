package com.shnaboo.troll.watsonvr.System;

import android.app.Activity;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by troll on 08.10.16.
 */

public class JsonManager {
//    public static ArrayList<String> classList  ;
//    public static ArrayList<String> ScoreList   ;
//    public static ArrayList<String> TypeList  ;
    public static ArrayList<String> classScoreList  ;

    public static void findObject(Context context, String jsonString) {




        try {

            JSONObject oJSONObject = new JSONObject(jsonString);
            if (oJSONObject != null) {

                if(oJSONObject.has("images")){
                    JSONArray images = oJSONObject.getJSONArray("images");
                    JSONObject arr1 = images.getJSONObject(0);
                    JSONArray classifiers = arr1.getJSONArray("classifiers");
                    JSONObject classifiers2= classifiers.getJSONObject(0);
                    JSONArray classes = classifiers2.getJSONArray("classes");

                    JSONObject classes2= classes.getJSONObject(0);
                    //JSONArray _class ;


                        //
//                    classList = new ArrayList() ;
//                    ScoreList = new ArrayList() ;
//                    TypeList = new ArrayList() ;
                    classScoreList = new ArrayList();
                    JSONObject arrayElement ;
                    for (int i=0 ; i <classes.length(); i++){
                        arrayElement = classes.getJSONObject(i) ;
//                        classList.add(arrayElement.getString("class"));
//                        ScoreList.add(arrayElement.getString("score"));
//                        TypeList.add(arrayElement.getString("type_hierarchy"));
                        classScoreList.add(arrayElement.getString("class") + "("+ arrayElement.getString("score") +"%)");
                    }

                }

                if (classScoreList.size()==0){
                    classScoreList.add("No Results");
                }

                 SingleChoiceDialogManager.openDialog((Activity)context, classScoreList,"");

            }
        }catch (Exception ex) {
            classScoreList = new ArrayList();
            if (classScoreList.size()==0){
                classScoreList.add("No Results");
            }
            String s =  ex.getMessage() ;
            ex.printStackTrace();
        }



    }
}
