package X;

import androidx.annotation.NonNull;

/* renamed from: X.1t1  reason: invalid class name */
public class AnonymousClass1t1 implements AbstractC11941tc {
    public final /* synthetic */ C11591sO A00;

    public AnonymousClass1t1(C11591sO r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC11941tc
    public final void A6r(@NonNull C11581sN r3, boolean z) {
        if (r3 instanceof SubMenuC11621sV) {
            r3.A04().A0F(false);
        }
        AbstractC11941tc r0 = ((AnonymousClass1sT) this.A00).A04;
        if (r0 != null) {
            r0.A6r(r3, z);
        }
    }

    @Override // X.AbstractC11941tc
    public final boolean A7T(@NonNull C11581sN r4) {
        C11591sO r2 = this.A00;
        if (r4 == ((AnonymousClass1sT) r2).A03) {
            return false;
        }
        ((SubMenuC11621sV) r4).getItem().getItemId();
        AbstractC11941tc r0 = ((AnonymousClass1sT) r2).A04;
        if (r0 != null) {
            return r0.A7T(r4);
        }
        return false;
    }
}
