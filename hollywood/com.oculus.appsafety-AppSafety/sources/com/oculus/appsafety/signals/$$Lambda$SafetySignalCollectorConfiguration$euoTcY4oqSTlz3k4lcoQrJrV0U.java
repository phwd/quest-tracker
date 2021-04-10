package com.oculus.appsafety.signals;

import com.google.gson.JsonObject;
import java.util.function.Function;

/* renamed from: com.oculus.appsafety.signals.-$$Lambda$SafetySignalCollectorConfiguration$euoTcY4oqST-lz3k4lcoQrJrV0U  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SafetySignalCollectorConfiguration$euoTcY4oqSTlz3k4lcoQrJrV0U implements Function {
    public static final /* synthetic */ $$Lambda$SafetySignalCollectorConfiguration$euoTcY4oqSTlz3k4lcoQrJrV0U INSTANCE = new $$Lambda$SafetySignalCollectorConfiguration$euoTcY4oqSTlz3k4lcoQrJrV0U();

    private /* synthetic */ $$Lambda$SafetySignalCollectorConfiguration$euoTcY4oqSTlz3k4lcoQrJrV0U() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((JsonObject) obj).getAsJsonObject("hwm_device");
    }
}
