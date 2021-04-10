package X;

import javax.annotation.Nullable;

/* renamed from: X.1SQ  reason: invalid class name */
public final class AnonymousClass1SQ implements AbstractC02660jW {
    public AbstractC02660jW A00;

    public AnonymousClass1SQ() {
        C03000kj r0 = new C03000kj();
        synchronized (this) {
            this.A00 = r0;
        }
    }

    @Override // X.AbstractC02660jW
    public final void report(String str) {
        AbstractC02660jW r0;
        synchronized (this) {
            r0 = this.A00;
        }
        r0.report(str);
    }

    @Override // X.AbstractC02660jW
    public final void report(String str, String str2, @Nullable Throwable th) {
        AbstractC02660jW r0;
        synchronized (this) {
            r0 = this.A00;
        }
        r0.report(str, str2, th);
    }
}
