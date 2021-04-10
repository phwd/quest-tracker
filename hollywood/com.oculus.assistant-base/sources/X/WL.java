package X;

import android.media.MediaPlayer;

public final class WL implements MediaPlayer.OnCompletionListener {
    public final /* synthetic */ WO A00;

    public WL(WO wo) {
        this.A00 = wo;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        WO wo = this.A00;
        WS ws = wo.A05;
        ws.A00 = true;
        ws.A01.cancel();
        AbstractC0400Wb wb = wo.A01;
        if (wb != null && (wb instanceof C1265wL)) {
            C0112Aq.A00().A01(new C1290wk());
        }
    }
}
