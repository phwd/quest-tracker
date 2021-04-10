package com.oculus.appsafety.signals;

import com.google.gson.JsonObject;
import java.util.function.Function;

/* renamed from: com.oculus.appsafety.signals.-$$Lambda$SafetySignalCollectorConfiguration$IBPVHj22aKrjg9KUdeyFtvnQPwk  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SafetySignalCollectorConfiguration$IBPVHj22aKrjg9KUdeyFtvnQPwk implements Function {
    public static final /* synthetic */ $$Lambda$SafetySignalCollectorConfiguration$IBPVHj22aKrjg9KUdeyFtvnQPwk INSTANCE = new $$Lambda$SafetySignalCollectorConfiguration$IBPVHj22aKrjg9KUdeyFtvnQPwk();

    private /* synthetic */ $$Lambda$SafetySignalCollectorConfiguration$IBPVHj22aKrjg9KUdeyFtvnQPwk() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((JsonObject) obj).getAsJsonObject("me");
    }
}
