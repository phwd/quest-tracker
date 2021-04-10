package com.oculus.alpenglow.boot;

import X.AbstractC04990iH;
import X.AbstractC05000iI;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R6;
import X.AnonymousClass0R7;
import android.content.Context;
import android.content.Intent;
import java.util.Set;

public class UserUnlockedActionReceiver implements AbstractC04990iH, AnonymousClass0R6 {
    public static final String TAG = "EnterpriseServer.UserUnlockedActionReceiver";
    public static UserUnlockedActionReceiver mUserUnlockedActionReceiver;
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Override // X.AbstractC04990iH
    public final void onReceive(Context context, Intent intent, AbstractC05000iI r7) {
        String str;
        String action = intent.getAction();
        if (action != null) {
            int hashCode = action.hashCode();
            if (hashCode == 823795052) {
                str = "android.intent.action.USER_PRESENT";
            } else if (hashCode == 833559602) {
                str = "android.intent.action.USER_UNLOCKED";
            } else {
                return;
            }
            if (action.equals(str)) {
                AnonymousClass0R7 r1 = new AnonymousClass0R7(1, AnonymousClass0Lh.get(context));
                this._UL_mInjectionContext = r1;
                Object A03 = AnonymousClass0Lh.A03(0, 69, r1);
                if (A03 != null) {
                    for (UnlockListener unlockListener : (Set) A03) {
                        try {
                            unlockListener.A6h(context);
                        } catch (Exception e) {
                            AnonymousClass0NK.A04(TAG, "error in unlock listener", e);
                        }
                    }
                }
            }
        }
    }
}
