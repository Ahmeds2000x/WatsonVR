package com.shnaboo.troll.watsonvr.System;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

/**
 * Created by troll on 07/02/15.
 */
public class AudioPlayer {

    private MediaPlayer mPlayer;

    public void stop(){
        if(mPlayer != null){
            mPlayer.release();
            mPlayer = null;
        }
    }
    public void play(Context c, Uri uri){
        stop();

        mPlayer = MediaPlayer.create(c, uri);

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mp){
                stop();
            }
        });

        mPlayer.start();
    }

}