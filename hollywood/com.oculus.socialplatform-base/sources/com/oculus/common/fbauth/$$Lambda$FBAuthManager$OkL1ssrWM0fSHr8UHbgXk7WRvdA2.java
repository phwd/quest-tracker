package com.oculus.common.fbauth;

import X.AbstractC06371Zh;
import X.AbstractC10551og;

/* renamed from: com.oculus.common.fbauth.-$$Lambda$FBAuthManager$OkL1ssrWM0fSHr8UHbgXk7WRvdA2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$FBAuthManager$OkL1ssrWM0fSHr8UHbgXk7WRvdA2 implements AbstractC06371Zh {
    public static final /* synthetic */ $$Lambda$FBAuthManager$OkL1ssrWM0fSHr8UHbgXk7WRvdA2 INSTANCE = new $$Lambda$FBAuthManager$OkL1ssrWM0fSHr8UHbgXk7WRvdA2();

    @Override // X.AbstractC06371Zh
    public final void subscribe(AbstractC10551og r1) {
        r1.onSuccess(FBAuthManager.generateAccessToken());
    }
}
