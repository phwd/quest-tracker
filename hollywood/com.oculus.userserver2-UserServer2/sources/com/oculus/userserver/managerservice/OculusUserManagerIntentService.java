package com.oculus.userserver.managerservice;

import X.BZ;
import X.Om;
import com.oculus.security.basecomponent.OculusPublicIntentService;

public class OculusUserManagerIntentService extends OculusPublicIntentService {
    public static final String ACTION_CREATE_USER_AND_SWITCH = "com.oculus.userserver.CREATE_USER_AND_SWITCH";
    public static final String ACTION_REMOVE_USER = "com.oculus.userserver.REMOVE_USER";
    public static final String EXTRA_USER_ID = "user_id";
    public static final String TAG = "OculusUserManagerIntentService";
    public Om _UL_mInjectionContext;

    @Override // com.oculus.security.basecomponent.OculusPublicIntentService, X.fB
    public final void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new Om(1, BZ.get(this));
    }
}
