package oculus.internal.license.parsing;

import java.util.function.Function;
import oculus.internal.license.parsing.CodableLicense;

/* renamed from: oculus.internal.license.parsing.-$$Lambda$cVgejGGna1CYv36P6dwKRhT1n10  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$cVgejGGna1CYv36P6dwKRhT1n10 implements Function {
    public static final /* synthetic */ $$Lambda$cVgejGGna1CYv36P6dwKRhT1n10 INSTANCE = new $$Lambda$cVgejGGna1CYv36P6dwKRhT1n10();

    private /* synthetic */ $$Lambda$cVgejGGna1CYv36P6dwKRhT1n10() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((CodableLicense.CodableRule.CodableFilterConfig) obj).toFilterConfig();
    }
}
