package X;

import X.AnonymousClass1mY;
import javax.annotation.Nullable;

/* renamed from: X.1li  reason: invalid class name and case insensitive filesystem */
public final class C10261li<DH extends AnonymousClass1mY> {
    public AnonymousClass1m0 A00 = null;
    @Nullable
    public DH A01;
    public boolean A02 = false;
    public boolean A03 = false;
    public boolean A04 = true;
    public final C00920Me A05 = new C00920Me();

    private void A00() {
        if (!this.A02) {
            this.A05.A00(EnumC00910Md.ON_ATTACH_CONTROLLER);
            this.A02 = true;
            AnonymousClass1m0 r1 = this.A00;
            if (r1 != null && r1.A45() != null) {
                r1.A6n();
            }
        }
    }

    private void A01() {
        if (this.A02) {
            this.A05.A00(EnumC00910Md.ON_DETACH_CONTROLLER);
            this.A02 = false;
            if (A03(this)) {
                this.A00.A70();
            }
        }
    }

    public static void A02(C10261li r1) {
        if (!r1.A03 || !r1.A04) {
            r1.A01();
        } else {
            r1.A00();
        }
    }

    public static final boolean A03(C10261li r3) {
        AnonymousClass1m0 r0 = r3.A00;
        if (r0 == null || r0.A45() != r3.A01) {
            return false;
        }
        return true;
    }

    public final void A04(@Nullable AnonymousClass1m0 r4) {
        boolean z = this.A02;
        if (z) {
            A01();
        }
        if (A03(this)) {
            this.A05.A00(EnumC00910Md.ON_CLEAR_OLD_CONTROLLER);
            this.A00.A9t(null);
        }
        this.A00 = r4;
        if (r4 != null) {
            this.A05.A00(EnumC00910Md.ON_SET_CONTROLLER);
            this.A00.A9t(this.A01);
        } else {
            this.A05.A00(EnumC00910Md.ON_CLEAR_CONTROLLER);
        }
        if (z) {
            A00();
        }
    }

    public final String toString() {
        C00720Ig A002 = C00730Ih.A00(this);
        C00720Ig.A00(A002, "controllerAttached", String.valueOf(this.A02));
        C00720Ig.A00(A002, "holderAttached", String.valueOf(this.A03));
        C00720Ig.A00(A002, "drawableVisible", String.valueOf(this.A04));
        C00720Ig.A00(A002, "events", this.A05.toString());
        return A002.toString();
    }
}
