package com.oculus.managed;

import X.AbstractC03180ld;
import X.AnonymousClass0Qj;
import X.AnonymousClass0RE;
import X.AnonymousClass0VB;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import android.os.Handler;
import android.os.Looper;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID"})
@ApplicationScoped
public class ManagedMode {
    public static volatile ManagedMode _UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_INSTANCE;
    public AnonymousClass0RE _UL_mInjectionContext;
    public boolean isEnterpriseModeEnabled;

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_managed_ManagedMode_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(82, r2);
    }

    @AutoGeneratedAccessMethod
    public static final ManagedMode _UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (ManagedMode) AnonymousClass1TK.A00(82, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final ManagedMode _UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_FACTORY_METHOD(AnonymousClass0lg r4, Object obj) {
        if (_UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_INSTANCE == null) {
            synchronized (ManagedMode.class) {
                AnonymousClass0Qj A00 = AnonymousClass0Qj.A00(_UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_INSTANCE, r4);
                if (A00 != null) {
                    try {
                        _UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_INSTANCE = new ManagedMode(r4.getApplicationInjector());
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_managed_ManagedMode_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(82, r2);
    }

    @Inject
    public ManagedMode(AnonymousClass0lg r6) {
        boolean z = true;
        AnonymousClass0RE r1 = new AnonymousClass0RE(1, r6);
        this._UL_mInjectionContext = r1;
        this.isEnterpriseModeEnabled = ((SettingsManager) AnonymousClass0VF.A03(0, 93, r1)).getInt("managed_device", 0) != 2 ? false : z;
        ((SettingsManager) AnonymousClass0VF.A03(0, 93, this._UL_mInjectionContext)).registerSettingsObserver("managed_device", new SettingsObserverCallback() {
            /* class com.oculus.managed.ManagedMode.AnonymousClass1 */

            public void onSettingChange(String str) {
                if ("managed_device".equals(str)) {
                    ManagedMode managedMode = ManagedMode.this;
                    boolean z = false;
                    if (((SettingsManager) AnonymousClass0VF.A03(0, 93, managedMode._UL_mInjectionContext)).getInt("managed_device", 0) == 2) {
                        z = true;
                    }
                    managedMode.isEnterpriseModeEnabled = z;
                }
            }
        }, new Handler(Looper.getMainLooper()));
    }

    public boolean isEnterpriseModeEnabled() {
        return this.isEnterpriseModeEnabled;
    }
}