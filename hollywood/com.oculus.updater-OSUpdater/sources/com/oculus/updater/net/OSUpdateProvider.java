package com.oculus.updater.net;

import android.text.TextUtils;
import com.facebook.debug.log.BLog;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.common.build.BuildConfig;
import com.oculus.http.core.base.ApiException;
import com.oculus.updater.device.DeviceInfo;
import com.oculus.updater.net.methods.OSReleasesParams;
import com.oculus.updater.net.methods.OSReleasesResponse;
import java.util.Locale;
import javax.annotation.Nullable;

@Dependencies
public class OSUpdateProvider {
    private static final String TAG = "OSUpdateProvider";
    @Inject
    @Eager
    private final ApiDispatcher mApiDispatcher;
    private final DeviceInfo mDeviceInfo;

    @AutoGeneratedFactoryMethod
    public static final OSUpdateProvider _UL__ULSEP_com_oculus_updater_net_OSUpdateProvider_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new OSUpdateProvider(injectorLike, DeviceInfo._UL__ULSEP_com_oculus_updater_device_DeviceInfo_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @Inject
    public OSUpdateProvider(InjectorLike injectorLike, DeviceInfo deviceInfo) {
        this.mApiDispatcher = ApiDispatcher._UL__ULSEP_com_oculus_updater_net_ApiDispatcher_ULSEP_ACCESS_METHOD(injectorLike);
        this.mDeviceInfo = deviceInfo;
    }

    public OSReleasesResponse fetchOSReleases(@Nullable String str, @Nullable String str2, int i, boolean z) throws ApiException {
        return this.mApiDispatcher.getMobileReleaseUpdates(getOSReleasesParams(str, str2, i, z));
    }

    /* access modifiers changed from: package-private */
    public OSReleasesParams getOSReleasesParams(@Nullable String str, @Nullable String str2, int i, boolean z) {
        String graphQl = getGraphQl(z, i);
        if (TextUtils.isEmpty(str)) {
            str = "OC|3733290306686872|";
        }
        if (str2 == null) {
            str2 = BuildConfig.PROVIDER_SUFFIX;
        }
        return new OSReleasesParams(graphQl, "399374017083309", str, str2, i);
    }

    private String getGraphQl(boolean z, int i) {
        String str = "ota." + this.mDeviceInfo.getDeviceType().replace('-', '.').replace('_', '.');
        String deviceSerial = this.mDeviceInfo.getDeviceSerial();
        String l = Long.toString(this.mDeviceInfo.getDeviceVersion());
        int deviceSdkVersion = this.mDeviceInfo.getDeviceSdkVersion();
        String securePatchDate = this.mDeviceInfo.getSecurePatchDate();
        BLog.d(TAG, "Querying for OTA packages, managedMode: %d, deviceType: %s, version: %s, useFullUpdate: %b", Integer.valueOf(i), str, l, Boolean.valueOf(z));
        String str2 = "undefined";
        if (z) {
            Locale locale = null;
            Object[] objArr = new Object[6];
            objArr[0] = str;
            objArr[1] = deviceSerial;
            objArr[2] = Integer.valueOf(deviceSdkVersion);
            objArr[3] = l;
            objArr[4] = "FullOnly";
            if (!TextUtils.isEmpty(securePatchDate)) {
                str2 = securePatchDate;
            }
            objArr[5] = str2;
            return String.format(locale, "update_interval,ota.device_type(%s).device_serial(%s).sdk_version(%d).version(%s).rollout_token(%s).fullbuild(true).security_patch_time(%s){download_uri,target_version,base_version,install_options,file_checksum,release_channel_id,release_channel_name}", objArr);
        }
        Locale locale2 = null;
        Object[] objArr2 = new Object[5];
        objArr2[0] = str;
        objArr2[1] = deviceSerial;
        objArr2[2] = Integer.valueOf(deviceSdkVersion);
        objArr2[3] = l;
        if (!TextUtils.isEmpty(securePatchDate)) {
            str2 = securePatchDate;
        }
        objArr2[4] = str2;
        return String.format(locale2, "update_interval,ota.device_type(%s).device_serial(%s).sdk_version(%d).version(%s).security_patch_time(%s){download_uri,target_version,base_version,install_options,file_checksum,release_channel_id,release_channel_name}", objArr2);
    }
}
