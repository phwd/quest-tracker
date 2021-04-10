package com.oculus.horizon.mediaupload;

import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.C02790bO;
import X.C02800bY;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.Initializer;
import com.oculus.appmanager.verifier.TrustedAppVerifier;
import com.oculus.security.basecomponent.OculusPublicService;

public class MediaUploaderService extends OculusPublicService {
    public static final String TAG = "MediaUploaderService";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Override // X.AnonymousClass1U9
    public final int doStartCommand(Intent intent, int i, int i2) {
        String str;
        ApplicationInfo applicationInfo;
        if (intent == null) {
            AnonymousClass0NO.A08(TAG, "intent is null");
        } else {
            C02790bO A00 = C02800bY.A00(this, intent);
            if (A00 != null) {
                str = C02790bO.A00(A00);
            } else {
                str = null;
            }
            try {
                PackageInfo packageInfo = ((TrustedAppVerifier) AnonymousClass0J2.A03(0, 501, this._UL_mInjectionContext)).mPackageManager.getPackageInfo(str, 0);
                if (!(packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null || (applicationInfo.flags & 1) == 0)) {
                    MediaUploaderServiceInternal.enqueueWork(this, intent);
                    stopSelf(i2);
                    return 3;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            AnonymousClass0NO.A08(TAG, StringFormatUtil.formatStrLocaleSafe("Not called by system app. Package name: %s", str));
        }
        stopSelf(i2);
        return 2;
    }

    @Override // X.AnonymousClass1U9, com.oculus.security.basecomponent.OculusPublicService
    @Initializer
    public final void doCreate() {
        super.doCreate();
        this._UL_mInjectionContext = new AnonymousClass0QC(1, AnonymousClass0J2.get(this));
    }
}
