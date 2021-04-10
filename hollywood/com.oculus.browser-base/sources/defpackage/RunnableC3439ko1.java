package defpackage;

import android.net.Uri;

/* renamed from: ko1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3439ko1 implements Runnable {
    public final C3781mo1 F;
    public final Uri G;

    public RunnableC3439ko1(C3781mo1 mo1, Uri uri) {
        this.F = mo1;
        this.G = uri;
    }

    public void run() {
        C3781mo1 mo1 = this.F;
        mo1.b.remove(this.G);
    }
}
