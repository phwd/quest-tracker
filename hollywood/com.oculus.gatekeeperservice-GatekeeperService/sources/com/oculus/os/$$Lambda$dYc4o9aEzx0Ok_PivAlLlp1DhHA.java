package com.oculus.os;

import com.oculus.os.PackageMetadata;
import java.util.function.ToIntFunction;

/* renamed from: com.oculus.os.-$$Lambda$dYc4o9aEzx0Ok_PivAlLlp1DhHA  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$dYc4o9aEzx0Ok_PivAlLlp1DhHA implements ToIntFunction {
    public static final /* synthetic */ $$Lambda$dYc4o9aEzx0Ok_PivAlLlp1DhHA INSTANCE = new $$Lambda$dYc4o9aEzx0Ok_PivAlLlp1DhHA();

    private /* synthetic */ $$Lambda$dYc4o9aEzx0Ok_PivAlLlp1DhHA() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        return ((PackageMetadata.Signature) obj).size();
    }
}
