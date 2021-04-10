package X;

import android.view.View;

/* renamed from: X.04q  reason: invalid class name and case insensitive filesystem */
public class RunnableC003704q implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ListPopupWindow$2";
    public final /* synthetic */ C04080dy A00;

    public RunnableC003704q(C04080dy r1) {
        this.A00 = r1;
    }

    public final void run() {
        C04080dy r1 = this.A00;
        View view = r1.A07;
        if (view != null && view.getWindowToken() != null) {
            r1.A8P();
        }
    }
}
