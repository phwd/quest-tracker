package X;

import java.lang.annotation.Annotation;
import javax.inject.Provider;

/* renamed from: X.0Qs  reason: invalid class name */
public final class AnonymousClass0Qs<T> {
    public byte A00;
    public AnonymousClass14P<T> A01;
    public Class A02;
    public final Class<? extends Annotation> A03;
    public Provider<? extends T> A04;
    public Provider<? extends T> A05;

    public final String toString() {
        String simpleName = getClass().getSimpleName();
        String canonicalName = this.A02.getCanonicalName();
        AnonymousClass14P<T> r4 = this.A01;
        Provider<? extends T> provider = this.A05;
        Class<? extends Annotation> cls = this.A03;
        boolean z = true;
        if ((this.A00 & 1) != 1) {
            z = false;
        }
        return String.format("%s{declaringModuleName = %s, key = %s, provider = %s, scope = %s, default = %s}", simpleName, canonicalName, r4, provider, cls, Boolean.valueOf(z));
    }
}
