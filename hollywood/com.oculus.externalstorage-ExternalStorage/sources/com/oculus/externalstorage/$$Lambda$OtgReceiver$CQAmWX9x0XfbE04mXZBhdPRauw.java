package com.oculus.externalstorage;

import android.media.MediaPlayer;

/* renamed from: com.oculus.externalstorage.-$$Lambda$OtgReceiver$CQAmWX9x0XfbE04mXZBhdPRau-w  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$OtgReceiver$CQAmWX9x0XfbE04mXZBhdPRauw implements MediaPlayer.OnCompletionListener {
    public static final /* synthetic */ $$Lambda$OtgReceiver$CQAmWX9x0XfbE04mXZBhdPRauw INSTANCE = new $$Lambda$OtgReceiver$CQAmWX9x0XfbE04mXZBhdPRauw();

    private /* synthetic */ $$Lambda$OtgReceiver$CQAmWX9x0XfbE04mXZBhdPRauw() {
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.release();
    }
}
