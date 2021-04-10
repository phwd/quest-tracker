package oculus.internal.license.store;

import com.oculus.os.PackageMetadata;
import java.util.function.Function;

/* renamed from: oculus.internal.license.store.-$$Lambda$DatabaseLicenseStore$BQ4_GqhLq4g1DGpd0AWoc9m45m0  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$DatabaseLicenseStore$BQ4_GqhLq4g1DGpd0AWoc9m45m0 implements Function {
    public static final /* synthetic */ $$Lambda$DatabaseLicenseStore$BQ4_GqhLq4g1DGpd0AWoc9m45m0 INSTANCE = new $$Lambda$DatabaseLicenseStore$BQ4_GqhLq4g1DGpd0AWoc9m45m0();

    private /* synthetic */ $$Lambda$DatabaseLicenseStore$BQ4_GqhLq4g1DGpd0AWoc9m45m0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((PackageMetadata.Signature) obj).certificate;
    }
}
