package com.oculus.vrshell.home.module.audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Pair;
import com.facebook.debug.log.BLog;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpeakerPlayer extends RCTBaseJavaModule {
    private static final String MODULE_NAME = "Speaker";
    private static final String TAG = SpeakerPlayer.class.getSimpleName();
    private MediaPlayer mMediaPlayer;

    public SpeakerPlayer(Context context) {
    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public void shutdownModule() {
        stopPlayback();
    }

    public static List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("startPlayback", "s"));
        list.add(new Pair<>("stopPlayback", ""));
        return list;
    }

    public void startPlayback(String filePath) {
        if (this.mMediaPlayer == null) {
            this.mMediaPlayer = new MediaPlayer();
            try {
                this.mMediaPlayer.setDataSource(filePath);
                this.mMediaPlayer.prepare();
                this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    /* class com.oculus.vrshell.home.module.audio.SpeakerPlayer.AnonymousClass1 */

                    public void onCompletion(MediaPlayer mp) {
                        SpeakerPlayer.this.stopPlayback();
                    }
                });
                this.mMediaPlayer.start();
                nativeEmitEvent(this.RVRCtxTag, "onStartPlayback");
            } catch (IOException | IllegalStateException e) {
                BLog.e(TAG, e.getMessage(), e);
                this.mMediaPlayer = null;
            }
        }
    }

    public void stopPlayback() {
        if (this.mMediaPlayer != null) {
            try {
                this.mMediaPlayer.stop();
                this.mMediaPlayer.release();
            } catch (IllegalStateException ise) {
                BLog.e(TAG, ise.getMessage(), ise);
            }
            this.mMediaPlayer = null;
            nativeEmitEvent(this.RVRCtxTag, "onStopPlayback");
        }
    }
}
