package defpackage;

import android.content.Intent;

/* renamed from: pG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RunnableC4208pG1 implements Runnable {
    public final /* synthetic */ Intent F;
    public final /* synthetic */ Intent G;
    public final /* synthetic */ AbstractServiceC2158dG1 H;

    public RunnableC4208pG1(AbstractServiceC2158dG1 dg1, Intent intent, Intent intent2) {
        this.H = dg1;
        this.F = intent;
        this.G = intent2;
    }

    public final void run() {
        this.H.handleIntent(this.F);
        this.H.a(this.G);
    }
}
