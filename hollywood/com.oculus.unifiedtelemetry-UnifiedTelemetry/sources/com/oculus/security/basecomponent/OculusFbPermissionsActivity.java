package com.oculus.security.basecomponent;

import X.AbstractC0096Hu;
import X.AbstractC0382ge;
import X.QC;
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
    public QC _UL_mInjectionContext;
    public AbstractC0382ge mFbPermissionReporter;
    @Inject
    @Eager
    public OculusIntentLogger mIntentLogger;

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AbstractC0096Hu hu = AbstractC0096Hu.get(this);
        this._UL_mInjectionContext = new QC(1, hu);
        this.mIntentLogger = OculusIntentLogger.A00(hu);
        this.mFbPermissionReporter = new AbstractC0382ge() {
            /* class com.oculus.security.basecomponent.OculusFbPermissionsActivity.AnonymousClass1 */

            @Override // X.AbstractC0382ge
            public final void A4i(String str, String str2, @Nullable Throwable th) {
                if (th == null) {
                    ((IErrorReporter) AbstractC0096Hu.A03(0, 135, OculusFbPermissionsActivity.this._UL_mInjectionContext)).A5H(str, str2);
                } else {
                    ((IErrorReporter) AbstractC0096Hu.A03(0, 135, OculusFbPermissionsActivity.this._UL_mInjectionContext)).A5I(str, str2, th);
                }
            }
        };
        getIntent();
        throw null;
    }
}
