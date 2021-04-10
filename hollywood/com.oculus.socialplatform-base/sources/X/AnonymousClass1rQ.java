package X;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1rQ  reason: invalid class name */
public final class AnonymousClass1rQ extends AbstractC11301rk implements AnonymousClass1tQ {
    public Context A00;
    public AbstractC11461s1 A01;
    public C11581sN A02;
    public ActionBarContextView A03;
    public WeakReference<View> A04;
    public boolean A05;

    @Override // X.AnonymousClass1tQ
    public final boolean A7I(@NonNull C11581sN r2, @NonNull MenuItem menuItem) {
        return this.A01.A6g(this, menuItem);
    }

    public AnonymousClass1rQ(Context context, ActionBarContextView actionBarContextView, AbstractC11461s1 r5) {
        this.A00 = context;
        this.A03 = actionBarContextView;
        this.A01 = r5;
        C11581sN r1 = new C11581sN(actionBarContextView.getContext());
        r1.A00 = 1;
        this.A02 = r1;
        r1.A0C(this);
    }

    @Override // X.AbstractC11301rk
    public final void A05(boolean z) {
        super.A05(z);
        this.A03.setTitleOptional(z);
    }

    @Override // X.AnonymousClass1tQ
    public final void A7J(@NonNull C11581sN r2) {
        A02();
        C11591sO r0 = ((AnonymousClass1rP) this.A03).A00;
        if (r0 != null) {
            r0.A05();
        }
    }
}
