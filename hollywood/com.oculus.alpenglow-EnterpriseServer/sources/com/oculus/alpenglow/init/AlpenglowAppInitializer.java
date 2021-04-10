package com.oculus.alpenglow.init;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0R7;
import android.content.Context;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.lifecycle.StartupListener;
import com.oculus.mobileconfig.updater.MobileConfigUpdaterInit;

@Dependencies({"_UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsHighPriorityInit_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsInitInEnterpriseMode_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_updater_MobileConfigUpdaterInit_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
public class AlpenglowAppInitializer implements StartupListener {
    public static final String STETHO_INIT = "StethoInit";
    public static final String TAG = "EnterpriseServer.AlpenglowAppInitializer";
    public static volatile AlpenglowAppInitializer _UL__ULSEP_com_oculus_alpenglow_init_AlpenglowAppInitializer_ULSEP_INSTANCE;
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Override // com.oculus.alpenglow.lifecycle.StartupListener
    public final void A6b(Context context) {
        ((MobileConfigUpdaterInit) AnonymousClass0Lh.A03(5, 2, this._UL_mInjectionContext)).A5C();
    }

    @Inject
    public AlpenglowAppInitializer(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(7, r3);
    }
}
