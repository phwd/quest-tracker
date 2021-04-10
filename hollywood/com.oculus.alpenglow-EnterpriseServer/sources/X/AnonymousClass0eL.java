package X;

import androidx.annotation.NonNull;

/* renamed from: X.0eL  reason: invalid class name */
public class AnonymousClass0eL implements AbstractC000503a {
    public final /* synthetic */ AnonymousClass0Mm A00;

    public AnonymousClass0eL(AnonymousClass0Mm r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC000503a
    public final void A5x(@NonNull C04280eZ r3, boolean z) {
        if (r3 instanceof SubMenuC01890Mu) {
            r3.A04().A0F(false);
        }
        AbstractC000503a r0 = ((AbstractC04310ee) this.A00).A04;
        if (r0 != null) {
            r0.A5x(r3, z);
        }
    }

    @Override // X.AbstractC000503a
    public final boolean A6L(@NonNull C04280eZ r4) {
        AnonymousClass0Mm r2 = this.A00;
        if (r4 == ((AbstractC04310ee) r2).A03) {
            return false;
        }
        ((SubMenuC01890Mu) r4).getItem().getItemId();
        AbstractC000503a r0 = ((AbstractC04310ee) r2).A04;
        if (r0 != null) {
            return r0.A6L(r4);
        }
        return false;
    }
}
