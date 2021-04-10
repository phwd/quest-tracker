package X;

import android.media.MediaPlayer;

/* renamed from: X.Ym  reason: case insensitive filesystem */
public final class C0442Ym implements MediaPlayer.OnCompletionListener {
    public final /* synthetic */ MediaPlayer.OnCompletionListener A00;

    public C0442Ym(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.A00 = onCompletionListener;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        C0441Yl.A00().A04();
        MediaPlayer.OnCompletionListener onCompletionListener = this.A00;
        if (onCompletionListener != null) {
            onCompletionListener.onCompletion(mediaPlayer);
        }
    }
}
