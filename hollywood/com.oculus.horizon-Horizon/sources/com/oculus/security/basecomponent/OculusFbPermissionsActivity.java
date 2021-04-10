package com.oculus.security.basecomponent;

import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0b1;
import X.AnonymousClass117;
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
    public AnonymousClass0QC _UL_mInjectionContext;
    public AnonymousClass0b1 mFbPermissionReporter;
    @Inject
    @Eager
    public OculusIntentLogger mIntentLogger;

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AnonymousClass0J2 r2 = AnonymousClass0J2.get(this);
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r2);
        this.mIntentLogger = (OculusIntentLogger) AnonymousClass117.A00(172, r2);
        this.mFbPermissionReporter = new AnonymousClass0b1() {
            /* class com.oculus.security.basecomponent.OculusFbPermissionsActivity.AnonymousClass1 */

            @Override // X.AnonymousClass0b1
            public final void report(String str) {
                ((IErrorReporter) AnonymousClass0J2.A03(0, 428, OculusFbPermissionsActivity.this._UL_mInjectionContext)).A96(OculusFbPermissionsActivity.TAG, str);
            }

            @Override // X.AnonymousClass0b1
            public final void report(String str, String str2, @Nullable Throwable th) {
                if (th == null) {
                    ((IErrorReporter) AnonymousClass0J2.A03(0, 428, OculusFbPermissionsActivity.this._UL_mInjectionContext)).A96(str, str2);
                } else {
                    ((IErrorReporter) AnonymousClass0J2.A03(0, 428, OculusFbPermissionsActivity.this._UL_mInjectionContext)).A97(str, str2, th);
                }
            }
        };
        getIntent();
        throw null;
    }
}
