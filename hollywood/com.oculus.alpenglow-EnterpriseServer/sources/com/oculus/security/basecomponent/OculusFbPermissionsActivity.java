package com.oculus.security.basecomponent;

import X.AbstractC04970iB;
import X.AnonymousClass0Lh;
import X.AnonymousClass0R7;
import X.AnonymousClass13m;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import javax.annotation.Nullable;

@SuppressLint({"EndpointWithoutSwitchOff"})
public abstract class OculusFbPermissionsActivity extends Activity {
    public static final String TAG = "OculusFbPermissionsActivity";
    public AnonymousClass0R7 _UL_mInjectionContext;
    public AbstractC04970iB mFbPermissionReporter;
    @Inject
    @Eager
    public OculusIntentLogger mIntentLogger;

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AnonymousClass0Lh r2 = AnonymousClass0Lh.get(this);
        this._UL_mInjectionContext = new AnonymousClass0R7(1, r2);
        this.mIntentLogger = (OculusIntentLogger) AnonymousClass13m.A00(23, r2);
        this.mFbPermissionReporter = new AbstractC04970iB() {
            /* class com.oculus.security.basecomponent.OculusFbPermissionsActivity.AnonymousClass1 */

            @Override // X.AbstractC04970iB
            public final void A7P(String str) {
            }

            @Override // X.AbstractC04970iB
            public final void A7Q(String str, String str2, @Nullable Throwable th) {
            }
        };
        getIntent();
        throw null;
    }
}
