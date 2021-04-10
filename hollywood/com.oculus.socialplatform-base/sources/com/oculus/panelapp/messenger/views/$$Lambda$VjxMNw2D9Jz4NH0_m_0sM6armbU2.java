package com.oculus.panelapp.messenger.views;

import com.oculus.panelapp.messenger.api.IMessengerAPI;
import java.util.function.Function;

/* renamed from: com.oculus.panelapp.messenger.views.-$$Lambda$VjxMNw2D9Jz4NH0_m_0sM6armbU2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$VjxMNw2D9Jz4NH0_m_0sM6armbU2 implements Function {
    public static final /* synthetic */ $$Lambda$VjxMNw2D9Jz4NH0_m_0sM6armbU2 INSTANCE = new $$Lambda$VjxMNw2D9Jz4NH0_m_0sM6armbU2();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((IMessengerAPI) obj).getType();
    }
}
