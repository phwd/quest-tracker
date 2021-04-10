package X;

import androidx.annotation.Nullable;

/* renamed from: X.1uZ  reason: invalid class name */
public class AnonymousClass1uZ implements AbstractC00450Aa, AnonymousClass1uj<AnonymousClass0AY<?>> {
    public AnonymousClass0AS A00;
    public final AnonymousClass1uY<AnonymousClass0AY<?>> A01;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1uj
    public final void A1I(AnonymousClass0AY<?> r2) {
        AnonymousClass0AY<?> r22 = r2;
        AnonymousClass0AS r0 = this.A00;
        if (r0 != null) {
            r22.A02(r0, this);
        }
    }

    @Override // X.AbstractC00450Aa
    public final void A6q(@Nullable Object obj) {
        AnonymousClass1uY<AnonymousClass0AY<?>> r0 = this.A01;
        AnonymousClass1uW r3 = (AnonymousClass1uW) r0.get();
        if (r3 == null) {
            r0.A00();
        } else {
            r3.handleFieldChange(r0.A02, r0.A00, 0);
        }
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1uj
    public final void A9B(AnonymousClass0AY<?> r1) {
        r1.A04(this);
    }

    @Override // X.AnonymousClass1uj
    public final void A9y(AnonymousClass0AS r3) {
        T t = this.A01.A00;
        if (t != null) {
            if (this.A00 != null) {
                t.A04(this);
            }
            if (r3 != null) {
                t.A02(r3, this);
            }
        }
        this.A00 = r3;
    }

    public AnonymousClass1uZ(AnonymousClass1uW r2, int i) {
        this.A01 = new AnonymousClass1uY<>(r2, i, this);
    }
}
