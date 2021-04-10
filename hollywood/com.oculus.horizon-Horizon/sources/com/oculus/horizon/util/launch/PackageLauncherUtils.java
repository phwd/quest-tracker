package com.oculus.horizon.util.launch;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import X.C003809k;
import android.content.pm.PackageManager;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.oculus.horizon.logging.OculusLogger;
import com.oculus.horizon.profile.UserProfileHelper;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_profile_UserProfileHelper_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
public class PackageLauncherUtils {
    public static final String ACTION_APP_LAUNCH = "app_launch";
    public static final String EXTRA_LAUNCH_PACKAGE_ON_START = "extra_launch_package_on_start";
    public static final String SHELL_META_LAUNCHER_CAPABILITY = "com.oculus.vrshell.central_launcher";
    public static final Class<?> TAG = PackageLauncherUtils.class;
    public static volatile PackageLauncherUtils _UL__ULSEP_com_oculus_horizon_util_launch_PackageLauncherUtils_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final OVRLibrary mLibrary;
    @Inject
    @Eager
    public final OculusLogger mOculusLogger;
    @Inject
    @Eager
    public final PackageManager mPackageManager;
    @Inject
    @Eager
    public final UserProfileHelper mUserProfileHelper;

    @Inject
    public PackageLauncherUtils(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mLibrary = OVRLibraryModule.A00(r3);
        this.mOculusLogger = (OculusLogger) AnonymousClass117.A00(574, r3);
        this.mUserProfileHelper = (UserProfileHelper) AnonymousClass117.A00(68, r3);
        this.mPackageManager = C003809k.A03(r3);
    }
}
