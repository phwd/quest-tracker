package com.oculus.license;

import java.util.function.ToIntFunction;

/* renamed from: com.oculus.license.-$$Lambda$O9E31hM3rnoLDM9fLrzBXMvSRNk  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$O9E31hM3rnoLDM9fLrzBXMvSRNk implements ToIntFunction {
    public static final /* synthetic */ $$Lambda$O9E31hM3rnoLDM9fLrzBXMvSRNk INSTANCE = new $$Lambda$O9E31hM3rnoLDM9fLrzBXMvSRNk();

    private /* synthetic */ $$Lambda$O9E31hM3rnoLDM9fLrzBXMvSRNk() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        return ((Signer) obj).size();
    }
}
