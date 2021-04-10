package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1kH  reason: invalid class name */
public final class AnonymousClass1kH implements AnonymousClass1kO {
    public final Iterable<AnonymousClass1kO> A00;
    public final AtomicBoolean A01 = new AtomicBoolean(false);

    public final void A00(long j) {
        for (AnonymousClass1kO r1 : this.A00) {
            if (r1 instanceof AnonymousClass1kH) {
                ((AnonymousClass1kH) r1).A00(j);
            }
        }
    }

    public final void A01(long j, String str, Exception exc, boolean z, String str2) {
        for (AnonymousClass1kO r2 : this.A00) {
            if (r2 instanceof AnonymousClass1kH) {
                ((AnonymousClass1kH) r2).A01(j, str, exc, z, str2);
            }
        }
    }

    public final void A02(long j, boolean z) {
        for (AnonymousClass1kO r1 : this.A00) {
            if (r1 instanceof AnonymousClass1kH) {
                ((AnonymousClass1kH) r1).A02(j, z);
            }
        }
    }

    public final void A03(String str) {
        for (AnonymousClass1kO r1 : this.A00) {
            if (r1 instanceof AnonymousClass1kH) {
                ((AnonymousClass1kH) r1).A03(str);
            }
        }
    }

    public final void A04(String str, Map<String, String> map) {
        for (AnonymousClass1kO r1 : this.A00) {
            if (r1 instanceof AnonymousClass1kH) {
                ((AnonymousClass1kH) r1).A04(str, map);
            }
        }
    }

    public final void A05(String str, boolean z) {
        for (AnonymousClass1kO r1 : this.A00) {
            if (r1 instanceof AnonymousClass1kH) {
                ((AnonymousClass1kH) r1).A05(str, z);
            }
        }
    }

    @Override // X.AnonymousClass1kO
    public final void A5u(AnonymousClass1aJ r3) {
        if (!this.A01.getAndSet(true)) {
            for (AnonymousClass1kO r0 : this.A00) {
                r0.A5u(r3);
            }
        }
    }

    @Override // X.AnonymousClass1kO
    public final void A6A(AnonymousClass1QC r3) {
        if (!this.A01.getAndSet(true)) {
            for (AnonymousClass1kO r0 : this.A00) {
                r0.A6A(r3);
            }
        }
    }

    @Override // X.AnonymousClass1kO
    public final void A6d(float f) {
        for (AnonymousClass1kO r0 : this.A00) {
            r0.A6d(f);
        }
    }

    @Override // X.AnonymousClass1kO
    public final void onStart() {
        this.A01.set(false);
        for (AnonymousClass1kO r0 : this.A00) {
            r0.onStart();
        }
    }

    public AnonymousClass1kH(Iterable<AnonymousClass1kO> iterable) {
        this.A00 = iterable;
    }
}
