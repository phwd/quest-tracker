package defpackage;

import android.os.ResultReceiver;
import android.view.View;

/* renamed from: R10  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class R10 implements Runnable {
    public final S10 F;
    public final View G;
    public final int H;
    public final ResultReceiver I;

    public R10(S10 s10, View view, int i, ResultReceiver resultReceiver) {
        this.F = s10;
        this.G = view;
        this.H = i;
        this.I = resultReceiver;
    }

    public void run() {
        S10 s10 = this.F;
        View view = this.G;
        int i = this.H;
        ResultReceiver resultReceiver = this.I;
        if (s10.c(view)) {
            s10.d(view, i, resultReceiver);
        }
    }
}
