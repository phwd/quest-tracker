package defpackage;

import org.chromium.ui.base.WindowAndroid;

/* renamed from: j6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3150j6 implements Runnable {
    public final WindowAndroid F;
    public final int[] G;
    public final AbstractC3834n6 H;

    public RunnableC3150j6(WindowAndroid windowAndroid, int[] iArr, AbstractC3834n6 n6Var) {
        this.F = windowAndroid;
        this.G = iArr;
        this.H = n6Var;
    }

    public void run() {
        AbstractC4005o6.a(this.F, this.G, this.H);
    }
}
