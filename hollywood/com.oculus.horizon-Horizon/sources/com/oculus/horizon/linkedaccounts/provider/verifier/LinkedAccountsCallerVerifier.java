package com.oculus.horizon.linkedaccounts.provider.verifier;

import X.AbstractC06640p5;
import X.AbstractC07090r4;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import X.AnonymousClass1Ch;
import X.C003809k;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.collect.HashMultimap;
import com.oculus.signature.SignatureHelper;
import com.oculus.unlockulus_helper.UnlockulusHelper;
import com.oculus.util.device.DeviceUtils;

@Dependencies({"_UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unlockulus_ULUNDERSCORE_helper_UnlockulusHelper_ULSEP_BINDING_ID"})
public class LinkedAccountsCallerVerifier {
    public static final String PACKAGE_NAME_FACEBOOK_AKIRA = "com.facebook.akira";
    public static final String PACKAGE_NAME_FACEBOOK_DAYKIRA = "com.facebook.daykira";
    public static final String PACKAGE_NAME_FACEBOOK_SNAPVR = "com.facebook.snapvr";
    public static final String PACKAGE_NAME_FACEBOOK_TV = "com.facebook.tv";
    public static final String PACKAGE_NAME_FACEBOOK_TV_DEBUG = "test.com.facebook.tv.msbkgs";
    public static final String PACKAGE_NAME_MEMORY_MACHINE_2D = "com.facebook.together.memorymachine2d";
    public static final String PACKAGE_NAME_OCULUS_360_PHOTO = "com.oculus.oculus360photos";
    public static final String PACKAGE_NAME_OCULUS_BROWSER = "com.oculus.browser";
    public static final String PACKAGE_NAME_OCULUS_EXPLORE = "com.oculus.explore";
    public static final String PACKAGE_NAME_OCULUS_MEDIA_GALLERY = "com.oculus.mediagallery";
    public static final String PACKAGE_NAME_OCULUS_ROOMS = "com.oculus.rooms";
    public static final String PACKAGE_NAME_OCULUS_SHELL_HOME = "com.oculus.vrshell.home";
    public static final String PACKAGE_NAME_OCULUS_SOCIAL_PLATFORM = "com.oculus.socialplatform";
    public static final String PACKAGE_NAME_OCULUS_STORE = "com.oculus.store";
    public static final String PACKAGE_NAME_OCULUS_SYSTEMUX = "com.oculus.systemux";
    public static final String PACKAGE_NAME_OCULUS_TOGETHER = "com.facebook.together.together";
    public static final String PACKAGE_NAME_OCULUS_TOGETHER_DEMO = "com.facebook.together.together.demo";
    public static final String PACKAGE_NAME_OCULUS_TOGETHER_DEV = "com.facebook.together.together.dev";
    public static final String PACKAGE_NAME_OCULUS_TOGETHER_MAIN_LKG = "com.facebook.together.together.main_lkg";
    public static final String PACKAGE_NAME_OCULUS_TOGETHER_MAIN_NIGHTLY = "com.facebook.together.together.main_nightly";
    public static final String PACKAGE_NAME_OCULUS_TOGETHER_MAIN_STABLE = "com.facebook.together.together.main_stable";
    public static final String PACKAGE_NAME_OCULUS_TOGETHER_MAIN_TEST = "com.facebook.together.together.main_test";
    public static final String PACKAGE_NAME_OCULUS_TOGETHER_PLAYTEST_PRIME = "com.facebook.together.together.playtest_prime";
    public static final String PACKAGE_NAME_OCULUS_TOGETHER_PLAYTEST_QA = "com.facebook.together.together.playtest_qa";
    public static final String PACKAGE_NAME_OCULUS_TOGETHER_RELEASE_LIVE = "com.facebook.horizon";
    public static final String PACKAGE_NAME_OCULUS_TOGETHER_RELEASE_LKG = "com.facebook.together.together.release_lkg";
    public static final String PACKAGE_NAME_OCULUS_TOGETHER_RELEASE_NIGHTLY = "com.facebook.together.together.release_nightly";
    public static final String PACKAGE_NAME_OCULUS_TOGETHER_RELEASE_RC = "com.facebook.together.together.release_rc";
    public static final String PACKAGE_NAME_OCULUS_TOGETHER_STABLE = "com.facebook.together.together.stable";
    public static final String PACKAGE_NAME_OCULUS_TOGETHER_STAGING = "com.facebook.together.together.staging";
    public static final String PACKAGE_NAME_OCULUS_VENUES = "com.oculus.venues";
    public static final String PACKAGE_NAME_OCULUS_VIDEO = "com.oculus.cinema";
    public static final String PACKAGE_NAME_OCULUS_WORKROOMS = "com.facebook.together.workrooms";
    public static final String PACKAGE_NAME_OCULUS_WORKROOMS_DEV = "com.facebook.together.workrooms.dev";
    public static final String PACKAGE_NAME_OCULUS_WORKROOMS_RELEASE = "com.facebook.together.workrooms.release";
    public static final String PACKAGE_NAME_OCULUS_WORKROOMS_STABLE = "com.facebook.together.workrooms.stable";
    public static final String PACKAGE_NAME_OCULUS_WORKROOMS_STAGING = "com.facebook.together.workrooms.staging";
    public static final String PACKAGE_NAME_OCULUS_WORKROOMS_VC = "com.facebook.together.workrooms_vc";
    public static final String PACKAGE_NAME_OCULUS_WORKROOMS_VC_DEV = "com.facebook.together.workrooms_vc.dev";
    public static final String PACKAGE_NAME_VENUES_DEV = "com.facebook.together.venues.dev";
    public static final String PACKAGE_NAME_VENUES_NIGHTLY = "com.facebook.together.venues";
    public static final String PACKAGE_NAME_VENUES_RELEASE = "com.facebook.venues";
    public static final String PACKAGE_NAME_VENUES_STABLE = "com.facebook.together.venues.stable";
    public static final String PACKAGE_NAME_VENUES_STAGING = "com.facebook.together.venues.staging";
    public static final String PACKAGE_NAME_WORKROOMS_EVENTS = "com.oculus.workrooms.events";
    public static final AbstractC07090r4<Signature, String> TRUSTED_READERS = new HashMultimap();
    public static final AbstractC07090r4<Signature, String> TRUSTED_WRITERS = new HashMultimap();
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final DeviceUtils mDeviceUtils;
    @Inject
    @Eager
    public final PackageManager mPackageManager;
    public final AnonymousClass1Ch mTrustedReaderVerifier = new AnonymousClass1Ch(TRUSTED_READERS, ((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext)).getPackageManager());
    public final AnonymousClass1Ch mTrustedWriterVerifier = new AnonymousClass1Ch(TRUSTED_WRITERS, ((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext)).getPackageManager());
    @Inject
    @Eager
    public final UnlockulusHelper mUnlockulusHelper;

