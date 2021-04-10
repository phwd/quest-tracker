package com.oculus.userserver.managerservice;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_oculus_userserver_managerservice_ForDeviceProtectedStorage_ULSEP_BINDING_ID"})
public class SharingStore {
    public static final String KEY_SHARING_ENABLED = "sharing_enabled";
    public static final String PREFS_NAME = "oculus_sharing_store";
    public final SharedPreferences mPrefs;

    @Inject
    public SharingStore(@ForDeviceProtectedStorage Context context) {
        this.mPrefs = context.getSharedPreferences(PREFS_NAME, 0);
    }
}
