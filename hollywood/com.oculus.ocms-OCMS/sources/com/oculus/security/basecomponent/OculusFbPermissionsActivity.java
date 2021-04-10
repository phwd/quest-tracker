package com.oculus.security.basecomponent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.TrustedApp;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import javax.annotation.Nullable;

@SuppressLint({"EndpointWithoutSwitchOff"})
public abstract class OculusFbPermissionsActivity extends Activity {
    private static final String TAG = "OculusFbPermissionsActivity";
    private InjectionContext _UL_mInjectionContext;
    private Reporter mFbPermissionReporter;
    @Inject
    @Eager
    private OculusIntentLogger mIntentLogger;

    /* access modifiers changed from: protected */
    public abstract void doCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract String getFbPermission();

    private static final void _UL_injectMe(Context context, OculusFbPermissionsActivity oculusFbPermissionsActivity) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), oculusFbPermissionsActivity);
        } else {
            FbInjector.injectMe(OculusFbPermissionsActivity.class, oculusFbPermissionsActivity, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, OculusFbPermissionsActivity oculusFbPermissionsActivity) {
        oculusFbPermissionsActivity._UL_mInjectionContext = new InjectionContext(1, injectorLike);
        oculusFbPermissionsActivity.mIntentLogger = OculusIntentLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_ACCESS_METHOD(injectorLike);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        _UL_injectMe(this, this);
        this.mFbPermissionReporter = new Reporter() {
            /* class com.oculus.security.basecomponent.OculusFbPermissionsActivity.AnonymousClass1 */

            @Override // com.facebook.secure.logger.Reporter
            public void report(String str) {
                BLog.d(OculusFbPermissionsActivity.TAG, "error: %s", str);
                ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, OculusFbPermissionsActivity.this._UL_mInjectionContext)).softError(OculusFbPermissionsActivity.TAG, str);
            }

            @Override // com.facebook.secure.logger.Reporter
            public void report(String str, String str2, @Nullable Throwable th) {
                BLog.d(OculusFbPermissionsActivity.TAG, "error: %s", str2);
                if (th == null) {
                    ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, OculusFbPermissionsActivity.this._UL_mInjectionContext)).softError(str, str2);
                } else {
                    ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, OculusFbPermissionsActivity.this._UL_mInjectionContext)).softError(str, str2, th);
                }
            }
        };
        if (onCheckPermissions()) {
            this.mIntentLogger.logIntent(StringFormatUtil.formatStrLocaleSafe("%s/%s", getPackageName(), getClass().getName()), getIntent());
            doCreate(bundle);
            return;
        }
        throw new SecurityException("Component access not allowed.");
    }

    /* access modifiers changed from: protected */
    public boolean onCheckPermissions() {
        return TrustedApp.checkCallerHasFbPermissions(getFbPermission(), this, getIntent(), this.mFbPermissionReporter);
    }
}
