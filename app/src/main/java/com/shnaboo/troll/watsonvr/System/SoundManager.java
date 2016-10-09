package com.shnaboo.troll.watsonvr.System;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.shnaboo.troll.watsonvr.R;

import java.util.HashMap;

/**
 * Created by SAEED on 10/9/2016.
 */
public class SoundManager {
    private SoundPool mShortPlayer= null;
    private HashMap mSounds = new HashMap();

    public SoundManager(Context pContext)
    {
        // setup Soundpool
        this.mShortPlayer = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);


       //  mSounds.put(R.raw. , this.mShortPlayer.load(pContext, R.raw.<sound_1_name>, 1));
//        mSounds.put(R.raw.<sound_2_name>, this.mShortPlayer.load(pContext, R.raw.<sound_2_name>, 1));
    }

    public void playShortResource(int piResource) {
        int iSoundId = (Integer) mSounds.get(piResource);
        this.mShortPlayer.play(iSoundId, 0.99f, 0.99f, 0, 0, 1);
    }

    // Cleanup
    public void release() {
        // Cleanup
        this.mShortPlayer.release();
        this.mShortPlayer = null;
    }
}