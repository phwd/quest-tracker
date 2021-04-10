package com.oculus.alpenglow.os;

import X.AbstractC02990bJ;
import X.AnonymousClass006;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import X.AnonymousClass0Sp;
import X.AnonymousClass0Sq;
import X.C04910hv;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.android.DevicePolicyManagerWrapper;
import com.oculus.alpenglow.config.ConfigChangeListener;
import com.oculus.alpenglow.config.Device;
import com.oculus.alpenglow.constants.Constants;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusOSDeviceAdmin;
import com.oculus.alpenglow.logging.OSUpdateLogger;

@Dependencies({"_UL__ULSEP_com_oculus_alpenglow_os_DeviceAdmin_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_logging_OSUpdateLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_os_ScheduleUpdate_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_secure_context_SecureContextHelper_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
public class OsConfigChangeListener implements ConfigChangeListener {
    public static final String TAG = "EnterpriseServer.OsConfigChangeListener";
    public static volatile OsConfigChangeListener _UL__ULSEP_com_oculus_alpenglow_os_OsConfigChangeListener_ULSEP_INSTANCE;
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Override // com.oculus.alpenglow.config.ConfigChangeListener
    public final void A5z(Device device, Device device2) {
        Device.ManagementInfo.DeviceConfig.OsConfig osConfig;
        String str;
        String str2;
        ComponentName componentName;
        Device.ManagementInfo A3y;
        Device.ManagementInfo.DeviceConfig A3Q;
        Device.ManagementInfo A3y2;
        Device.ManagementInfo.DeviceConfig A3Q2;
        Device.ManagementInfo.DeviceConfig.OsConfig osConfig2 = null;
        if (device2 == null || (A3y2 = device2.A3y()) == null || (A3Q2 = A3y2.A3Q()) == null) {
            osConfig = null;
        } else {
            osConfig = A3Q2.A4D();
        }
        if (!(device == null || (A3y = device.A3y()) == null || (A3Q = A3y.A3Q()) == null)) {
            osConfig2 = A3Q.A4D();
        }
        if (osConfig != null) {
            String A4e = osConfig.A4e();
            if (!TextUtils.isEmpty(A4e)) {
                AnonymousClass0NK.A06(TAG, "setting timezone to %s", A4e);
                Intent intent = new Intent();
                intent.setComponent(ComponentName.createRelative("com.oculus.companion.server", Constants.COMPANION_SERVER_SERVICE));
                intent.setAction(Constants.COMPANION_ACTION_TIME_ZONE);
                intent.putExtra(Constants.COMPANION_EXTRA_TIME_ZONE_ID, A4e);
                ((C04910hv) AnonymousClass0Lh.A03(4, 15, this._UL_mInjectionContext)).A03().A00(intent, (Context) AnonymousClass0Lh.A03(5, 4, this._UL_mInjectionContext));
            }
            if (!osConfig.equals(osConfig2)) {
                boolean A32 = osConfig.A32();
                ((ScheduleUpdate) AnonymousClass0Lh.A03(2, 33, this._UL_mInjectionContext)).A01(A32);
                if (!A32) {
                    ((OSUpdateLogger) AnonymousClass0Lh.A03(1, 81, this._UL_mInjectionContext)).A01(false, false, null);
                } else if (((AnonymousClass0Sp) AnonymousClass0Lh.A03(3, 116, this._UL_mInjectionContext)).A38(36310271995740161L, AnonymousClass0Sq.A03)) {
                    ((OSUpdateLogger) AnonymousClass0Lh.A03(1, 81, this._UL_mInjectionContext)).A01(true, false, "feature disabled");
                } else if (((ScheduleUpdate) AnonymousClass0Lh.A03(2, 33, this._UL_mInjectionContext)).A02(osConfig)) {
                    ((ScheduleUpdate) AnonymousClass0Lh.A03(2, 33, this._UL_mInjectionContext)).A01(false);
                }
            }
            DeviceAdmin deviceAdmin = (DeviceAdmin) AnonymousClass0Lh.A03(0, 67, this._UL_mInjectionContext);
            GraphQLHWMOculusOSDeviceAdmin A3O = osConfig.A3O();
            if (A3O == GraphQLHWMOculusOSDeviceAdmin.AIRWATCH) {
                componentName = DeviceAdmin.AIRWATCH;
            } else if (A3O == GraphQLHWMOculusOSDeviceAdmin.MOBILE_IRON) {
                componentName = DeviceAdmin.MOBILE_IRON;
            } else {
                str = DeviceAdmin.TAG;
                str2 = "No device admin requested.";
                AnonymousClass0NK.A01(str, str2);
            }
            AnonymousClass0NK.A06(DeviceAdmin.TAG, "Attempting to set %s as the device owner.", componentName.getPackageName());
            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) ((Context) AnonymousClass0Lh.A03(0, 4, deviceAdmin._UL_mInjectionContext)).getSystemService("device_policy");
            if (devicePolicyManager == null) {
                str = DeviceAdmin.TAG;
                str2 = "Failed to access the DevicePolicyManager service.";
            } else if (devicePolicyManager.isDeviceOwnerApp(componentName.getPackageName())) {
                str = DeviceAdmin.TAG;
                str2 = "MDM already set as device owner. Exiting early.";
            } else {
                DevicePolicyManagerWrapper.A00(devicePolicyManager, componentName);
                try {
                    Runtime.getRuntime().exec(AnonymousClass006.A05("dpm set-device-owner ", componentName.flattenToString()));
                    return;
                } catch (Exception e) {
                    AnonymousClass0NK.A04(DeviceAdmin.TAG, "Failed to set MDM as device owner.", e);
                    devicePolicyManager.removeActiveAdmin(componentName);
                    return;
                }
            }
            AnonymousClass0NK.A01(str, str2);
        }
    }

    @Inject
    public OsConfigChangeListener(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(6, r3);
    }
}
