package com.oculus.modules;

import X.AnonymousClass006;
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
import org.apache.commons.cli.HelpFormatter;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceEnvironmentModuleImpl extends DeviceEnvironmentModule {
    public static final String TAG = "DeviceEnvironmentModuleImpl";
    public final Context mContext;

    public static String getDeviceName() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return StringUtil.capitalize(str2);
        }
        return AnonymousClass006.A09(StringUtil.capitalize(str), HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, str2);
    }

    public static String getDeviceSerial() {
        return SystemProperties.getString("ro.serialno", Build.SERIAL);
    }

    public static DeviceEnvironmentModule.RepositoryLocationType getRepositoryLocation(String str) {
        DeviceEnvironmentModule.RepositoryLocationType repositoryLocationType = new DeviceEnvironmentModule.RepositoryLocationType();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() > 0) {
                repositoryLocationType.setFromJSONObject(jSONObject);
            }
        } catch (JSONException e) {
            Log.w(TAG, AnonymousClass006.A07("Repository location is invalid JSON:", str), e);
        } catch (NullPointerException e2) {
            Log.w(TAG, AnonymousClass006.A07("Repository location is missing parameters: ", str), e2);
        }
        if (repositoryLocationType.nodes == null) {
            repositoryLocationType.nodes = new ArrayList();
        }
        if (repositoryLocationType.remoteBookmark == null) {
            repositoryLocationType.remoteBookmark = "";
        }
        return repositoryLocationType;
    }

    @Override // com.oculus.modules.codegen.DeviceEnvironmentModule
    public void getDeviceEnvironmentHeadersImpl(Resolver<DeviceEnvironmentModule.DeviceEnvironmentHeadersType> resolver) {
        resolver.resolve(NetworkHeaders.getHeaders(this.mContext));
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
        AppDetails appDetails = AppDetails.get(this.mContext, packageName, false);
        deviceEnvironmentBlobType.versionCode = (double) appDetails.versionCode;
        deviceEnvironmentBlobType.versionName = appDetails.versionName;
        return deviceEnvironmentBlobType;
    }

    public DeviceEnvironmentModuleImpl(Context context) {
        this.mContext = context;
    }

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    public static String getDeviceLocale(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return locale.toString();
    }
}
