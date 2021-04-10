package X;

import javax.annotation.Nullable;

/* renamed from: X.0av  reason: invalid class name and case insensitive filesystem */
public final class C02850av implements AbstractC04970iB {
    public AbstractC04970iB A00;

    @Override // X.AbstractC04970iB
    public final void A7P(String str) {
        AbstractC04970iB r0;
        synchronized (this) {
            r0 = this.A00;
        }
        r0.A7P(str);
    }

    @Override // X.AbstractC04970iB
    public final void A7Q(String str, String str2, @Nullable Throwable th) {
        AbstractC04970iB r0;
        synchronized (this) {
            r0 = this.A00;
        }
        r0.A7Q(str, str2, th);
    }

    public C02850av() {
        C02810ar r0 = new C02810ar();
        synchronized (this) {
            this.A00 = r0;
        }
    }
}
