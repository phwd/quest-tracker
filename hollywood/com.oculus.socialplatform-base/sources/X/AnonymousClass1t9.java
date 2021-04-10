package X;

import android.os.Handler;
import android.widget.AbsListView;

/* renamed from: X.1t9  reason: invalid class name */
public class AnonymousClass1t9 implements AbsListView.OnScrollListener {
    public final /* synthetic */ C11691sf A00;

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 1) {
            C11691sf r2 = this.A00;
            if (r2.A0A.getInputMethodMode() != 2 && r2.A0A.getContentView() != null) {
                Handler handler = r2.A0K;
                AnonymousClass1tF r0 = r2.A0L;
                handler.removeCallbacks(r0);
                r0.run();
            }
        }
    }

    public AnonymousClass1t9(C11691sf r1) {
        this.A00 = r1;
    }
}
