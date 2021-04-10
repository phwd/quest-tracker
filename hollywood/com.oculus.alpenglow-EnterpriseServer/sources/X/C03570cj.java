package X;

import androidx.annotation.NonNull;

/* renamed from: X.0cj  reason: invalid class name and case insensitive filesystem */
public final class C03570cj implements AbstractC01030Da {
    public C03540cc A00 = null;

    @Override // X.AbstractC01030Da
    @NonNull
    public final AnonymousClass0DY getLifecycle() {
        C03540cc r0 = this.A00;
        if (r0 != null) {
            return r0;
        }
        C03540cc r02 = new C03540cc(this);
        this.A00 = r02;
        return r02;
    }
}
