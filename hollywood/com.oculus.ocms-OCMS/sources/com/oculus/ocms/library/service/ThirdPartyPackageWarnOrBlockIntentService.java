package com.oculus.ocms.library.service;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.mobileconfig.factory.MobileConfig;
import com.facebook.mobileconfig.factory.module.MobileConfigFactoryModule;
import com.facebook.secure.switchoff.DefaultSwitchOffs;
import com.facebook.ultralight.UL;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.ocms.library.provider.contract.TrustedBinaryContract;
import com.oculus.ocms.library.service.MC;
import com.oculus.security.basecomponent.OculusPublicIntentService;
import com.oculus.userserver.api.OculusUserManager;
import com.oculus.userserver.api.user.OculusUser;
import java.util.ArrayList;
import java.util.List;

public class ThirdPartyPackageWarnOrBlockIntentService extends OculusPublicIntentService {
    private static final boolean DEBUG = false;
    private static final String EVENT_SUBTYPE = "event_subtype";
    private static final String PACKAGE_NAME = "package_name";
    private static final String TAG = "ThirdPartyPackageWarnOrBlockIntentService";
    private static final String TELEMETRY_EVENT_NAME = "oculus_mobile_third_party_package_started";
    private InjectionContext _UL_mInjectionContext;
    private OculusUserManager mUserManager;

    private static final void _UL_injectMe(Context context, ThirdPartyPackageWarnOrBlockIntentService thirdPartyPackageWarnOrBlockIntentService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe((InjectorLike) FbInjector.get(context), thirdPartyPackageWarnOrBlockIntentService);
        } else {
            FbInjector.injectMe(ThirdPartyPackageWarnOrBlockIntentService.class, thirdPartyPackageWarnOrBlockIntentService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, ThirdPartyPackageWarnOrBlockIntentService thirdPartyPackageWarnOrBlockIntentService) {
        thirdPartyPackageWarnOrBlockIntentService._UL_mInjectionContext = new InjectionContext(2, injectorLike);
    }

    public ThirdPartyPackageWarnOrBlockIntentService() {
        super(ThirdPartyPackageWarnOrBlockIntentService.class.getSimpleName());
    }

    @Override // com.facebook.secure.service.PublicBaseIntentServiceWithSwitchOff, com.oculus.security.basecomponent.OculusPublicIntentService
    public void onCreate() {
        super.onCreate();
        _UL_injectMe((Context) this, this);
        this.mUserManager = new OculusUserManager(this);
    }

    public void onDestroy() {
        OculusUserManager oculusUserManager = this.mUserManager;
        if (oculusUserManager != null) {
            oculusUserManager.close();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.service.PublicBaseIntentServiceWithSwitchOff
    public void onSecuredHandleIntent(@Nullable Intent intent) {
        if (intent != null && DefaultSwitchOffs.general().check(this, this, intent)) {
            String stringExtra = intent.getStringExtra("package_name");
            if (!TextUtils.isEmpty(stringExtra)) {
                if (((MobileConfig) FbInjector.lazyInstance(1, MobileConfigFactoryModule.UL_id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getBoolean(MC.oculus_library.enable_untrusted_binary_launch_block)) {
                    List<OculusUser> suspendedUsers = getSuspendedUsers();
                    if (!suspendedUsers.isEmpty()) {
                        ((ActivityManager) getSystemService("activity")).killBackgroundProcesses(stringExtra);
                        TrustedBinaryContract.showUnofficialAppDialog(this, stringExtra, TrustedBinaryContract.ACTION_BLOCK, getAliasesFromUsers(suspendedUsers));
                        getEventBuilder(stringExtra, "gk_pass_block").logAndRelease();
                        return;
                    }
                }
                if (((MobileConfig) FbInjector.lazyInstance(1, MobileConfigFactoryModule.UL_id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getBoolean(MC.oculus_library.enable_untrusted_binary_launch_warning)) {
                    TrustedBinaryContract.showUnofficialAppDialog(this, stringExtra, TrustedBinaryContract.ACTION_WARN, null);
                    getEventBuilder(stringExtra, "gk_pass_warn").logAndRelease();
                    return;
                }
                getEventBuilder(stringExtra, "gk_fail_warn").logAndRelease();
            }
        }
    }

    private Event getEventBuilder(String str, String str2) {
        return ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(TELEMETRY_EVENT_NAME).addExtra("package_name", str).addExtra(EVENT_SUBTYPE, str2);
    }

    private List<OculusUser> getSuspendedUsers() {
        ArrayList arrayList = new ArrayList();
        try {
            for (OculusUser oculusUser : this.mUserManager.getUsers()) {
                if (oculusUser.isSuspended()) {
                    arrayList.add(oculusUser);
                }
            }
        } catch (RemoteException e) {
            getEventBuilder("", "get_users_failed").logAndRelease();
            BLog.e(TAG, "Get users failed", e);
        }
        return arrayList;
    }

    private static List<String> getAliasesFromUsers(List<OculusUser> list) {
        ArrayList arrayList = new ArrayList();
        for (OculusUser oculusUser : list) {
            arrayList.add(oculusUser.getUserName());
        }
        return arrayList;
    }
}
