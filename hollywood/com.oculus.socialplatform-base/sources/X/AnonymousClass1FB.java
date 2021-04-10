package X;

import android.view.ViewParent;

/* renamed from: X.1FB  reason: invalid class name */
public class AnonymousClass1FB implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ForwardingListener$DisallowIntercept";
    public final /* synthetic */ AnonymousClass1F8 A00;

    public AnonymousClass1FB(AnonymousClass1F8 r1) {
        this.A00 = r1;
    }

    public final void run() {
        ViewParent parent = this.A00.A07.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }
}
