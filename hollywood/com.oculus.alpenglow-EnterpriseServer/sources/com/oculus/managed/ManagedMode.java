package com.oculus.managed;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0R7;
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
    public AnonymousClass0R7 _UL_mInjectionContext;
    public boolean isEnterpriseModeEnabled;

    @Inject
    public ManagedMode(AbstractC02990bJ r6) {
        boolean z = true;
        AnonymousClass0R7 r1 = new AnonymousClass0R7(1, r6);
        this._UL_mInjectionContext = r1;
        this.isEnterpriseModeEnabled = ((SettingsManager) AnonymousClass0Lh.A03(0, 105, r1)).getInt("managed_device", 0) != 2 ? false : z;
        ((SettingsManager) AnonymousClass0Lh.A03(0, 105, this._UL_mInjectionContext)).registerSettingsObserver("managed_device", new SettingsObserverCallback() {
            /* class com.oculus.managed.ManagedMode.AnonymousClass1 */

            public void onSettingChange(String str) {
                if ("managed_device".equals(str)) {
                    ManagedMode managedMode = ManagedMode.this;
                    boolean z = false;
                    if (((SettingsManager) AnonymousClass0Lh.A03(0, 105, managedMode._UL_mInjectionContext)).getInt("managed_device", 0) == 2) {
                        z = true;
                    }
                    managedMode.isEnterpriseModeEnabled = z;
                }
            }
        }, new Handler(Looper.getMainLooper()));
    }
}
