package com.oculus.appsafety;

import java.util.function.Function;
import oculus.internal.gson.HexString;

/* renamed from: com.oculus.appsafety.-$$Lambda$3Q9D5ethWmHHaKd02rM4coTfHUw  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$3Q9D5ethWmHHaKd02rM4coTfHUw implements Function {
    public static final /* synthetic */ $$Lambda$3Q9D5ethWmHHaKd02rM4coTfHUw INSTANCE = new $$Lambda$3Q9D5ethWmHHaKd02rM4coTfHUw();

    private /* synthetic */ $$Lambda$3Q9D5ethWmHHaKd02rM4coTfHUw() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return HexString.encode((byte[]) obj);
    }
}
