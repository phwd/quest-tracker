package com.oculus.panelapp.messenger.views;

import com.oculus.messengervr.interfaces.MessengerThread;
import java.util.function.Function;

/* renamed from: com.oculus.panelapp.messenger.views.-$$Lambda$LeftbarView$2$gwujBor2IBWcIcPbQQ3pI8eyqr02  reason: invalid class name */
public final /* synthetic */ class $$Lambda$LeftbarView$2$gwujBor2IBWcIcPbQQ3pI8eyqr02 implements Function {
    public static final /* synthetic */ $$Lambda$LeftbarView$2$gwujBor2IBWcIcPbQQ3pI8eyqr02 INSTANCE = new $$Lambda$LeftbarView$2$gwujBor2IBWcIcPbQQ3pI8eyqr02();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return new ExistingThreadAdapterItem((MessengerThread) obj);
    }
}
