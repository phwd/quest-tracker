package com.oculus.managed;

import X.BZ;
import X.Om;
import X.SZ;
import android.os.Handler;
import android.os.Looper;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;

@Dependencies({"_UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID"})
@ApplicationScoped
public class ManagedMode {
    public static volatile ManagedMode _UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_INSTANCE;
    public Om _UL_mInjectionContext;
    public boolean isEnterpriseModeEnabled;

    @Inject
    public ManagedMode(SZ sz) {
        boolean z = true;
        Om om = new Om(1, sz);
        this._UL_mInjectionContext = om;
        this.isEnterpriseModeEnabled = ((SettingsManager) BZ.A02(0, 45, om)).getInt("managed_device", 0) != 2 ? false : z;
        ((SettingsManager) BZ.A02(0, 45, this._UL_mInjectionContext)).registerSettingsObserver("managed_device", new SettingsObserverCallback() {
            /* class com.oculus.managed.ManagedMode.AnonymousClass1 */

            public void onSettingChange(String str) {
                if ("managed_device".equals(str)) {
                    ManagedMode managedMode = ManagedMode.this;
                    boolean z = false;
                    if (((SettingsManager) BZ.A02(0, 45, managedMode._UL_mInjectionContext)).getInt("managed_device", 0) == 2) {
                        z = true;
                    }
                    managedMode.isEnterpriseModeEnabled = z;
                }
            }
        }, new Handler(Looper.getMainLooper()));
    }
}
