package X;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.List;

/* renamed from: X.03O  reason: invalid class name */
public class AnonymousClass03O implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ View$OnKeyListenerC01900Mx A00;

    public AnonymousClass03O(View$OnKeyListenerC01900Mx r1) {
        this.A00 = r1;
    }

    public final void onGlobalLayout() {
        View$OnKeyListenerC01900Mx r2 = this.A00;
        if (r2.A5a()) {
            List<AnonymousClass03R> list = r2.A0L;
            if (list.size() > 0 && !list.get(0).A02.A0E) {
                View view = r2.A05;
                if (view == null || !view.isShown()) {
                    r2.dismiss();
                    return;
                }
                for (AnonymousClass03R r0 : list) {
                    r0.A02.A8P();
                }
            }
        }
    }
}
