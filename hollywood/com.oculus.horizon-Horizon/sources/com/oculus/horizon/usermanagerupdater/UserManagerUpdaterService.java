package com.oculus.horizon.usermanagerupdater;

import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import com.oculus.security.basecomponent.OculusPublicIntentService;

public class UserManagerUpdaterService extends OculusPublicIntentService {
    public static final String ACTION_UPDATE_USER_MANAGER = "UPDATE_USER_MANAGER";
    public static final String TAG = "UserManagerUpdaterService";
    public AnonymousClass0QC _UL_mInjectionContext;

    public UserManagerUpdaterService() {
        super(TAG);
    }

    @Override // X.AnonymousClass1U8, com.oculus.security.basecomponent.OculusPublicIntentService
    public final void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new AnonymousClass0QC(1, AnonymousClass0J2.get(this));
    }
}
