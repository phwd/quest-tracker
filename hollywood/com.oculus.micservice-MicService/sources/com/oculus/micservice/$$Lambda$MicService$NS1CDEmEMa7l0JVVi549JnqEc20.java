package com.oculus.micservice;

import android.os.IBinder;
import java.util.function.Function;
import oculus.internal.power.IVrPowerManager;

/* renamed from: com.oculus.micservice.-$$Lambda$MicService$NS1CDEmEMa7l0JVVi549JnqEc20  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MicService$NS1CDEmEMa7l0JVVi549JnqEc20 implements Function {
    public static final /* synthetic */ $$Lambda$MicService$NS1CDEmEMa7l0JVVi549JnqEc20 INSTANCE = new $$Lambda$MicService$NS1CDEmEMa7l0JVVi549JnqEc20();

    private /* synthetic */ $$Lambda$MicService$NS1CDEmEMa7l0JVVi549JnqEc20() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return IVrPowerManager.Stub.asInterface((IBinder) obj);
    }
}