    static {
        AbstractC07090r4<Signature, String> r5 = TRUSTED_READERS;
        Signature signature = SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG;
        r5.A7h(signature, PACKAGE_NAME_OCULUS_360_PHOTO);
        r5.A7h(signature, "com.oculus.browser");
        r5.A7h(signature, "com.oculus.explore");
        r5.A7h(signature, "com.oculus.mediagallery");
        r5.A7h(signature, "com.oculus.rooms");
        r5.A7h(signature, "com.oculus.vrshell.home");
        r5.A7h(signature, "com.oculus.socialplatform");
        r5.A7h(signature, "com.oculus.store");
        r5.A7h(SignatureHelper.OCULUS_CORE_RELEASE_SIGNATURE, "com.oculus.systemux");
        Signature signature2 = SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG;
        r5.A7h(signature2, "com.oculus.venues");
        r5.A7h(signature2, PACKAGE_NAME_OCULUS_VIDEO);
        Signature signature3 = SignatureHelper.FBANDROID_FIRST_PARTY_RELEASE_SIG;
        r5.A7h(signature3, PACKAGE_NAME_FACEBOOK_AKIRA);
        r5.A7h(signature3, PACKAGE_NAME_FACEBOOK_DAYKIRA);
        r5.A7h(signature3, PACKAGE_NAME_FACEBOOK_SNAPVR);
        r5.A7h(signature3, PACKAGE_NAME_FACEBOOK_TV);
        Signature signature4 = SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG;
        r5.A7h(signature4, "com.facebook.together.together");
        r5.A7h(signature4, "com.facebook.together.together.dev");
        r5.A7h(signature4, "com.facebook.together.together.stable");
        r5.A7h(signature4, "com.facebook.together.together.staging");
        r5.A7h(signature4, PACKAGE_NAME_WORKROOMS_EVENTS);
        r5.A7h(signature4, PACKAGE_NAME_OCULUS_WORKROOMS);
        r5.A7h(signature4, PACKAGE_NAME_OCULUS_WORKROOMS_DEV);
        r5.A7h(signature4, PACKAGE_NAME_OCULUS_WORKROOMS_STABLE);
        r5.A7h(signature4, PACKAGE_NAME_OCULUS_WORKROOMS_STAGING);
        r5.A7h(signature4, PACKAGE_NAME_OCULUS_WORKROOMS_RELEASE);
        r5.A7h(signature4, PACKAGE_NAME_OCULUS_WORKROOMS_VC);
        r5.A7h(signature4, PACKAGE_NAME_OCULUS_WORKROOMS_VC_DEV);
        r5.A7h(signature4, PACKAGE_NAME_MEMORY_MACHINE_2D);
        r5.A7h(signature4, PACKAGE_NAME_VENUES_RELEASE);
        r5.A7h(signature4, PACKAGE_NAME_VENUES_DEV);
        r5.A7h(signature4, PACKAGE_NAME_VENUES_NIGHTLY);
        r5.A7h(signature4, PACKAGE_NAME_VENUES_STAGING);
        r5.A7h(signature4, PACKAGE_NAME_VENUES_STABLE);
        r5.A7h(signature4, "com.facebook.together.together.main_nightly");
        r5.A7h(signature4, "com.facebook.together.together.main_stable");
        r5.A7h(signature4, "com.facebook.together.together.main_lkg");
        r5.A7h(signature4, "com.facebook.together.together.main_test");
        r5.A7h(signature4, "com.facebook.together.together.playtest_prime");
        r5.A7h(signature4, "com.facebook.together.together.playtest_qa");
        r5.A7h(signature4, "com.facebook.together.together.demo");
        r5.A7h(signature4, "com.facebook.together.together.release_nightly");
        r5.A7h(signature4, "com.facebook.together.together.release_lkg");
        r5.A7h(signature4, "com.facebook.together.together.release_rc");
        r5.A7h(signature4, "com.facebook.horizon");
        AbstractC07090r4<Signature, String> r0 = TRUSTED_WRITERS;
        r0.A7h(signature4, "com.oculus.explore");
        r0.A7h(signature4, "com.oculus.vrshell.home");
        r0.A7h(signature4, "com.oculus.socialplatform");
    }

    public final boolean A00() {
        if (this.mUnlockulusHelper.A00((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext)) || AnonymousClass1Ch.A00(this.mTrustedReaderVerifier).A03) {
            return true;
        }
        return false;
    }

    public final boolean A01(String str) {
        if (!this.mUnlockulusHelper.A00((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext)) && !AnonymousClass1Ch.A00(this.mTrustedWriterVerifier).A03) {
            if (this.mDeviceUtils.A04()) {
                try {
                    if ((this.mPackageManager.getApplicationInfo(str, 0).flags & 1) == 0) {
                        return false;
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            return false;
        }
        return true;
    }

    @Inject
    public LinkedAccountsCallerVerifier(AbstractC06640p5 r5) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r5);
        this.mDeviceUtils = DeviceUtils.A00(r5);
        this.mPackageManager = C003809k.A03(r5);
        this.mUnlockulusHelper = (UnlockulusHelper) AnonymousClass117.A00(296, r5);
    }
}
