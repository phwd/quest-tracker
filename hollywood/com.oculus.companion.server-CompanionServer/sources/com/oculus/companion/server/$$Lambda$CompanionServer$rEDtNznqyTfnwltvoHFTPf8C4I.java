package com.oculus.companion.server;

import android.os.IBinder;
import java.util.function.Function;
import oculus.internal.power.IVrPowerManager;

/* renamed from: com.oculus.companion.server.-$$Lambda$CompanionServer$rEDtNznqy-TfnwltvoHFTPf8C4I  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$CompanionServer$rEDtNznqyTfnwltvoHFTPf8C4I implements Function {
    public static final /* synthetic */ $$Lambda$CompanionServer$rEDtNznqyTfnwltvoHFTPf8C4I INSTANCE = new $$Lambda$CompanionServer$rEDtNznqyTfnwltvoHFTPf8C4I();

    private /* synthetic */ $$Lambda$CompanionServer$rEDtNznqyTfnwltvoHFTPf8C4I() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return IVrPowerManager.Stub.asInterface((IBinder) obj);
    }
}
