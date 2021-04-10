package oculus.internal.license.store;

import java.util.function.Function;

/* renamed from: oculus.internal.license.store.-$$Lambda$DatabaseLicenseStore$kEhv814OnYPgYbWuSNIK8ZRN1ww  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$DatabaseLicenseStore$kEhv814OnYPgYbWuSNIK8ZRN1ww implements Function {
    public static final /* synthetic */ $$Lambda$DatabaseLicenseStore$kEhv814OnYPgYbWuSNIK8ZRN1ww INSTANCE = new $$Lambda$DatabaseLicenseStore$kEhv814OnYPgYbWuSNIK8ZRN1ww();

    private /* synthetic */ $$Lambda$DatabaseLicenseStore$kEhv814OnYPgYbWuSNIK8ZRN1ww() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return DatabaseLicenseStore.md5((byte[]) obj);
    }
}
