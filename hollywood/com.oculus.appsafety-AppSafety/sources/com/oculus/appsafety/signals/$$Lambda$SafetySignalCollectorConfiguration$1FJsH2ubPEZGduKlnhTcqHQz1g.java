package com.oculus.appsafety.signals;

import com.google.gson.JsonObject;
import java.util.function.Function;

/* renamed from: com.oculus.appsafety.signals.-$$Lambda$SafetySignalCollectorConfiguration$1FJsH2ubPEZGduKlnh-TcqHQz1g  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SafetySignalCollectorConfiguration$1FJsH2ubPEZGduKlnhTcqHQz1g implements Function {
    public static final /* synthetic */ $$Lambda$SafetySignalCollectorConfiguration$1FJsH2ubPEZGduKlnhTcqHQz1g INSTANCE = new $$Lambda$SafetySignalCollectorConfiguration$1FJsH2ubPEZGduKlnhTcqHQz1g();

    private /* synthetic */ $$Lambda$SafetySignalCollectorConfiguration$1FJsH2ubPEZGduKlnhTcqHQz1g() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((JsonObject) obj).getAsJsonPrimitive("safety_signal_config");
    }
}
