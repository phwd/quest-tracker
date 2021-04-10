package oculus.internal.license.parsing;

import java.util.function.Function;
import oculus.internal.license.parsing.CodableLicense;

/* renamed from: oculus.internal.license.parsing.-$$Lambda$lVnOn0D-tn0tV5phHXi4kq1kyL0  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$lVnOn0Dtn0tV5phHXi4kq1kyL0 implements Function {
    public static final /* synthetic */ $$Lambda$lVnOn0Dtn0tV5phHXi4kq1kyL0 INSTANCE = new $$Lambda$lVnOn0Dtn0tV5phHXi4kq1kyL0();

    private /* synthetic */ $$Lambda$lVnOn0Dtn0tV5phHXi4kq1kyL0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((CodableLicense.CodablePackageFilter.CodableSigner) obj).toSigner();
    }
}
