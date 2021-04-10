package oculus.internal.license;

import com.oculus.os.PackageMetadata;
import java.util.function.Predicate;

/* renamed from: oculus.internal.license.-$$Lambda$ProvisionalLicenseFactory$mFqonkH_byHXV8RfVvcJX1wvAkE  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$ProvisionalLicenseFactory$mFqonkH_byHXV8RfVvcJX1wvAkE implements Predicate {
    public static final /* synthetic */ $$Lambda$ProvisionalLicenseFactory$mFqonkH_byHXV8RfVvcJX1wvAkE INSTANCE = new $$Lambda$ProvisionalLicenseFactory$mFqonkH_byHXV8RfVvcJX1wvAkE();

    private /* synthetic */ $$Lambda$ProvisionalLicenseFactory$mFqonkH_byHXV8RfVvcJX1wvAkE() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ProvisionalLicenseFactory.lambda$formatPackageFilters$1((PackageMetadata.Signature) obj);
    }
}
