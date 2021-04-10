package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* renamed from: Wu1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Wu1 implements Runnable {
    public final int F;
    public final RecyclerView G;

    public Wu1(int i, RecyclerView recyclerView) {
        this.F = i;
        this.G = recyclerView;
    }

    public void run() {
        this.G.w0(this.F);
    }
}
