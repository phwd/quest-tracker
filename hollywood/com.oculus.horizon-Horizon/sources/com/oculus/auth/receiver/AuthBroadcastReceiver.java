package com.oculus.auth.receiver;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QB;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.auth.service.contract.ServiceContract;

public class AuthBroadcastReceiver extends BroadcastReceiver implements AnonymousClass0QB {
    @Inject
    @Eager
    public BaseLoginHandler mHandler;

    public static final void _UL_injectMe(Context context, AuthBroadcastReceiver authBroadcastReceiver) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), authBroadcastReceiver);
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r0, AuthBroadcastReceiver authBroadcastReceiver) {
        authBroadcastReceiver.mHandler = ReceiverModule._UL__ULSEP_com_oculus_auth_receiver_BaseLoginHandler_ULSEP_ACCESS_METHOD(r0);
    }

    public void onReceive(Context context, Intent intent) {
        _UL_injectMe(context, this);
        String action = intent.getAction();
        if (action != null) {
            int hashCode = action.hashCode();
            if (hashCode != 168677078) {
                if (hashCode == 698177661 && action.equals(ServiceContract.BROADCAST_LOGIN)) {
                    if (intent.getExtras().getBoolean("is_relogin", false)) {
                        this.mHandler.onRelogin();
                        return;
                    } else {
                        this.mHandler.onLogin();
                        return;
                    }
                }
            } else if (action.equals(ServiceContract.BROADCAST_LOGOUT)) {
                this.mHandler.onLogout();
            }
            AnonymousClass0NO.A08("TAG", AnonymousClass006.A05("Unsupported action: ", action));
        }
    }
}
