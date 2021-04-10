package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* renamed from: rK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4560rK0 implements Runnable {
    public final /* synthetic */ RecyclerView F;

    public RunnableC4560rK0(RecyclerView recyclerView) {
        this.F = recyclerView;
    }

    public void run() {
        RecyclerView recyclerView = this.F;
        if (recyclerView.g0 && !recyclerView.isLayoutRequested()) {
            RecyclerView recyclerView2 = this.F;
            if (!recyclerView2.d0) {
                recyclerView2.requestLayout();
            } else if (recyclerView2.j0) {
                recyclerView2.i0 = true;
            } else {
                recyclerView2.o();
            }
        }
    }
}
