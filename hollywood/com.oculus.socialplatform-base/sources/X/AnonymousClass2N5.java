package X;

import android.view.View;
import android.view.ViewStub;

/* renamed from: X.2N5  reason: invalid class name */
public class AnonymousClass2N5 implements ViewStub.OnInflateListener {
    public final /* synthetic */ AnonymousClass2NC A00;

    public AnonymousClass2N5(AnonymousClass2NC r1) {
        this.A00 = r1;
    }

    public final void onInflate(ViewStub viewStub, View view) {
        AnonymousClass2NC r3 = this.A00;
        r3.A00 = view;
        r3.A04 = AnonymousClass1uU.A00.getDataBinder((AbstractC003408r) null, view, viewStub.getLayoutResource());
        r3.A02 = null;
        ViewStub.OnInflateListener onInflateListener = r3.A01;
        if (onInflateListener != null) {
            onInflateListener.onInflate(viewStub, view);
            r3.A01 = null;
        }
        r3.A03.invalidateAll();
        r3.A03.executeBindings();
    }
}
