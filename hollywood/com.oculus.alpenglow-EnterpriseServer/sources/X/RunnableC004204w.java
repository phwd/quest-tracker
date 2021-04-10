package X;

/* renamed from: X.04w  reason: invalid class name and case insensitive filesystem */
public class RunnableC004204w implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ListPopupWindow$ResizePopupRunnable";
    public final /* synthetic */ C04080dy A00;

    public RunnableC004204w(C04080dy r1) {
        this.A00 = r1;
    }

    public final void run() {
        C04080dy r2 = this.A00;
        C003004g r0 = r2.A0B;
        if (r0 != null && r0.isAttachedToWindow() && r2.A0B.getCount() > r2.A0B.getChildCount() && r2.A0B.getChildCount() <= r2.A00) {
            r2.A0A.setInputMethodMode(2);
            r2.A8P();
        }
    }
}
