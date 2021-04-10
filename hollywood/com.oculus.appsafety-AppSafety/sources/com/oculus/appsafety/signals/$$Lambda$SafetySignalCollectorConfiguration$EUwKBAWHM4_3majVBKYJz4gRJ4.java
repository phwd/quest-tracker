package com.oculus.appsafety.signals;

import com.google.gson.JsonObject;
import java.util.function.Function;

/* renamed from: com.oculus.appsafety.signals.-$$Lambda$SafetySignalCollectorConfiguration$EUwKBAWHM4_3majVBKYJz-4gRJ4  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SafetySignalCollectorConfiguration$EUwKBAWHM4_3majVBKYJz4gRJ4 implements Function {
    public static final /* synthetic */ $$Lambda$SafetySignalCollectorConfiguration$EUwKBAWHM4_3majVBKYJz4gRJ4 INSTANCE = new $$Lambda$SafetySignalCollectorConfiguration$EUwKBAWHM4_3majVBKYJz4gRJ4();

    private /* synthetic */ $$Lambda$SafetySignalCollectorConfiguration$EUwKBAWHM4_3majVBKYJz4gRJ4() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((JsonObject) obj).getAsJsonObject("data");
    }
}
