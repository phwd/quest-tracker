package oculus.internal.license.store;

import com.oculus.license.Signer;
import java.util.function.Function;

/* renamed from: oculus.internal.license.store.-$$Lambda$LicenseStoreSchema$Jfe3TF_OaZZb5FMPh0zYChxwBmY  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$LicenseStoreSchema$Jfe3TF_OaZZb5FMPh0zYChxwBmY implements Function {
    public static final /* synthetic */ $$Lambda$LicenseStoreSchema$Jfe3TF_OaZZb5FMPh0zYChxwBmY INSTANCE = new $$Lambda$LicenseStoreSchema$Jfe3TF_OaZZb5FMPh0zYChxwBmY();

    private /* synthetic */ $$Lambda$LicenseStoreSchema$Jfe3TF_OaZZb5FMPh0zYChxwBmY() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((Signer) obj).certificate;
    }
}
