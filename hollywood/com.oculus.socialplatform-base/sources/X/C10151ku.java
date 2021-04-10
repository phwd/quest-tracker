package X;

import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1ku  reason: invalid class name and case insensitive filesystem */
public final class C10151ku implements AnonymousClass1l6, AnonymousClass1m8 {
    @Nullable
    public final AnonymousClass1l6 A00;
    public final AnonymousClass1lE A01;
    @Nullable
    public final AnonymousClass1m8 A02;
    @Nullable
    public final AnonymousClass1mI A03;

    @Override // X.AnonymousClass1l6
    public final void A7W(C10161kv r3, String str, String str2) {
        AnonymousClass1lE r1 = this.A01;
        if (r1 != null) {
            r1.A7X(r3.A09, str, str2);
        }
        AnonymousClass1l6 r0 = this.A00;
        if (r0 != null) {
            r0.A7W(r3, str, str2);
        }
    }

    @Override // X.AnonymousClass1l6
    public final void A7Y(ProducerContext producerContext, String str, @Nullable Map<String, String> map) {
        AnonymousClass1lE r1 = this.A01;
        if (r1 != null) {
            r1.A7Z(producerContext.A09, str, map);
        }
        AnonymousClass1l6 r0 = this.A00;
        if (r0 != null) {
            r0.A7Y(producerContext, str, map);
        }
    }

    @Override // X.AnonymousClass1l6
    public final void A7a(ProducerContext producerContext, String str, Throwable th, @Nullable Map<String, String> map) {
        AnonymousClass1lE r1 = this.A01;
        if (r1 != null) {
            r1.A7b(producerContext.A09, str, th, map);
        }
        AnonymousClass1l6 r0 = this.A00;
        if (r0 != null) {
            r0.A7a(producerContext, str, th, map);
        }
    }

    @Override // X.AnonymousClass1l6
    public final void A7c(ProducerContext producerContext, String str, @Nullable Map<String, String> map) {
        AnonymousClass1lE r1 = this.A01;
        if (r1 != null) {
            r1.A7d(producerContext.A09, str, map);
        }
        AnonymousClass1l6 r0 = this.A00;
        if (r0 != null) {
            r0.A7c(producerContext, str, map);
        }
    }

    @Override // X.AnonymousClass1l6
    public final void A7e(C10161kv r3, String str) {
        AnonymousClass1lE r1 = this.A01;
        if (r1 != null) {
            r1.A7f(r3.A09, str);
        }
        AnonymousClass1l6 r0 = this.A00;
        if (r0 != null) {
            r0.A7e(r3, str);
        }
    }

    @Override // X.AnonymousClass1m8
    public final void A7n(C10161kv r3) {
        AnonymousClass1mI r1 = this.A03;
        if (r1 != null) {
            r1.A7o(r3.A09);
        }
        AnonymousClass1m8 r0 = this.A02;
        if (r0 != null) {
            r0.A7n(r3);
        }
    }

    @Override // X.AnonymousClass1m8
    public final void A7q(C10161kv r5, Throwable th) {
        AnonymousClass1mI r3 = this.A03;
        if (r3 != null) {
            r3.A7r(r5.A07, r5.A09, th, r5.A09());
        }
        AnonymousClass1m8 r0 = this.A02;
        if (r0 != null) {
            r0.A7q(r5, th);
        }
    }

    @Override // X.AnonymousClass1m8
    public final void A7s(C10161kv r6) {
        AnonymousClass1mI r4 = this.A03;
        if (r4 != null) {
            r4.A7t(r6.A07, r6.A08, r6.A09, r6.A09());
        }
        AnonymousClass1m8 r0 = this.A02;
        if (r0 != null) {
            r0.A7s(r6);
        }
    }

    @Override // X.AnonymousClass1m8
    public final void A7v(C10161kv r5) {
        AnonymousClass1mI r3 = this.A03;
        if (r3 != null) {
            r3.A7w(r5.A07, r5.A09, r5.A09());
        }
        AnonymousClass1m8 r0 = this.A02;
        if (r0 != null) {
            r0.A7v(r5);
        }
    }

    @Override // X.AnonymousClass1l6
    public final void A8F(C10161kv r3, String str, boolean z) {
        AnonymousClass1lE r1 = this.A01;
        if (r1 != null) {
            r1.A8G(r3.A09, str, z);
        }
        AnonymousClass1l6 r0 = this.A00;
        if (r0 != null) {
            r0.A8F(r3, str, z);
        }
    }

    @Override // X.AnonymousClass1l6
    public final boolean A9I(C10161kv r3, String str) {
        boolean z;
        AnonymousClass1lE r1 = this.A01;
        if (r1 != null) {
            z = r1.A9J(r3.A09);
            if (z) {
                return z;
            }
        } else {
            z = false;
        }
        AnonymousClass1l6 r0 = this.A00;
        if (r0 != null) {
            return r0.A9I(r3, str);
        }
        return z;
    }

    public C10151ku(@Nullable AnonymousClass1mI r1, @Nullable AnonymousClass1m8 r2) {
        this.A01 = r1;
        this.A00 = r2;
        this.A03 = r1;
        this.A02 = r2;
    }
}
