package defpackage;

import android.app.Activity;
import android.view.KeyEvent;
import com.oculus.browser.VrShellImpl;

/* renamed from: xw1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5679xw1 implements Runnable {
    public final /* synthetic */ Activity F;
    public final /* synthetic */ int G;
    public final /* synthetic */ int H;

    public RunnableC5679xw1(VrShellImpl vrShellImpl, Activity activity, int i, int i2) {
        this.F = activity;
        this.G = i;
        this.H = i2;
    }

    public void run() {
        this.F.dispatchKeyEvent(new KeyEvent(this.G, this.H));
    }
}
