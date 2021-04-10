package com.oculus.android.exoplayer2.source.ads;

import android.view.ViewGroup;
import com.oculus.android.exoplayer2.ExoPlayer;
import java.io.IOException;

public interface AdsLoader {

    public interface EventListener {
        void onAdClicked();

        void onAdLoadError(IOException iOException);

        void onAdPlaybackState(AdPlaybackState adPlaybackState);

        void onAdTapped();

        void onInternalAdLoadError(RuntimeException runtimeException);
    }

    void attachPlayer(ExoPlayer exoPlayer, EventListener eventListener, ViewGroup viewGroup);

    void detachPlayer();

    void handlePrepareError(int i, int i2, IOException iOException);

    void release();

    void setSupportedContentTypes(int... iArr);
}
