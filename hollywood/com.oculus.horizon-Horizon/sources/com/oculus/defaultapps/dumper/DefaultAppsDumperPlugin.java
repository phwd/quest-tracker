package com.oculus.defaultapps.dumper;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import X.AnonymousClass0dM;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_defaultapps_DefaultAppsPrefs_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_defaultapps_DefaultAppsSetupListener_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class DefaultAppsDumperPlugin implements AnonymousClass0dM {
    public static final String CMD_INSTALL = "install";
    public static final String CMD_LIST_APP_IDS = "list";
    public static final String NAME = "setupinstall";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public DefaultAppsDumperPlugin(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r3);
    }
}
