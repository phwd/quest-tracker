package com.oculus.notification_proxy;

import android.os.IBinder;
import java.util.function.Function;
import oculus.internal.power.IVrPowerManager;

/* renamed from: com.oculus.notification_proxy.-$$Lambda$VrPowerManagerClient$VudFZFrKlcrr04OAzU2LW7D33xQ  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$VrPowerManagerClient$VudFZFrKlcrr04OAzU2LW7D33xQ implements Function {
    public static final /* synthetic */ $$Lambda$VrPowerManagerClient$VudFZFrKlcrr04OAzU2LW7D33xQ INSTANCE = new $$Lambda$VrPowerManagerClient$VudFZFrKlcrr04OAzU2LW7D33xQ();

    private /* synthetic */ $$Lambda$VrPowerManagerClient$VudFZFrKlcrr04OAzU2LW7D33xQ() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return IVrPowerManager.Stub.asInterface((IBinder) obj);
    }
}
