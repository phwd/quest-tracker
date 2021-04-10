package com.oculus.alpenglow.lockscreen;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import X.C04910hv;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.UserManager;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.config.ConfigChangeListener;
import com.oculus.alpenglow.config.Device;
import com.oculus.alpenglow.constants.Constants;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusLockScreenPinType;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
public class Lockscreen implements ConfigChangeListener {
    public static final String LAST_UNIQUE_TOKEN = "last_unique_token";
    public static final String PREFERENCES_NAME = "lockscreen";
    public static final String TAG = "EnterpriseServer.Lockscreen";
    public static volatile Lockscreen _UL__ULSEP_com_oculus_alpenglow_lockscreen_Lockscreen_ULSEP_INSTANCE;
    public AnonymousClass0R7 _UL_mInjectionContext;
    @Nullable
    public String mLastUniqueToken = null;

    @Override // com.oculus.alpenglow.config.ConfigChangeListener
    public final void A5z(Device device, Device device2) {
        String str;
        Device.ManagementInfo.DeviceConfig A3Q;
        Device.ManagementInfo.DeviceConfig.OsConfig A4D;
        if (device2 != null) {
            UserManager userManager = (UserManager) ((Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext)).getSystemService(UserManager.class);
            KeyguardManager keyguardManager = (KeyguardManager) ((Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext)).getSystemService(KeyguardManager.class);
            if (userManager == null || !userManager.isUserUnlocked() || keyguardManager.isDeviceLocked()) {
                AnonymousClass0NK.A01(TAG, "can't change lockscreen config until the user unlocks the lockscreen");
                return;
            }
            Device.ManagementInfo A3y = device2.A3y();
            Device.ManagementInfo.DeviceConfig.OsConfig.LockScreen lockScreen = null;
            if (A3y == null || (A3Q = A3y.A3Q()) == null || (A4D = A3Q.A4D()) == null) {
                str = null;
            } else {
                str = A4D.A4j();
                lockScreen = A4D.A3w();
            }
            String string = ((Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext)).getSharedPreferences(PREFERENCES_NAME, 0).getString(LAST_UNIQUE_TOKEN, null);
            this.mLastUniqueToken = string;
            if (lockScreen == null) {
                return;
            }
            if (string == null || !string.equals(str)) {
                GraphQLHWMOculusLockScreenPinType A4m = lockScreen.A4m();
                if (A4m == null) {
                    AnonymousClass0NK.A02(TAG, "missing unlock type");
                } else {
                    Intent intent = new Intent();
                    intent.setComponent(ComponentName.createRelative("com.oculus.companion.server", Constants.COMPANION_SERVER_SERVICE));
                    intent.setAction(Constants.COMPANION_ACTION_PIN_RESET);
                    C04910hv.A00().A03().A00(intent, (Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext));
                    if (A4m != GraphQLHWMOculusLockScreenPinType.NONE) {
                        Intent intent2 = new Intent();
                        intent2.setComponent(ComponentName.createRelative("com.oculus.companion.server", Constants.COMPANION_SERVER_SERVICE));
                        intent2.setAction(Constants.COMPANION_ACTION_PIN_SET);
                        intent2.putExtra(Constants.COMPANION_EXTRA_LOCK_METHOD, A4m.toString());
                        String A4l = lockScreen.A4l();
                        if (A4l == null) {
                            A4l = "";
                        }
                        intent2.putExtra(Constants.COMPANION_EXTRA_PIN, A4l);
                        C04910hv.A00().A03().A00(intent2, (Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext));
                    }
                }
                this.mLastUniqueToken = str;
                SharedPreferences sharedPreferences = ((Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext)).getSharedPreferences(PREFERENCES_NAME, 0);
                if (this.mLastUniqueToken != null) {
                    sharedPreferences.edit().putString(LAST_UNIQUE_TOKEN, this.mLastUniqueToken).apply();
                }
            }
        }
    }

    @Inject
    public Lockscreen(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(1, r3);
    }
}
