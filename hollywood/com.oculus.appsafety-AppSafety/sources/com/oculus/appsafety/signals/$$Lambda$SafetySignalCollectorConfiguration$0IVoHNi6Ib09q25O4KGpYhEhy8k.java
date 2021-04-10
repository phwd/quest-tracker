package com.oculus.appsafety.signals;

import com.google.gson.JsonPrimitive;
import java.util.function.Function;

/* renamed from: com.oculus.appsafety.signals.-$$Lambda$SafetySignalCollectorConfiguration$0IVoHNi6Ib09q25O4KGpYhEhy8k  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SafetySignalCollectorConfiguration$0IVoHNi6Ib09q25O4KGpYhEhy8k implements Function {
    public static final /* synthetic */ $$Lambda$SafetySignalCollectorConfiguration$0IVoHNi6Ib09q25O4KGpYhEhy8k INSTANCE = new $$Lambda$SafetySignalCollectorConfiguration$0IVoHNi6Ib09q25O4KGpYhEhy8k();

    private /* synthetic */ $$Lambda$SafetySignalCollectorConfiguration$0IVoHNi6Ib09q25O4KGpYhEhy8k() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((JsonPrimitive) obj).getAsString();
    }
}
