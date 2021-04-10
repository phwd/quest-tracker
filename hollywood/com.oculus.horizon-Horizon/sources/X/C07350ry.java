package X;

import androidx.annotation.NonNull;

/* renamed from: X.0ry  reason: invalid class name and case insensitive filesystem */
public final class C07350ry implements AnonymousClass0AR {
    public C07280rn A00 = null;

    @Override // X.AnonymousClass0AR
    @NonNull
    public final AnonymousClass0AP getLifecycle() {
        C07280rn r0 = this.A00;
        if (r0 != null) {
            return r0;
        }
        C07280rn r02 = new C07280rn(this);
        this.A00 = r02;
        return r02;
    }
}
