package X;

/* renamed from: X.04s  reason: invalid class name and case insensitive filesystem */
public class RunnableC003804s implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ListPopupWindow$ListSelectorHider";
    public final /* synthetic */ C04080dy A00;

    public RunnableC003804s(C04080dy r1) {
        this.A00 = r1;
    }

    public final void run() {
        C003004g r1 = this.A00.A0B;
        if (r1 != null) {
            r1.A08 = true;
            r1.requestLayout();
        }
    }
}
