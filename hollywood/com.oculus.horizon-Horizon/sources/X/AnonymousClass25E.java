package X;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.horizon.notifications.core.NotificationBuilder;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.25E  reason: invalid class name */
public final class AnonymousClass25E implements AnonymousClass1z3 {
    public final AnonymousClass25F A00;
    public final C11111xg A01;
    public final AnonymousClass25B A02;

    @Override // X.AnonymousClass1z3
    public final String A3M() {
        return this.A01.A00();
    }

    public AnonymousClass25E(AnonymousClass25C r4) {
        AnonymousClass25B r2 = r4.A01;
        this.A02 = r2;
        C11111xg r0 = r4.A00;
        this.A01 = r0;
        this.A00 = new AnonymousClass25F(new AnonymousClass25G(r2, r0));
    }

    @Override // X.AnonymousClass1z3
    public final int A30() {
        return NotificationBuilder.CANCELLABLE_NOTIFICATION_FIRST_ID;
    }

    @Override // X.AnonymousClass1z3
    public final int A31() {
        return 200;
    }

    @Override // X.AnonymousClass1z3
    public final int A32() {
        return 2000;
    }

    @Override // X.AnonymousClass1z3
    public final int A33() {
        return 4000;
    }

    @Override // X.AnonymousClass1z3
    public final int A4U() {
        return 2000;
    }

    @Override // X.AnonymousClass1z3
    public final boolean A90() {
        return false;
    }

    @Override // X.AnonymousClass1z3
    public final boolean A91() {
        return false;
    }

    @Override // X.AnonymousClass1z3
    public final boolean A92() {
        return false;
    }
}
