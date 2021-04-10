package com.oculus.horizon.service.result;

import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QB;
import X.AnonymousClass0QC;
import android.os.Bundle;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.service.CallerUtils;
import com.oculus.horizon.service.OVRService;
import com.oculus.platform.util.Util;
import javax.annotation.Nullable;

public abstract class ResultBuilder implements AnonymousClass0QB {
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public CallerUtils mCallerUtils;
    @Nullable
    public Bundle mErrorBundle = null;
    public final OVRService mService;

    /* JADX INFO: finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v4, types: [com.oculus.platform.aidl.RemoteConstants$EntitlementResult] */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0360, code lost:
        if (r11.latestVersionCode <= r7) goto L_0x0362;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x045b, code lost:
        if (r1.containsKey(java.lang.Integer.valueOf(r2)) == false) goto L_0x045d;
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle A00(android.os.Bundle r27) {
        /*
        // Method dump skipped, instructions count: 1716
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service.result.ResultBuilder.A00(android.os.Bundle):android.os.Bundle");
    }

    public ResultBuilder(OVRService oVRService) {
        AnonymousClass0J2 r2 = AnonymousClass0J2.get(oVRService.getApplicationContext());
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r2);
        this.mCallerUtils = CallerUtils._UL__ULSEP_com_oculus_horizon_service_CallerUtils_ULSEP_ACCESS_METHOD(r2);
        this.mService = oVRService;
    }

    private final Bundle A01(Throwable th, String str, int i) {
        AnonymousClass0NO.A07(getClass(), th, "ResultBuilder error has been set to: %s", str);
        Bundle makeErrorResult = Util.makeErrorResult(th, str, i);
        this.mErrorBundle = makeErrorResult;
        return makeErrorResult;
    }
}
