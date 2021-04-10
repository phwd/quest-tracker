package com.oculus.security.basecomponent;

import X.AbstractC02660jW;
import X.AbstractC02700jf;
import X.AbstractC02970kf;
import X.AbstractC03010kk;
import X.AnonymousClass0RE;
import X.AnonymousClass0VF;
import X.AnonymousClass0jg;
import X.AnonymousClass0lg;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import javax.annotation.Nullable;

public abstract class OculusSecureBroadcastReceiverBase extends AbstractC02970kf {
    public AnonymousClass0RE _UL_mInjectionContext;
    public AbstractC02700jf mActionReceiver = new AbstractC02700jf() {
        /* class com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase.AnonymousClass1 */

        @Override // X.AbstractC02700jf
        public void onReceive(Context context, Intent intent, AnonymousClass0jg r4) {
            OculusSecureBroadcastReceiverBase.this.onReceive(context, intent, r4);
        }
    };
    @Nullable
    public IntentFilter mIntentFilter = null;
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;
    public final AbstractC02660jW mReporter = new BLogDebugReporter(getClass().getName());

    public static final void _UL_staticInjectMe(AnonymousClass0lg r2, OculusSecureBroadcastReceiverBase oculusSecureBroadcastReceiverBase) {
        oculusSecureBroadcastReceiverBase._UL_mInjectionContext = new AnonymousClass0RE(1, r2);
        oculusSecureBroadcastReceiverBase.mOculusIntentLogger = OculusIntentLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_ACCESS_METHOD(r2);
    }

    @Override // X.AbstractC02970kf
    public final Object endpointObjectFor(AbstractC02700jf r1) {
        return this;
    }

    @Override // X.AbstractC02970kf
    public final boolean isActionRemoved(String str) {
        return false;
    }

    public abstract void onReceive(Context context, Intent intent, AnonymousClass0jg v);

    public abstract void registerReceiver(Context context);

    private boolean isActionAllowed(Intent intent) {
        IntentFilter intentFilter = this.mIntentFilter;
        if (intentFilter == null || intentFilter.hasAction(intent.getAction())) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC02970kf
    public final IntentFilter getIntentFilter() {
        IntentFilter intentFilter = this.mIntentFilter;
        if (intentFilter != null) {
            return intentFilter;
        }
        throw new UnsupportedOperationException("intent filter is not initialized");
    }

    public OculusSecureBroadcastReceiverBase(String... strArr) {
        int length = strArr.length;
        if (length > 0) {
            this.mIntentFilter = new IntentFilter();
            for (String str : strArr) {
                this.mIntentFilter.addAction(str);
            }
        }
    }

    public static final void _UL_injectMe(Context context, OculusSecureBroadcastReceiverBase oculusSecureBroadcastReceiverBase) {
        _UL_staticInjectMe(AnonymousClass0VF.get(context), oculusSecureBroadcastReceiverBase);
    }

    @Override // X.AbstractC02970kf
    public AbstractC03010kk getIntentLogger() {
        return this.mOculusIntentLogger;
    }

    @Override // X.AbstractC02970kf
    public final AbstractC02660jW getReporter() {
        return this.mReporter;
    }

    @Override // X.AbstractC02970kf
    public void onBeforeReceive(Context context) {
        super.onBeforeReceive(context);
        if (this.mOculusIntentLogger == null || AnonymousClass0VF.A03(0, 114, this._UL_mInjectionContext) == null) {
            _UL_injectMe(context, this);
        }
    }

    @Override // X.AbstractC02970kf
    public boolean shouldProcessBroadcast(Context context, Intent intent) {
        if (!isActionAllowed(intent) || !super.shouldProcessBroadcast(context, intent)) {
            return false;
        }
        return true;
    }

    public void setIntentFilter(IntentFilter intentFilter) {
        this.mIntentFilter = intentFilter;
    }

    public void unregisterReceiver(Context context) {
        context.unregisterReceiver(this);
    }

    @Override // X.AbstractC02970kf
    @Nullable
    public final AbstractC02700jf findReceiverForIntent(Context context, String str) {
        return this.mActionReceiver;
    }
}
