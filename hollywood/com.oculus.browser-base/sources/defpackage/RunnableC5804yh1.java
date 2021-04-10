package defpackage;

import android.view.View;
import java.util.Objects;

/* renamed from: yh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5804yh1 implements Runnable {
    public final /* synthetic */ View F;
    public final /* synthetic */ C0145Ch1 G;

    public RunnableC5804yh1(C0145Ch1 ch1, View view) {
        this.G = ch1;
        this.F = view;
    }

    public void run() {
        this.G.b.onWindowFocusChanged(true);
        this.G.f7830a.c(this.F);
        Objects.requireNonNull(this.G);
        AbstractC0084Bh1.f7749a.post(new RunnableC5634xh1(this));
    }
}
