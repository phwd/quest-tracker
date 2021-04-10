package X;

import java.lang.annotation.Annotation;
import javax.inject.Provider;

/* renamed from: X.0Pq  reason: invalid class name */
public final class AnonymousClass0Pq<T> {
    public byte A00;
    public C09160zY<T> A01;
    public Class A02;
    public final Class<? extends Annotation> A03;
    public Provider<? extends T> A04;
    public Provider<? extends T> A05;

    public final String toString() {
        Object[] objArr = new Object[6];
        objArr[0] = getClass().getSimpleName();
        objArr[1] = this.A02.getCanonicalName();
        objArr[2] = this.A01;
        objArr[3] = this.A05;
        objArr[4] = this.A03;
        boolean z = true;
        if ((this.A00 & 1) != 1) {
            z = false;
        }
        objArr[5] = Boolean.valueOf(z);
        return String.format("%s{declaringModuleName = %s, key = %s, provider = %s, scope = %s, default = %s}", objArr);
    }
}
