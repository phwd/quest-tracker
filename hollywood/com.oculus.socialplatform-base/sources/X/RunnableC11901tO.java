package X;

import android.view.View;

/* renamed from: X.1tO  reason: invalid class name and case insensitive filesystem */
public class RunnableC11901tO implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ListPopupWindow$2";
    public final /* synthetic */ C11691sf A00;

    public RunnableC11901tO(C11691sf r1) {
        this.A00 = r1;
    }

    public final void run() {
        C11691sf r1 = this.A00;
        View view = r1.A07;
        if (view != null && view.getWindowToken() != null) {
            r1.AAO();
        }
    }
}
