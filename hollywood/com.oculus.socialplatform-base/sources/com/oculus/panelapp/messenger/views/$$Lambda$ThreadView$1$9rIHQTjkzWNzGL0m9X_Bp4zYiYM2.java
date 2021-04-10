package com.oculus.panelapp.messenger.views;

import com.oculus.panelapp.messenger.fetchers.MessengerContact;
import java.util.function.Function;

/* renamed from: com.oculus.panelapp.messenger.views.-$$Lambda$ThreadView$1$9rIHQTjkzWNzGL0m9X_Bp4zYiYM2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$ThreadView$1$9rIHQTjkzWNzGL0m9X_Bp4zYiYM2 implements Function {
    public static final /* synthetic */ $$Lambda$ThreadView$1$9rIHQTjkzWNzGL0m9X_Bp4zYiYM2 INSTANCE = new $$Lambda$ThreadView$1$9rIHQTjkzWNzGL0m9X_Bp4zYiYM2();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return new DraftThreadParticipantAdapterItem((MessengerContact) obj);
    }
}
