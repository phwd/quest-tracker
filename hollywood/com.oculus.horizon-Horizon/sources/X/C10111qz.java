package X;

import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1qz  reason: invalid class name and case insensitive filesystem */
public final class C10111qz implements AnonymousClass1qE, AnonymousClass1t0 {
    @Nullable
    public final AnonymousClass1qE A00;
    public final AnonymousClass1s1 A01;
    @Nullable
    public final AnonymousClass1t0 A02;
    @Nullable
    public final AnonymousClass1tF A03;

    @Override // X.AnonymousClass1qE
    public final void A6T(AnonymousClass1qU r3, String str, String str2) {
        AnonymousClass1s1 r1 = this.A01;
        if (r1 != null) {
            r1.A6U(r3.A09, str, str2);
        }
        AnonymousClass1qE r0 = this.A00;
        if (r0 != null) {
            r0.A6T(r3, str, str2);
        }
    }

    @Override // X.AnonymousClass1qE
    public final void A6V(ProducerContext producerContext, String str, @Nullable Map<String, String> map) {
        AnonymousClass1s1 r1 = this.A01;
        if (r1 != null) {
            r1.A6W(producerContext.A09, str, map);
        }
        AnonymousClass1qE r0 = this.A00;
        if (r0 != null) {
            r0.A6V(producerContext, str, map);
        }
    }

    @Override // X.AnonymousClass1qE
    public final void A6X(ProducerContext producerContext, String str, Throwable th, @Nullable Map<String, String> map) {
        AnonymousClass1s1 r1 = this.A01;
        if (r1 != null) {
            r1.A6Y(producerContext.A09, str, th, map);
        }
        AnonymousClass1qE r0 = this.A00;
        if (r0 != null) {
            r0.A6X(producerContext, str, th, map);
        }
    }

    @Override // X.AnonymousClass1qE
    public final void A6Z(ProducerContext producerContext, String str, @Nullable Map<String, String> map) {
        AnonymousClass1s1 r1 = this.A01;
        if (r1 != null) {
            r1.A6a(producerContext.A09, str, map);
        }
        AnonymousClass1qE r0 = this.A00;
        if (r0 != null) {
            r0.A6Z(producerContext, str, map);
        }
    }

    @Override // X.AnonymousClass1qE
    public final void A6b(AnonymousClass1qU r3, String str) {
        AnonymousClass1s1 r1 = this.A01;
        if (r1 != null) {
            r1.A6c(r3.A09, str);
        }
        AnonymousClass1qE r0 = this.A00;
        if (r0 != null) {
            r0.A6b(r3, str);
        }
    }

    @Override // X.AnonymousClass1t0
    public final void A6j(AnonymousClass1qU r3) {
        AnonymousClass1tF r1 = this.A03;
        if (r1 != null) {
            r1.A6k(r3.A09);
        }
        AnonymousClass1t0 r0 = this.A02;
        if (r0 != null) {
            r0.A6j(r3);
        }
    }

    @Override // X.AnonymousClass1t0
    public final void A6l(AnonymousClass1qU r5, Throwable th) {
        AnonymousClass1tF r3 = this.A03;
        if (r3 != null) {
            r3.A6m(r5.A07, r5.A09, th, r5.A09());
        }
        AnonymousClass1t0 r0 = this.A02;
        if (r0 != null) {
            r0.A6l(r5, th);
        }
    }

    @Override // X.AnonymousClass1t0
    public final void A6n(AnonymousClass1qU r6) {
        AnonymousClass1tF r4 = this.A03;
        if (r4 != null) {
            r4.A6o(r6.A07, r6.A08, r6.A09, r6.A09());
        }
        AnonymousClass1t0 r0 = this.A02;
        if (r0 != null) {
            r0.A6n(r6);
        }
    }

    @Override // X.AnonymousClass1t0
    public final void A6p(AnonymousClass1qU r5) {
        AnonymousClass1tF r3 = this.A03;
        if (r3 != null) {
            r3.A6q(r5.A07, r5.A09, r5.A09());
        }
        AnonymousClass1t0 r0 = this.A02;
        if (r0 != null) {
            r0.A6p(r5);
        }
    }

    @Override // X.AnonymousClass1qE
    public final void A77(AnonymousClass1qU r3, String str, boolean z) {
        AnonymousClass1s1 r1 = this.A01;
        if (r1 != null) {
            r1.A78(r3.A09, str, z);
        }
        AnonymousClass1qE r0 = this.A00;
        if (r0 != null) {
            r0.A77(r3, str, z);
        }
    }

    @Override // X.AnonymousClass1qE
    public final boolean A8K(AnonymousClass1qU r3, String str) {
        boolean z;
        AnonymousClass1s1 r1 = this.A01;
        if (r1 != null) {
            z = r1.A8L(r3.A09);
            if (z) {
                return z;
            }
        } else {
            z = false;
        }
        AnonymousClass1qE r0 = this.A00;
        if (r0 != null) {
            return r0.A8K(r3, str);
        }
        return z;
    }

    public C10111qz(@Nullable AnonymousClass1tF r1, @Nullable AnonymousClass1t0 r2) {
        this.A01 = r1;
        this.A00 = r2;
        this.A03 = r1;
        this.A02 = r2;
    }
}
