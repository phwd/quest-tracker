package oculus.internal.license;

import com.oculus.os.PackageMetadata;
import java.util.function.Function;

/* renamed from: oculus.internal.license.-$$Lambda$Evaluator$0347ooCOX3iomDnRkO3e0VWzts8  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$Evaluator$0347ooCOX3iomDnRkO3e0VWzts8 implements Function {
    public static final /* synthetic */ $$Lambda$Evaluator$0347ooCOX3iomDnRkO3e0VWzts8 INSTANCE = new $$Lambda$Evaluator$0347ooCOX3iomDnRkO3e0VWzts8();

    private /* synthetic */ $$Lambda$Evaluator$0347ooCOX3iomDnRkO3e0VWzts8() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Evaluator.lambda$signerMatches$2((PackageMetadata.Signature) obj);
    }
}
