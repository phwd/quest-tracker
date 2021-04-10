package oculus.internal.license.store;

import java.util.function.Function;

/* renamed from: oculus.internal.license.store.-$$Lambda$LicenseDbSchemaMaintainer$LMtt6La1-t8-WFt5qZM386d5DH8  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$LicenseDbSchemaMaintainer$LMtt6La1t8WFt5qZM386d5DH8 implements Function {
    public static final /* synthetic */ $$Lambda$LicenseDbSchemaMaintainer$LMtt6La1t8WFt5qZM386d5DH8 INSTANCE = new $$Lambda$LicenseDbSchemaMaintainer$LMtt6La1t8WFt5qZM386d5DH8();

    private /* synthetic */ $$Lambda$LicenseDbSchemaMaintainer$LMtt6La1t8WFt5qZM386d5DH8() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return LicenseStoreSchema.formatRevokedLicenseTableRecord(((Long) obj).longValue());
    }
}
