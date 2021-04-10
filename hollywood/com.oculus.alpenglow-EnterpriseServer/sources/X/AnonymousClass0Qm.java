package X;

import java.lang.annotation.Annotation;

/* renamed from: X.0Qm  reason: invalid class name */
public final class AnonymousClass0Qm<T> {
    public byte A00;
    public C01440Gz<T> A01;
    public Class A02;
    public final Class<? extends Annotation> A03;
    public AbstractC07240oz<? extends T> A04;
    public AbstractC07240oz<? extends T> A05;

    public final String toString() {
        String simpleName = getClass().getSimpleName();
        String canonicalName = this.A02.getCanonicalName();
        C01440Gz<T> r4 = this.A01;
        AbstractC07240oz<? extends T> r5 = this.A05;
        Class<? extends Annotation> cls = this.A03;
        boolean z = true;
        if ((this.A00 & 1) != 1) {
            z = false;
        }
        return String.format("%s{declaringModuleName = %s, key = %s, provider = %s, scope = %s, default = %s}", simpleName, canonicalName, r4, r5, cls, Boolean.valueOf(z));
    }
}
