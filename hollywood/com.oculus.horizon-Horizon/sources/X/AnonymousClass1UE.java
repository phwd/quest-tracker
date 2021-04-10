package X;

import javax.annotation.Nullable;

/* renamed from: X.1UE  reason: invalid class name */
public final class AnonymousClass1UE implements AnonymousClass0b1 {
    public AnonymousClass0b1 A00;

    public AnonymousClass1UE() {
        C04580iA r0 = new C04580iA();
        synchronized (this) {
            this.A00 = r0;
        }
    }

    @Override // X.AnonymousClass0b1
    public final void report(String str) {
        AnonymousClass0b1 r0;
        synchronized (this) {
            r0 = this.A00;
        }
        r0.report(str);
    }

    @Override // X.AnonymousClass0b1
    public final void report(String str, String str2, @Nullable Throwable th) {
        AnonymousClass0b1 r0;
        synchronized (this) {
            r0 = this.A00;
        }
        r0.report(str, str2, th);
    }
}
