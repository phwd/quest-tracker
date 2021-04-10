package X;

import android.media.MediaPlayer;

public final class WJ implements MediaPlayer.OnCompletionListener {
    public final /* synthetic */ boolean A00;

    public WJ(boolean z) {
        this.A00 = z;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (!this.A00) {
            HandlerC0422Wz.A02();
        }
    }
}
