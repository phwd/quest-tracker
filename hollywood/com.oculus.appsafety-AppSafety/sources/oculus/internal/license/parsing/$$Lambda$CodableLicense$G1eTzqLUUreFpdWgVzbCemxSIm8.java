package oculus.internal.license.parsing;

import com.oculus.license.Signer;
import java.util.function.Function;

/* renamed from: oculus.internal.license.parsing.-$$Lambda$CodableLicense$G1eTzqLUUreFpdWgVzbCemxSIm8  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$CodableLicense$G1eTzqLUUreFpdWgVzbCemxSIm8 implements Function {
    public static final /* synthetic */ $$Lambda$CodableLicense$G1eTzqLUUreFpdWgVzbCemxSIm8 INSTANCE = new $$Lambda$CodableLicense$G1eTzqLUUreFpdWgVzbCemxSIm8();

    private /* synthetic */ $$Lambda$CodableLicense$G1eTzqLUUreFpdWgVzbCemxSIm8() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return CodableLicense.toCodableSigner((Signer) obj);
    }
}
