package X;

import android.os.Handler;
import android.widget.AbsListView;

/* renamed from: X.04u  reason: invalid class name and case insensitive filesystem */
public class C004004u implements AbsListView.OnScrollListener {
    public final /* synthetic */ C04080dy A00;

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 1) {
            C04080dy r2 = this.A00;
            if (r2.A0A.getInputMethodMode() != 2 && r2.A0A.getContentView() != null) {
                Handler handler = r2.A0K;
                RunnableC004204w r0 = r2.A0L;
                handler.removeCallbacks(r0);
                r0.run();
            }
        }
    }

    public C004004u(C04080dy r1) {
        this.A00 = r1;
    }
}
