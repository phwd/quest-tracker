package X;

/* renamed from: X.1tF  reason: invalid class name */
public class AnonymousClass1tF implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ListPopupWindow$ResizePopupRunnable";
    public final /* synthetic */ C11691sf A00;

    public AnonymousClass1tF(C11691sf r1) {
        this.A00 = r1;
    }

    public final void run() {
        C11691sf r2 = this.A00;
        AnonymousClass1F5 r0 = r2.A0B;
        if (r0 != null && r0.isAttachedToWindow() && r2.A0B.getCount() > r2.A0B.getChildCount() && r2.A0B.getChildCount() <= r2.A00) {
            r2.A0A.setInputMethodMode(2);
            r2.AAO();
        }
    }
}
