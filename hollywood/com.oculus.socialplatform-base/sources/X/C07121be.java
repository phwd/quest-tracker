package X;

import androidx.annotation.NonNull;

/* renamed from: X.1be  reason: invalid class name and case insensitive filesystem */
public class C07121be<Model> implements AbstractC07051bX<Model> {
    public final Model A00;

    @Override // X.AbstractC07051bX
    public final void A26() {
    }

    @Override // X.AbstractC07051bX
    public final void cancel() {
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final Class<Model> A3h() {
        return (Class<Model>) this.A00.getClass();
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final AnonymousClass1fM A3i() {
        return AnonymousClass1fM.LOCAL;
    }

    @Override // X.AbstractC07051bX
    public final void A6H(@NonNull AnonymousClass1cY r2, @NonNull AnonymousClass1Ry<? super Model> r3) {
        r3.A6x(this.A00);
    }

    public C07121be(Model model) {
        this.A00 = model;
    }
}
