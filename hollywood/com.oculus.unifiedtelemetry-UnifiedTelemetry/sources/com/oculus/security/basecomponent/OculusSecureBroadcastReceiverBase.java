package com.oculus.security.basecomponent;

import X.AbstractC0096Hu;
import X.AbstractC0382ge;
import X.AbstractC0385gk;
import X.AbstractC0386gl;
import X.QC;
import X.XR;
import X.XT;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import javax.annotation.Nullable;

public abstract class OculusSecureBroadcastReceiverBase extends XR {
    public QC _UL_mInjectionContext;
    public AbstractC0385gk mActionReceiver = new AbstractC0385gk() {
        /* class com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase.AnonymousClass1 */

        @Override // X.AbstractC0385gk
        public final void A3q(Context context, Intent intent, AbstractC0386gl glVar) {
            OculusSecureBroadcastReceiverBase.this.A08(context, intent, glVar);
        }
    };
    @Nullable
    public IntentFilter mIntentFilter = null;
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;
    public final AbstractC0382ge mReporter = new BLogDebugReporter(getClass().getName());

    @Override // X.XR
    public final Object A04(AbstractC0385gk gkVar) {
        return this;
    }

    @Override // X.XR
    public final boolean A07(String str) {
        return false;
    }

    public abstract void A08(Context context, Intent intent, AbstractC0386gl glVar);

    @Override // X.XR
    public final boolean A06(Context context, Intent intent) {
        IntentFilter intentFilter = this.mIntentFilter;
        if ((intentFilter == null || intentFilter.hasAction(intent.getAction())) && super.A06(context, intent)) {
            return true;
        }
        return false;
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

    @Override // X.XR
    public final void A05(Context context) {
        super.A05(context);
        if (this.mOculusIntentLogger == null || AbstractC0096Hu.A03(0, 135, this._UL_mInjectionContext) == null) {
            AbstractC0096Hu hu = AbstractC0096Hu.get(context);
            this._UL_mInjectionContext = new QC(1, hu);
            this.mOculusIntentLogger = OculusIntentLogger.A00(hu);
        }
    }

    @Override // X.XR
    public final XT A01() {
        return this.mOculusIntentLogger;
    }

    @Override // X.XR
    public final AbstractC0382ge A02() {
        return this.mReporter;
    }

    @Override // X.XR
    @Nullable
    public final AbstractC0385gk A03(Context context, String str) {
        return this.mActionReceiver;
    }
}
