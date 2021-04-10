package defpackage;

import android.view.KeyEvent;

/* renamed from: th1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4954th1 implements Runnable {
    public final /* synthetic */ KeyEvent F;
    public final /* synthetic */ C5464wh1 G;

    public RunnableC4954th1(C5464wh1 wh1, KeyEvent keyEvent) {
        this.G = wh1;
        this.F = keyEvent;
    }

    public void run() {
        this.G.sendKeyEvent(this.F);
    }
}
