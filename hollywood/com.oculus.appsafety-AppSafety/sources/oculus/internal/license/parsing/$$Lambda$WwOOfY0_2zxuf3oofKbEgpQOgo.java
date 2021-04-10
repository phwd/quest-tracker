package oculus.internal.license.parsing;

import java.util.function.Function;
import oculus.internal.license.parsing.CodableLicense;

/* renamed from: oculus.internal.license.parsing.-$$Lambda$WwOOfY0_-2zxuf3oofKbEgpQOgo  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$WwOOfY0_2zxuf3oofKbEgpQOgo implements Function {
    public static final /* synthetic */ $$Lambda$WwOOfY0_2zxuf3oofKbEgpQOgo INSTANCE = new $$Lambda$WwOOfY0_2zxuf3oofKbEgpQOgo();

    private /* synthetic */ $$Lambda$WwOOfY0_2zxuf3oofKbEgpQOgo() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((CodableLicense.CodablePackageFilter.CodableSigner.CodableDigest) obj).toDigest();
    }
}
