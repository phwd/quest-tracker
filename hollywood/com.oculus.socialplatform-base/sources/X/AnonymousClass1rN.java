package X;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.lang.ref.WeakReference;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1rN  reason: invalid class name */
public class AnonymousClass1rN extends AbstractC11301rk implements AnonymousClass1tQ {
    public AbstractC11461s1 A00;
    public WeakReference<View> A01;
    public final Context A02;
    public final C11581sN A03;
    public final /* synthetic */ C11201rK A04;

    public AnonymousClass1rN(C11201rK r3, Context context, AbstractC11461s1 r5) {
        this.A04 = r3;
        this.A02 = context;
        this.A00 = r5;
        C11581sN r1 = new C11581sN(context);
        r1.A00 = 1;
        this.A03 = r1;
        r1.A0C(this);
    }

    @Override // X.AnonymousClass1tQ
    public final boolean A7I(@NonNull C11581sN r2, @NonNull MenuItem menuItem) {
        AbstractC11461s1 r0 = this.A00;
        if (r0 != null) {
            return r0.A6g(this, menuItem);
        }
        return false;
    }

    @Override // X.AnonymousClass1tQ
    public final void A7J(@NonNull C11581sN r2) {
        if (this.A00 != null) {
            A02();
            C11591sO r0 = ((AnonymousClass1rP) this.A04.A09).A00;
            if (r0 != null) {
                r0.A05();
            }
        }
    }

    @Override // X.AbstractC11301rk
    public final void A05(boolean z) {
        super.A05(z);
        this.A04.A09.setTitleOptional(z);
    }
}
