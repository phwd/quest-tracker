package com.oculus.security.basecomponent;

import X.AbstractC04590iB;
import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0b1;
import X.AnonymousClass0b8;
import X.AnonymousClass0b9;
import X.AnonymousClass0i9;
import X.AnonymousClass117;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import javax.annotation.Nullable;

public abstract class OculusSecureBroadcastReceiverBase extends AnonymousClass0i9 {
    public AnonymousClass0QC _UL_mInjectionContext;
    public AnonymousClass0b8 mActionReceiver = new AnonymousClass0b8() {
        /* class com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase.AnonymousClass1 */

        @Override // X.AnonymousClass0b8
        public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r4) {
            OculusSecureBroadcastReceiverBase.this.onReceive(context, intent, r4);
        }
    };
    @Nullable
    public IntentFilter mIntentFilter = null;
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;
    public final AnonymousClass0b1 mReporter = new BLogDebugReporter(getClass().getName());

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, OculusSecureBroadcastReceiverBase oculusSecureBroadcastReceiverBase) {
        oculusSecureBroadcastReceiverBase._UL_mInjectionContext = new AnonymousClass0QC(1, r2);
        oculusSecureBroadcastReceiverBase.mOculusIntentLogger = (OculusIntentLogger) AnonymousClass117.A00(172, r2);
    }

    @Override // X.AnonymousClass0i9
    public final Object endpointObjectFor(AnonymousClass0b8 r1) {
        return this;
    }

    @Override // X.AnonymousClass0i9
    public final boolean isActionRemoved(String str) {
        return false;
    }

    public abstract void onReceive(Context context, Intent intent, AnonymousClass0b9 v);

    public abstract void registerReceiver(Context context);

    private boolean isActionAllowed(Intent intent) {
        IntentFilter intentFilter = this.mIntentFilter;
        if (intentFilter == null || intentFilter.hasAction(intent.getAction())) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0i9
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
        _UL_staticInjectMe(AnonymousClass0J2.get(context), oculusSecureBroadcastReceiverBase);
    }

    @Override // X.AnonymousClass0i9
    public AbstractC04590iB getIntentLogger() {
        return this.mOculusIntentLogger;
    }

    @Override // X.AnonymousClass0i9
    public final AnonymousClass0b1 getReporter() {
        return this.mReporter;
    }

    @Override // X.AnonymousClass0i9
    public void onBeforeReceive(Context context) {
        super.onBeforeReceive(context);
        if (this.mOculusIntentLogger == null || AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext) == null) {
            _UL_injectMe(context, this);
        }
    }

    @Override // X.AnonymousClass0i9
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

    @Override // X.AnonymousClass0i9
    @Nullable
    public final AnonymousClass0b8 findReceiverForIntent(Context context, String str) {
        return this.mActionReceiver;
    }
}
