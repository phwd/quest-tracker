package oculus.internal.license.parsing;

import com.oculus.license.Signer;
import java.util.function.Function;

/* renamed from: oculus.internal.license.parsing.-$$Lambda$CodableLicense$A7dWSEwShJrnk2wueXnJFxEpzsc  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$CodableLicense$A7dWSEwShJrnk2wueXnJFxEpzsc implements Function {
    public static final /* synthetic */ $$Lambda$CodableLicense$A7dWSEwShJrnk2wueXnJFxEpzsc INSTANCE = new $$Lambda$CodableLicense$A7dWSEwShJrnk2wueXnJFxEpzsc();

    private /* synthetic */ $$Lambda$CodableLicense$A7dWSEwShJrnk2wueXnJFxEpzsc() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return CodableLicense.toCodableDigest((Signer.Digest) obj);
    }
}
