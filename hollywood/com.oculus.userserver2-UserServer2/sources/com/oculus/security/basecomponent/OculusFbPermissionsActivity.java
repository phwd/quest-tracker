package com.oculus.security.basecomponent;

import X.AbstractC0201ew;
import X.BZ;
import X.IX;
import X.Om;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import javax.annotation.Nullable;

@SuppressLint({"EndpointWithoutSwitchOff"})
public abstract class OculusFbPermissionsActivity extends Activity {
    public static final String TAG = "OculusFbPermissionsActivity";
    public Om _UL_mInjectionContext;
    public AbstractC0201ew mFbPermissionReporter;
    @Inject
    @Eager
    public OculusIntentLogger mIntentLogger;

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BZ bz = BZ.get(this);
        this._UL_mInjectionContext = new Om(1, bz);
        this.mIntentLogger = (OculusIntentLogger) IX.A00(36, bz);
        this.mFbPermissionReporter = new AbstractC0201ew() {
            /* class com.oculus.security.basecomponent.OculusFbPermissionsActivity.AnonymousClass1 */

            @Override // X.AbstractC0201ew
            public final void A3M(String str, String str2, @Nullable Throwable th) {
                if (th == null) {
                    ((IErrorReporter) BZ.A02(0, 78, OculusFbPermissionsActivity.this._UL_mInjectionContext)).A3k(str, str2);
                } else {
                    ((IErrorReporter) BZ.A02(0, 78, OculusFbPermissionsActivity.this._UL_mInjectionContext)).A3l(str, str2, th);
                }
            }

            @Override // X.AbstractC0201ew
            public final void A3L(String str) {
                ((IErrorReporter) BZ.A02(0, 78, OculusFbPermissionsActivity.this._UL_mInjectionContext)).A3k(OculusFbPermissionsActivity.TAG, str);
            }
        };
        getIntent();
        throw null;
    }
}
