package com.oculus.security.basecomponent;

import X.AbstractC02660jW;
import X.AnonymousClass0RE;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import javax.annotation.Nullable;

@SuppressLint({"EndpointWithoutSwitchOff"})
public abstract class OculusFbPermissionsActivity extends Activity {
    public static final String TAG = "OculusFbPermissionsActivity";
    public AnonymousClass0RE _UL_mInjectionContext;
    public AbstractC02660jW mFbPermissionReporter;
    @Inject
    @Eager
    public OculusIntentLogger mIntentLogger;

    public static final void _UL_staticInjectMe(AnonymousClass0lg r2, OculusFbPermissionsActivity oculusFbPermissionsActivity) {
        oculusFbPermissionsActivity._UL_mInjectionContext = new AnonymousClass0RE(1, r2);
        oculusFbPermissionsActivity.mIntentLogger = OculusIntentLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_ACCESS_METHOD(r2);
    }

    public abstract void doCreate(Bundle bundle);

    public abstract String getFbPermission();

    public static final void _UL_injectMe(Context context, OculusFbPermissionsActivity oculusFbPermissionsActivity) {
        _UL_staticInjectMe(AnonymousClass0VF.get(context), oculusFbPermissionsActivity);
    }

    public boolean onCheckPermissions() {
        getIntent();
        throw new NullPointerException("getFbPermission");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        _UL_injectMe(this, this);
        this.mFbPermissionReporter = new AbstractC02660jW() {
            /* class com.oculus.security.basecomponent.OculusFbPermissionsActivity.AnonymousClass1 */

            @Override // X.AbstractC02660jW
            public void report(String str) {
                ((IErrorReporter) AnonymousClass0VF.A03(0, 114, OculusFbPermissionsActivity.this._UL_mInjectionContext)).softError(OculusFbPermissionsActivity.TAG, str);
            }

            @Override // X.AbstractC02660jW
            public void report(String str, String str2, @Nullable Throwable th) {
                if (th == null) {
                    ((IErrorReporter) AnonymousClass0VF.A03(0, 114, OculusFbPermissionsActivity.this._UL_mInjectionContext)).softError(str, str2);
                } else {
                    ((IErrorReporter) AnonymousClass0VF.A03(0, 114, OculusFbPermissionsActivity.this._UL_mInjectionContext)).softError(str, str2, th);
                }
            }
        };
        onCheckPermissions();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
