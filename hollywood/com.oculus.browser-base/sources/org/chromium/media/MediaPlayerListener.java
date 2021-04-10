package org.chromium.media;

import J.N;
import android.media.MediaPlayer;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaPlayerListener implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnVideoSizeChangedListener, MediaPlayer.OnErrorListener {

    /* renamed from: a  reason: collision with root package name */
    public long f10983a;

    public MediaPlayerListener(long j) {
        this.f10983a = j;
    }

    public static MediaPlayerListener create(long j, MediaPlayerBridge mediaPlayerBridge) {
        MediaPlayerListener mediaPlayerListener = new MediaPlayerListener(j);
        if (mediaPlayerBridge != null) {
            mediaPlayerBridge.b().setOnCompletionListener(mediaPlayerListener);
            mediaPlayerBridge.b().setOnErrorListener(mediaPlayerListener);
            mediaPlayerBridge.b().setOnPreparedListener(mediaPlayerListener);
            mediaPlayerBridge.b().setOnVideoSizeChangedListener(mediaPlayerListener);
        }
        return mediaPlayerListener;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        N.MX$D6jYE(this.f10983a, this);
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        int i3 = 3;
        if (i != 1) {
            if (i == 100) {
                i3 = 4;
            } else if (i == 200) {
                i3 = 2;
            }
        } else if (i2 == -1007) {
            i3 = 1;
        } else if (i2 != -110) {
            i3 = 0;
        }
        N.Myj2LnkZ(this.f10983a, this, i3);
        return true;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        N.MQTompEl(this.f10983a, this);
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        N.Mfq$ZJpW(this.f10983a, this, i, i2);
    }
}
