package oculus.internal.license.parsing;

import com.oculus.license.PackageFilter;
import java.util.function.Function;

/* renamed from: oculus.internal.license.parsing.-$$Lambda$CodableLicense$m3Pdv_8U3lUMkzcE6FZKsUvRAFQ  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$CodableLicense$m3Pdv_8U3lUMkzcE6FZKsUvRAFQ implements Function {
    public static final /* synthetic */ $$Lambda$CodableLicense$m3Pdv_8U3lUMkzcE6FZKsUvRAFQ INSTANCE = new $$Lambda$CodableLicense$m3Pdv_8U3lUMkzcE6FZKsUvRAFQ();

    private /* synthetic */ $$Lambda$CodableLicense$m3Pdv_8U3lUMkzcE6FZKsUvRAFQ() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return CodableLicense.toCodablePackageFilter((PackageFilter) obj);
    }
}
