package com.oculus.horizon.service_media;

import X.AbstractC06640p5;
import X.C003108z;
import X.C02780bN;
import X.C02870bf;
import X.C02880bg;
import android.content.Context;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.debug.DebugMode;
import java.util.Collections;
import java.util.HashSet;

@Dependencies({"_UL__ULSEP_com_oculus_debug_DebugMode_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
public class HorizonCallerPackageVerifier {
    public static final C02870bf sHorizonDebugTrustedApp = C02880bg.A02(new HashSet(Collections.singletonList(C02780bN.A01)), new HashSet(Collections.singletonList("com.oculus.horizon")));
    public static final C02870bf sHorizonReleaseTrustedApp = C02880bg.A02(new HashSet(Collections.singletonList(C02780bN.A0P)), new HashSet(Collections.singletonList("com.oculus.horizon")));
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    @Inject
    @Eager
    public final DebugMode mDebugMode;

    @Inject
    public HorizonCallerPackageVerifier(AbstractC06640p5 r2) {
        this.mDebugMode = DebugMode.A00(r2);
        this.mContext = C003108z.A02(r2);
    }
}
