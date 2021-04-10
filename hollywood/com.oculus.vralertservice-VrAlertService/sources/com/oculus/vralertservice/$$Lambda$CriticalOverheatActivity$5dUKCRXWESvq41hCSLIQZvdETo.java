package com.oculus.vralertservice;

import android.media.MediaPlayer;

/* renamed from: com.oculus.vralertservice.-$$Lambda$CriticalOverheatActivity$5d-UKCRXWESvq41hCSLIQZvdETo  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$CriticalOverheatActivity$5dUKCRXWESvq41hCSLIQZvdETo implements MediaPlayer.OnErrorListener {
    public static final /* synthetic */ $$Lambda$CriticalOverheatActivity$5dUKCRXWESvq41hCSLIQZvdETo INSTANCE = new $$Lambda$CriticalOverheatActivity$5dUKCRXWESvq41hCSLIQZvdETo();

    private /* synthetic */ $$Lambda$CriticalOverheatActivity$5dUKCRXWESvq41hCSLIQZvdETo() {
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        return CriticalOverheatActivity.lambda$playOverheatRing$2(mediaPlayer, i, i2);
    }
}
