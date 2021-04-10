package X;

/* renamed from: X.097  reason: invalid class name */
public class AnonymousClass097 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.DialogFragment$1";
    public final /* synthetic */ AnonymousClass0AH A00;

    public AnonymousClass097(AnonymousClass0AH r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass0AH r0 = this.A00;
        r0.mOnDismissListener.onDismiss(r0.mDialog);
    }
}
