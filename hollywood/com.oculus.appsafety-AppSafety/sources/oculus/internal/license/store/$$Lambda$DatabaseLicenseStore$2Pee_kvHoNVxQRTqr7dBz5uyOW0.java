package oculus.internal.license.store;

import java.util.function.Function;

/* renamed from: oculus.internal.license.store.-$$Lambda$DatabaseLicenseStore$2Pee_kvHoNVxQRTqr7dBz5uyOW0  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$DatabaseLicenseStore$2Pee_kvHoNVxQRTqr7dBz5uyOW0 implements Function {
    public static final /* synthetic */ $$Lambda$DatabaseLicenseStore$2Pee_kvHoNVxQRTqr7dBz5uyOW0 INSTANCE = new $$Lambda$DatabaseLicenseStore$2Pee_kvHoNVxQRTqr7dBz5uyOW0();

    private /* synthetic */ $$Lambda$DatabaseLicenseStore$2Pee_kvHoNVxQRTqr7dBz5uyOW0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return LicenseStoreSchema.formatRevokedLicenseTableRecord(((Long) obj).longValue());
    }
}
