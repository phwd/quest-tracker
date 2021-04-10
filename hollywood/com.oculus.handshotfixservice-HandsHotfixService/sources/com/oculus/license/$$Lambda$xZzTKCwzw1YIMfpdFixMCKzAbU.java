package com.oculus.license;

import java.util.function.ToIntFunction;

/* renamed from: com.oculus.license.-$$Lambda$xZzTKCwzw1YIMfpdFix-MCKzAbU  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$xZzTKCwzw1YIMfpdFixMCKzAbU implements ToIntFunction {
    public static final /* synthetic */ $$Lambda$xZzTKCwzw1YIMfpdFixMCKzAbU INSTANCE = new $$Lambda$xZzTKCwzw1YIMfpdFixMCKzAbU();

    private /* synthetic */ $$Lambda$xZzTKCwzw1YIMfpdFixMCKzAbU() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        return ((PackageFilter) obj).size();
    }
}
