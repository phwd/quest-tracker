package com.oculus.common.fbauth;

import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/* renamed from: com.oculus.common.fbauth.-$$Lambda$FBAuthManager$OkL1ssrWM0fSHr8UHbgXk7WRvdA  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$FBAuthManager$OkL1ssrWM0fSHr8UHbgXk7WRvdA implements SingleOnSubscribe {
    public static final /* synthetic */ $$Lambda$FBAuthManager$OkL1ssrWM0fSHr8UHbgXk7WRvdA INSTANCE = new $$Lambda$FBAuthManager$OkL1ssrWM0fSHr8UHbgXk7WRvdA();

    private /* synthetic */ $$Lambda$FBAuthManager$OkL1ssrWM0fSHr8UHbgXk7WRvdA() {
    }

    public final void subscribe(SingleEmitter singleEmitter) {
        FBAuthManager.lambda$queryAccessToken$0(singleEmitter);
    }
}
