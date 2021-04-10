package X;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.List;

/* renamed from: X.1sr  reason: invalid class name and case insensitive filesystem */
public class ViewTreeObserver$OnGlobalLayoutListenerC11801sr implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ View$OnKeyListenerC11671sd A00;

    public ViewTreeObserver$OnGlobalLayoutListenerC11801sr(View$OnKeyListenerC11671sd r1) {
        this.A00 = r1;
    }

    public final void onGlobalLayout() {
        View$OnKeyListenerC11671sd r2 = this.A00;
        if (r2.A6B()) {
            List<AnonymousClass1tW> list = r2.A0M;
            if (list.size() > 0 && !list.get(0).A02.A0E) {
                View view = r2.A08;
                if (view == null || !view.isShown()) {
                    r2.dismiss();
                    return;
                }
                for (AnonymousClass1tW r0 : list) {
                    r0.A02.AAO();
                }
            }
        }
    }
}
