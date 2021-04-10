package com.oculus.alpenglow.boot;

import X.AbstractC04990iH;
import X.AbstractC05000iI;
import X.AnonymousClass0LT;
import X.AnonymousClass0Lh;
import X.AnonymousClass0R6;
import X.AnonymousClass0R7;
import android.content.Context;
import android.content.Intent;
import java.util.Set;

public class BootBroadcastReceiver extends AnonymousClass0LT {
    public static final String TAG = "EnterpriseServer.BootBroadcastReceiver";

    public static class BootActionReceiver implements AbstractC04990iH, AnonymousClass0R6 {
        public AnonymousClass0R7 _UL_mInjectionContext;

        @Override // X.AbstractC04990iH
        public final void onReceive(Context context, Intent intent, AbstractC05000iI r6) {
            intent.getAction();
            if ("android.intent.action.LOCKED_BOOT_COMPLETED".equals(intent.getAction())) {
                AnonymousClass0R7 r2 = new AnonymousClass0R7(1, AnonymousClass0Lh.get(context));
                this._UL_mInjectionContext = r2;
                for (BootListener bootListener : (Set) AnonymousClass0Lh.A03(0, 19, r2)) {
                    bootListener.A5u(context);
                }
            }
        }
    }

    public BootBroadcastReceiver() {
        super("android.intent.action.LOCKED_BOOT_COMPLETED", new BootActionReceiver());
    }
}
