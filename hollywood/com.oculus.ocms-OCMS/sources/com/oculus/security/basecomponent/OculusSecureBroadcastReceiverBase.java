package com.oculus.security.basecomponent;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.secure.logger.IntentLogger;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.receiver.ActionReceiver;
import com.facebook.secure.receiver.BroadcastReceiverLike;
import com.facebook.secure.receiver.SecureBroadcastReceiver;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import javax.annotation.Nullable;

abstract class OculusSecureBroadcastReceiverBase extends SecureBroadcastReceiver {
    private InjectionContext _UL_mInjectionContext;
    private ActionReceiver mActionReceiver = new ActionReceiver() {
        /* class com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase.AnonymousClass1 */

        @Override // com.facebook.secure.receiver.ActionReceiver
        public void onReceive(Context context, Intent intent, BroadcastReceiverLike broadcastReceiverLike) {
            OculusSecureBroadcastReceiverBase.this.onReceive(context, intent, broadcastReceiverLike);
        }
    };
    @Nullable
    private IntentFilter mIntentFilter = null;
    @Inject
    @Eager
    private OculusIntentLogger mOculusIntentLogger;
    private final Reporter mReporter = new BLogDebugReporter(getClass().getName());

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.receiver.SecureBroadcastReceiver
    public final Object endpointObjectFor(ActionReceiver actionReceiver) {
        return this;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.receiver.SecureBroadcastReceiver
    public final boolean isActionRemoved(String str) {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract void onReceive(Context context, Intent intent, BroadcastReceiverLike broadcastReceiverLike);

    public abstract void registerReceiver(Context context);

    private static final void _UL_injectMe(Context context, OculusSecureBroadcastReceiverBase oculusSecureBroadcastReceiverBase) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), oculusSecureBroadcastReceiverBase);
        } else {
            FbInjector.injectMe(OculusSecureBroadcastReceiverBase.class, oculusSecureBroadcastReceiverBase, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, OculusSecureBroadcastReceiverBase oculusSecureBroadcastReceiverBase) {
        oculusSecureBroadcastReceiverBase._UL_mInjectionContext = new InjectionContext(1, injectorLike);
        oculusSecureBroadcastReceiverBase.mOculusIntentLogger = OculusIntentLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public OculusSecureBroadcastReceiverBase(String... strArr) {
        if (strArr.length > 0) {
            this.mIntentFilter = new IntentFilter();
            for (String str : strArr) {
                this.mIntentFilter.addAction(str);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.receiver.SecureBroadcastReceiver
    @Nullable
    public final ActionReceiver findReceiverForIntent(Context context, String str) {
        return this.mActionReceiver;
    }

    @Override // com.facebook.secure.receiver.SecureBroadcastReceiver
    public final Reporter getReporter() {
        return this.mReporter;
    }

    @Override // com.facebook.secure.receiver.SecureBroadcastReceiver
    public IntentLogger getIntentLogger() {
        return this.mOculusIntentLogger;
    }

    @Override // com.facebook.secure.receiver.SecureBroadcastReceiver
    public final IntentFilter getIntentFilter() {
        IntentFilter intentFilter = this.mIntentFilter;
        if (intentFilter != null) {
            return intentFilter;
        }
        throw new UnsupportedOperationException("intent filter is not initialized");
    }

    public void setIntentFilter(IntentFilter intentFilter) {
        this.mIntentFilter = intentFilter;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.receiver.SecureBroadcastReceiver
    public boolean shouldProcessBroadcast(Context context, Intent intent) {
        return isActionAllowed(intent) && super.shouldProcessBroadcast(context, intent);
    }

    private boolean isActionAllowed(Intent intent) {
        IntentFilter intentFilter = this.mIntentFilter;
        return intentFilter == null || intentFilter.hasAction(intent.getAction());
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.receiver.SecureBroadcastReceiver
    public void onBeforeReceive(Context context) {
        super.onBeforeReceive(context);
        if (this.mOculusIntentLogger == null || ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)) == null) {
            _UL_injectMe(context, this);
        }
    }

    public void unregisterReceiver(Context context) {
        context.unregisterReceiver(this);
    }
}
