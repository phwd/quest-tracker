package com.oculus.messengervr.oc;

import X.AbstractC13191z2;

/* renamed from: com.oculus.messengervr.oc.-$$Lambda$MessageListObservableUtil$qR7FPsoPiq8CH538jvPL10SKrX82  reason: invalid class name */
public final /* synthetic */ class $$Lambda$MessageListObservableUtil$qR7FPsoPiq8CH538jvPL10SKrX82 implements AbstractC13191z2 {
    public static final /* synthetic */ $$Lambda$MessageListObservableUtil$qR7FPsoPiq8CH538jvPL10SKrX82 INSTANCE = new $$Lambda$MessageListObservableUtil$qR7FPsoPiq8CH538jvPL10SKrX82();

    @Override // X.AbstractC13191z2
    public final boolean test(Object obj) {
        return Constants.MESSAGE_UPDATE_RESULT_TYPES.contains(((OcMessengerManagerUpdateResult) obj).mUpdateResultType);
    }
}
