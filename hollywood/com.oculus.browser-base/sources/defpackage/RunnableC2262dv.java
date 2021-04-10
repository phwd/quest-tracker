package defpackage;

import android.view.View;

/* renamed from: dv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2262dv implements Runnable {
    public final View F;

    public RunnableC2262dv(View view) {
        this.F = view;
    }

    public void run() {
        this.F.requestLayout();
    }
}
