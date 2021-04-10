package defpackage;

import java.util.HashSet;

/* renamed from: zC0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5897zC0 implements Runnable {
    public final IC0 F;
    public final HashSet G;

    public RunnableC5897zC0(IC0 ic0, HashSet hashSet) {
        this.F = ic0;
        this.G = hashSet;
    }

    public void run() {
        IC0 ic0 = this.F;
        HashSet hashSet = this.G;
        C3209jS0 js0 = ic0.Q;
        js0.c = hashSet;
        js0.e();
    }
}
