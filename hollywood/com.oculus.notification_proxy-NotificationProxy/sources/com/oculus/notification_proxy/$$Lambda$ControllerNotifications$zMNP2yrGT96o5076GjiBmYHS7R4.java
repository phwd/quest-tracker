package com.oculus.notification_proxy;

import android.os.IBinder;
import java.util.function.Function;
import oculus.internal.remote.IRemoteService;

/* renamed from: com.oculus.notification_proxy.-$$Lambda$ControllerNotifications$zMNP2yrGT96o5076GjiBmYHS7R4  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$ControllerNotifications$zMNP2yrGT96o5076GjiBmYHS7R4 implements Function {
    public static final /* synthetic */ $$Lambda$ControllerNotifications$zMNP2yrGT96o5076GjiBmYHS7R4 INSTANCE = new $$Lambda$ControllerNotifications$zMNP2yrGT96o5076GjiBmYHS7R4();

    private /* synthetic */ $$Lambda$ControllerNotifications$zMNP2yrGT96o5076GjiBmYHS7R4() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return IRemoteService.Stub.asInterface((IBinder) obj);
    }
}
