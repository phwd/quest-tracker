package com.oculus.library.security;

import android.content.Context;
import android.content.pm.Signature;
import android.os.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.oxygen.common.verification.TrustedCallerVerifier;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.UL;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.oculus.android.os.internal.UserHandleInternal;
import com.oculus.android.os.internal.inject.InternalModule;
import com.oculus.common.build.BuildConstants;
import com.oculus.library.security.SecurityModule;
import com.oculus.signature.SignatureHelper;
import com.oculus.unlockulus_helper.UnlockulusHelper;
import com.oculus.unlockulus_helper.inject.UnlockulusModule;
import com.oculus.util.constants.OculusConstants;
import javax.inject.Inject;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unlockulus_ULUNDERSCORE_helper_UnlockulusHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_android_os_internal_UserHandleInternal_ULSEP_BINDING_ID"})
public class TrustedApplications {
    private static final Multimap<Signature, String> TRUSTED_APPLICATIONS = HashMultimap.create();
    private InjectionContext _UL_mInjectionContext;
    private TrustedCallerVerifier mTrustedCallerVerifier;
    @Inject
    @Eager
    private final UnlockulusHelper mUnlockulusHelper;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_library_security_TrustedApplications_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(SecurityModule.UL_id._UL__ULSEP_com_oculus_library_security_TrustedApplications_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final TrustedApplications _UL__ULSEP_com_oculus_library_security_TrustedApplications_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (TrustedApplications) UL.factorymap.get(SecurityModule.UL_id._UL__ULSEP_com_oculus_library_security_TrustedApplications_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final TrustedApplications _UL__ULSEP_com_oculus_library_security_TrustedApplications_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new TrustedApplications(injectorLike, BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_library_security_TrustedApplications_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(SecurityModule.UL_id._UL__ULSEP_com_oculus_library_security_TrustedApplications_ULSEP_BINDING_ID, injectorLike);
    }

    static {
        TRUSTED_APPLICATIONS.put(SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG, "com.oculus.vrshell.home");
        TRUSTED_APPLICATIONS.put(SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG, "com.oculus.vrshell");
        TRUSTED_APPLICATIONS.put(SignatureHelper.OCULUS_PROD_RELEASE_SIGNATURE, "com.oculus.horizon");
        TRUSTED_APPLICATIONS.put(SignatureHelper.OCULUS_PROD_RELEASE_SIGNATURE, "com.oculus.ocms");
        TRUSTED_APPLICATIONS.put(SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG, "com.oculus.rooms");
        TRUSTED_APPLICATIONS.put(SignatureHelper.OCULUS_TV_PROD_SIG, "com.oculus.tv");
        TRUSTED_APPLICATIONS.put(SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG, OculusConstants.PACKAGE_NAME_LIVING_ROOM);
        TRUSTED_APPLICATIONS.put(SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG, "com.oculus.socialplatform");
        TRUSTED_APPLICATIONS.put(SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG, "com.oculus.systemutilities");
        TRUSTED_APPLICATIONS.put(SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG, "com.oculus.systemdriver");
        TRUSTED_APPLICATIONS.put(SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG, "com.oculus.explore");
        TRUSTED_APPLICATIONS.put(SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG, "com.oculus.store");
        TRUSTED_APPLICATIONS.put(SignatureHelper.QUEST_PLATFORM_RELEASE_SIGNATURE, "com.oculus.companion.server");
        TRUSTED_APPLICATIONS.put(SignatureHelper.GO_PLATFORM_RELEASE_SIGNATURE, "com.oculus.companion.server");
        TRUSTED_APPLICATIONS.put(SignatureHelper.MIRAMAR_PLATFORM_RELEASE_SIGNATURE, "com.oculus.companion.server");
        TRUSTED_APPLICATIONS.put(SignatureHelper.QUEST_PLATFORM_RELEASE_SIGNATURE, OculusConstants.PACKAGE_NAME_UNIFIED_TELEMETRY);
        TRUSTED_APPLICATIONS.put(SignatureHelper.GO_PLATFORM_RELEASE_SIGNATURE, OculusConstants.PACKAGE_NAME_UNIFIED_TELEMETRY);
        TRUSTED_APPLICATIONS.put(SignatureHelper.MIRAMAR_PLATFORM_RELEASE_SIGNATURE, OculusConstants.PACKAGE_NAME_UNIFIED_TELEMETRY);
        if (BuildConstants.DEBUG) {
            TRUSTED_APPLICATIONS.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, "com.oculus.vrshell.home");
            TRUSTED_APPLICATIONS.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, "com.oculus.vrshell");
            TRUSTED_APPLICATIONS.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, "com.oculus.horizon");
            TRUSTED_APPLICATIONS.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, "com.oculus.ocms");
            TRUSTED_APPLICATIONS.put(SignatureHelper.OCULUS_FIRST_PARTY_DEBUG_SIG, "com.oculus.rooms");
            TRUSTED_APPLICATIONS.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, "com.oculus.tv");
            TRUSTED_APPLICATIONS.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, OculusConstants.PACKAGE_NAME_LIVING_ROOM);
            TRUSTED_APPLICATIONS.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, "com.oculus.socialplatform");
            TRUSTED_APPLICATIONS.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, "com.oculus.systemutilities");
            TRUSTED_APPLICATIONS.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, "com.oculus.systemdriver");
            TRUSTED_APPLICATIONS.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, "com.oculus.explore");
            TRUSTED_APPLICATIONS.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, "com.oculus.store");
            TRUSTED_APPLICATIONS.put(SignatureHelper.STANDALONE_PLATFORM_DEBUG_SIGNATURE, "com.oculus.companion.server");
            TRUSTED_APPLICATIONS.put(SignatureHelper.STANDALONE_PLATFORM_DEBUG_SIGNATURE, OculusConstants.PACKAGE_NAME_UNIFIED_TELEMETRY);
        }
    }

    @Inject
    public TrustedApplications(InjectorLike injectorLike, @UnsafeContextInjection Context context) {
        this._UL_mInjectionContext = new InjectionContext(1, injectorLike);
        this.mUnlockulusHelper = UnlockulusModule._UL__ULSEP_com_oculus_unlockulus_ULUNDERSCORE_helper_UnlockulusHelper_ULSEP_ACCESS_METHOD(injectorLike);
        this.mTrustedCallerVerifier = new TrustedCallerVerifier(TRUSTED_APPLICATIONS, context.getPackageManager());
    }

    public boolean isTrusted() {
        return isCallingAppSystem() || this.mTrustedCallerVerifier.checkTrustedCaller();
    }

    public boolean isCallingAppSystem() {
        return ((UserHandleInternal) FbInjector.lazyInstance(0, InternalModule.UL_id._UL__ULSEP_com_oculus_android_os_internal_UserHandleInternal_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getAppId(Binder.getCallingUid()) == 1000;
    }
}
