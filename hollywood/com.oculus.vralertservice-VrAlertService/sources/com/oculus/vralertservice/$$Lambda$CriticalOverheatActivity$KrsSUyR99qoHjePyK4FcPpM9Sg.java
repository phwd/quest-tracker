package com.oculus.vralertservice;

import android.media.MediaPlayer;

/* renamed from: com.oculus.vralertservice.-$$Lambda$CriticalOverheatActivity$KrsSUyR99-qoHjePyK4FcPpM9Sg  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$CriticalOverheatActivity$KrsSUyR99qoHjePyK4FcPpM9Sg implements MediaPlayer.OnCompletionListener {
    public static final /* synthetic */ $$Lambda$CriticalOverheatActivity$KrsSUyR99qoHjePyK4FcPpM9Sg INSTANCE = new $$Lambda$CriticalOverheatActivity$KrsSUyR99qoHjePyK4FcPpM9Sg();

    private /* synthetic */ $$Lambda$CriticalOverheatActivity$KrsSUyR99qoHjePyK4FcPpM9Sg() {
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.release();
    }
}
