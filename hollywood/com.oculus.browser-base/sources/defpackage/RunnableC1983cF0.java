package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* renamed from: cF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1983cF0 implements Runnable {
    public final /* synthetic */ AbstractC2324eF0 F;

    public RunnableC1983cF0(AbstractC2324eF0 ef0) {
        this.F = ef0;
    }

    public void run() {
        RecyclerView recyclerView = this.F.A0;
        recyclerView.focusableViewAvailable(recyclerView);
    }
}
