package com.shnaboo.troll.watsonvr.System;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import com.shnaboo.troll.watsonvr.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by m.alsaadi on 12/11/2015.
 */


public class SingleChoiceDialogManager extends DialogFragment {

    public static final String DATA = "items";
    public static final String Dialog = "Dialog";
    public static final String TITLE = "title";
    private Context context;
    private AlertDialog.Builder dialog;
    private Bundle bundle;
    private List<String> list;
    //



    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        bundle = getArguments();
        //,  R.style.MyDialog
        dialog = new AlertDialog.Builder(getActivity());


        //
        context = getActivity();
        try {

            //
            list = (List<String>) bundle.get(DATA);
            if (list != null && list.size() > 0) {
                CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
                dialog.setSingleChoiceItems(cs, 0, selectItemListener);

            }
            //
            dialog.setTitle(bundle.getString(TITLE));
            dialog.setCancelable(false);
            setCancelable(false);



            //
            dialog.setPositiveButton(getActivity().getString(R.string.action_listening), new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                   // doYesActions();
                    String selectedValue  = list.get(0);
                    String substr = selectedValue.substring(0,selectedValue.indexOf("("));
                    new WatsonT2SManager().execute(substr);


                }
            });
            //

            dialog.setNegativeButton(getActivity().getString(R.string.action_close), new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        //doNoActions();
                        dialog.dismiss();
                 }
                });








        } catch (Exception ex) {

        } finally {
            return dialog.create();
        }


    }


    DialogInterface.OnClickListener selectItemListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            selectItemHandel(dialog, which);
        }
    };


    public static void openDialog(Activity activity,
                                  ArrayList<String> list,
                                  String title) {

        FragmentManager manager = activity.getFragmentManager();
        SingleChoiceDialogManager dialog = new SingleChoiceDialogManager();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(SingleChoiceDialogManager.DATA,list );
        bundle.putString(SingleChoiceDialogManager.TITLE,  title);
        dialog.setArguments(bundle);
        dialog.show(manager, SingleChoiceDialogManager.Dialog);
    }


    ////// Select Item Region /////////////////////////////////

    private void selectItemHandel(DialogInterface dialog, int which) {
        try {
//            switch (ChoiceType) {
//                case SIGN_UP_IDENTIFIER_DETECTION:
//                    select_item_choice_sign_up_identifier_detection(dialog, which);
//                    break;
//                case AUTO_REGISTRATION:
//                    break;
//                case FORGET_USER_NAME_ACCOUNT_IDENTIFIER_TYPE :
//                    select_item_choice_account_identifier_type(dialog, which);
//                    break;
//            }
        } catch (Exception ex) {
        }


    }





//////// init Choice Region ///////////////////////////////////////////











///////// Override Region /////////////////////////////

    @Override
    public void onDestroyView() {
        try {
            super.onDestroyView();
            context = null;
            dialog = null;
            bundle = null;
            list = null;
        } catch (Exception ex) {
        }
    }


}
