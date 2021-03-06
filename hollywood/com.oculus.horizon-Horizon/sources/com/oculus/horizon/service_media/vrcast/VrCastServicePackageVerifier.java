package com.oculus.horizon.service_media.vrcast;

import X.AbstractC06640p5;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C003008y;
import X.C003809k;
import X.C01020Iw;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import javax.annotation.Nullable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID"})
public class VrCastServicePackageVerifier {
    public static final String OCULUS_VRCAST_SERVICE = "com.oculus.vrcast";
    @Inject
    @Eager
    public final PackageManager mPackageManager;

    public boolean isSystemApp(@Nullable String str) {
        ApplicationInfo applicationInfo;
        try {
            PackageInfo packageInfo = this.mPackageManager.getPackageInfo(str, 0);
            return (packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null || (applicationInfo.flags & 1) == 0) ? false : true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_horizon_service_ULUNDERSCORE_media_vrcast_VrCastServicePackageVerifier_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(352, r2);
    }

    @AutoGeneratedAccessMethod
    public static final VrCastServicePackageVerifier _UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_vrcast_VrCastServicePackageVerifier_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (VrCastServicePackageVerifier) AnonymousClass117.A00(352, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final VrCastServicePackageVerifier _UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_vrcast_VrCastServicePackageVerifier_ULSEP_FACTORY_METHOD(AbstractC06640p5 r1) {
        return new VrCastServicePackageVerifier(r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_horizon_service_ULUNDERSCORE_media_vrcast_VrCastServicePackageVerifier_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(352, r2);
    }

    public boolean isVrCastServiceApp(@Nullable String str) {
        if (!"com.oculus.vrcast".equals(str)) {
            return false;
        }
        return isSystemApp(str);
    }

    @Inject
    public VrCastServicePackageVerifier(AbstractC06640p5 r2) {
        this.mPackageManager = C003809k.A03(r2);
    }
}
