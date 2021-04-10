package X;

import android.media.MediaPlayer;

public final class WK implements MediaPlayer.OnCompletionListener {
    public final /* synthetic */ WO A00;

    public WK(WO wo) {
        this.A00 = wo;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        WO wo = this.A00;
        AbstractC0400Wb wb = wo.A01;
        if (wb != null) {
            if (wb.A07()) {
                wo.A05.A01.start();
            }
            wb.A04();
        }
    }
}
