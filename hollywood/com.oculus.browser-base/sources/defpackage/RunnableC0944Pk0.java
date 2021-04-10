package defpackage;

/* renamed from: Pk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0944Pk0 implements Runnable {
    public final View$OnClickListenerC1249Uk0 F;

    public RunnableC0944Pk0(View$OnClickListenerC1249Uk0 uk0) {
        this.F = uk0;
    }

    public void run() {
        View$OnClickListenerC1249Uk0 uk0 = this.F;
        uk0.R = false;
        Runnable runnable = uk0.P;
        if (runnable != null) {
            runnable.run();
            uk0.P = null;
        }
    }
}
