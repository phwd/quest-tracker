package X;

/* renamed from: X.1tY  reason: invalid class name */
public class AnonymousClass1tY implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ListPopupWindow$ListSelectorHider";
    public final /* synthetic */ C11691sf A00;

    public AnonymousClass1tY(C11691sf r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass1F5 r1 = this.A00.A0B;
        if (r1 != null) {
            r1.A08 = true;
            r1.requestLayout();
        }
    }
}
