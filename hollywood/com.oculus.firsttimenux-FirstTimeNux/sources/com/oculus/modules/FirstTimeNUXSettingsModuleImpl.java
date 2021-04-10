package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import com.oculus.modules.codegen.FirstTimeNUXSettingsModule;
import com.oculus.secure.trustedapp.CallerInfoHelper;

public class FirstTimeNUXSettingsModuleImpl extends FirstTimeNUXSettingsModule {
    private static final String SET_FIRST_TIME_NUX_ALLOW_GUARDIAN_ACTION = "set_first_time_nux_allow_guardian";
    private static final String SET_FIRST_TIME_NUX_COMPLETE_ACTION = "set_first_time_nux_complete";
    private static final String SET_FIRST_TIME_NUX_HEALTH_SAFETY_COMPLETE_ACTION = "set_first_time_nux_health_safety_complete";
    private static final String SYSTEM_UTILITIES_PACKAGE_NAME = "com.oculus.systemutilities";
    private static final String TAG = FirstTimeNUXSettingsModuleImpl.class.getSimpleName();
    private Context mContext;

    public FirstTimeNUXSettingsModuleImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.oculus.modules.codegen.FirstTimeNUXSettingsModule
    public void markAllowGuardianImpl() {
        Intent intent = new Intent(SET_FIRST_TIME_NUX_ALLOW_GUARDIAN_ACTION);
        intent.setPackage("com.oculus.systemutilities");
        CallerInfoHelper.attachCallerInfo(intent, this.mContext, TAG + ":markAllowGuardianImpl()");
        this.mContext.sendBroadcast(intent);
    }

    @Override // com.oculus.modules.codegen.FirstTimeNUXSettingsModule
    public void markFirstTimeNUXCompleteImpl() {
        Intent intent = new Intent(SET_FIRST_TIME_NUX_COMPLETE_ACTION);
        intent.setPackage("com.oculus.systemutilities");
        CallerInfoHelper.attachCallerInfo(intent, this.mContext, TAG + ":markFirstTimeNUXCompleteImpl()");
        this.mContext.sendBroadcast(intent);
    }

    @Override // com.oculus.modules.codegen.FirstTimeNUXSettingsModule
    public void markHealthSafetyCompleteImpl() {
        Intent intent = new Intent(SET_FIRST_TIME_NUX_HEALTH_SAFETY_COMPLETE_ACTION);
        intent.setPackage("com.oculus.systemutilities");
        CallerInfoHelper.attachCallerInfo(intent, this.mContext, TAG + ":markHealthSafetyCompleteImpl()");
        this.mContext.sendBroadcast(intent);
    }
}
