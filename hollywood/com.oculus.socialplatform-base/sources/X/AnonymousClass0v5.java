package X;

import androidx.annotation.NonNull;

/* renamed from: X.0v5  reason: invalid class name */
public final class AnonymousClass0v5 implements AnonymousClass0AS {
    public AnonymousClass0uv A00 = null;

    @Override // X.AnonymousClass0AS
    @NonNull
    public final AnonymousClass0AQ getLifecycle() {
        AnonymousClass0uv r0 = this.A00;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass0uv r02 = new AnonymousClass0uv(this);
        this.A00 = r02;
        return r02;
    }
}
