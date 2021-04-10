package com.oculus.modules;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.oculus.device.BuildConfig;
import com.oculus.device.NetworkHeaders;
import com.oculus.modules.codegen.DeviceEnvironmentModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.panellib.AppDetails;
import com.oculus.panellib.SystemProperties;
import com.oculus.util.StringUtil;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceEnvironmentModuleImpl extends DeviceEnvironmentModule {
    private static final String TAG = "DeviceEnvironmentModuleImpl";
    private final Context mContext;

    public DeviceEnvironmentModuleImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.oculus.modules.codegen.DeviceEnvironmentModule
    public DeviceEnvironmentModule.DeviceEnvironmentBlobType marshallJSConstantEnvironment() {
        DeviceEnvironmentModule.DeviceEnvironmentBlobType deviceEnvironmentBlobType = new DeviceEnvironmentModule.DeviceEnvironmentBlobType();
        deviceEnvironmentBlobType.deviceId = getDeviceId(this.mContext);
        deviceEnvironmentBlobType.deviceLocale = getDeviceLocale(this.mContext);
        deviceEnvironmentBlobType.deviceName = getDeviceName();
        deviceEnvironmentBlobType.deviceSerial = getDeviceSerial();
        deviceEnvironmentBlobType.osVersionName = Build.DISPLAY;
        deviceEnvironmentBlobType.repositoryLocation = getRepositoryLocation(BuildConfig.REPOSITORY_LOCATION);
        deviceEnvironmentBlobType.unsafeDeviceProductUseFeatureDetectionInstead = Build.PRODUCT;
        deviceEnvironmentBlobType.userAgent = NetworkHeaders.getUserAgent();
        String packageName = this.mContext.getPackageName();
        deviceEnvironmentBlobType.packageName = packageName;
        AppDetails appDetails = AppDetails.get(this.mContext, packageName);
        deviceEnvironmentBlobType.versionCode = (double) appDetails.versionCode;
        deviceEnvironmentBlobType.versionName = appDetails.versionName;
        return deviceEnvironmentBlobType;
    }

    @Override // com.oculus.modules.codegen.DeviceEnvironmentModule
    public void getDeviceEnvironmentHeadersImpl(Resolver<DeviceEnvironmentModule.DeviceEnvironmentHeadersType> resolver) {
        Context context = this.mContext;
        DeviceEnvironmentModule.DeviceEnvironmentHeadersType deviceEnvironmentHeadersType = new DeviceEnvironmentModule.DeviceEnvironmentHeadersType();
        deviceEnvironmentHeadersType.Accept_Language = getDeviceLocale(context);
        deviceEnvironmentHeadersType.User_Agent = NetworkHeaders.getUserAgent();
        deviceEnvironmentHeadersType.X_ANDROID_ID = getDeviceId(context);
        deviceEnvironmentHeadersType.X_Build_Model = getDeviceName();
        deviceEnvironmentHeadersType.X_Build_Number = String.valueOf(AppDetails.get(context, context.getPackageName()).versionCode);
        deviceEnvironmentHeadersType.X_Build_Version_Incremental = SystemProperties.getString("ro.build.version.incremental", com.oculus.common.build.BuildConfig.PROVIDER_SUFFIX);
        deviceEnvironmentHeadersType.X_OC_Selected_Headset_Serial = getDeviceSerial();
        deviceEnvironmentHeadersType.X_OC_VrShell_Build_Name = AppDetails.get(context, "com.oculus.vrshell").versionName;
        resolver.resolve(deviceEnvironmentHeadersType);
    }

    public static String getDeviceSerial() {
        return SystemProperties.getString("ro.serialno", Build.SERIAL);
    }

    public static String getDeviceLocale(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return locale.toString();
    }

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    public static String getDeviceName() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return StringUtil.capitalize(str2);
        }
        return StringUtil.capitalize(str) + " " + str2;
    }

    private static DeviceEnvironmentModule.RepositoryLocationType getRepositoryLocation(String str) {
        DeviceEnvironmentModule.RepositoryLocationType repositoryLocationType = new DeviceEnvironmentModule.RepositoryLocationType();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() > 0) {
                repositoryLocationType.setFromJSONObject(jSONObject);
            }
        } catch (JSONException e) {
            String str2 = TAG;
            Log.w(str2, "Repository location is invalid JSON:" + str, e);
        } catch (NullPointerException e2) {
            String str3 = TAG;
            Log.w(str3, "Repository location is missing parameters: " + str, e2);
        }
        if (repositoryLocationType.nodes == null) {
            repositoryLocationType.nodes = new ArrayList();
        }
        if (repositoryLocationType.remoteBookmark == null) {
            repositoryLocationType.remoteBookmark = com.oculus.common.build.BuildConfig.PROVIDER_SUFFIX;
        }
        return repositoryLocationType;
    }
}
