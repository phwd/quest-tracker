package com.oculus.modules;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.oculus.common.build.BuildConfig;
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
    private static final String TAG = DeviceEnvironmentModuleImpl.class.getSimpleName();
    private final Context mContext;

    public DeviceEnvironmentModuleImpl(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceEnvironmentModule
    public DeviceEnvironmentModule.DeviceEnvironmentBlobType marshallJSConstantEnvironment() {
        DeviceEnvironmentModule.DeviceEnvironmentBlobType env = new DeviceEnvironmentModule.DeviceEnvironmentBlobType();
        env.deviceId = getDeviceId(this.mContext);
        env.deviceLocale = getDeviceLocale(this.mContext);
        env.deviceName = getDeviceName();
        env.deviceSerial = getDeviceSerial();
        env.osVersionName = Build.DISPLAY;
        env.repositoryLocation = getRepositoryLocation("{\"dirty\":true,\"nodes\":[{\"date\":1617296683.0252,\"landed\":false,\"node\":\"960af00ff2b8b5e60716c2e73d4cafd01ed14e9c\",\"remoteBookmark\":\"remote/releases/release-oculus-explore-2021.03.26\"}],\"remoteBookmark\":\"remote/releases/release-oculus-explore-2021.03.26\"}");
        env.unsafeDeviceProductUseFeatureDetectionInstead = Build.PRODUCT;
        env.userAgent = NetworkHeaders.getUserAgent();
        String packageName = this.mContext.getPackageName();
        env.packageName = packageName;
        AppDetails currentAppDetails = AppDetails.get(this.mContext, packageName);
        env.versionCode = (double) currentAppDetails.versionCode;
        env.versionName = currentAppDetails.versionName;
        return env;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceEnvironmentModule
    public void getDeviceEnvironmentHeadersImpl(Resolver<DeviceEnvironmentModule.DeviceEnvironmentHeadersType> resolver) {
        resolver.resolve(NetworkHeaders.getHeaders(this.mContext));
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
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return StringUtil.capitalize(model);
        }
        return StringUtil.capitalize(manufacturer) + " " + model;
    }

    private static DeviceEnvironmentModule.RepositoryLocationType getRepositoryLocation(String repositoryLocationString) {
        DeviceEnvironmentModule.RepositoryLocationType repositoryLocation = new DeviceEnvironmentModule.RepositoryLocationType();
        try {
            JSONObject repositoryLocationJson = new JSONObject(repositoryLocationString);
            if (repositoryLocationJson.length() > 0) {
                repositoryLocation.setFromJSONObject(repositoryLocationJson);
            }
        } catch (JSONException jsonException) {
            Log.w(TAG, "Repository location is invalid JSON:" + repositoryLocationString, jsonException);
        } catch (NullPointerException nullPointerException) {
            Log.w(TAG, "Repository location is missing parameters: " + repositoryLocationString, nullPointerException);
        }
        if (repositoryLocation.nodes == null) {
            repositoryLocation.nodes = new ArrayList();
        }
        if (repositoryLocation.remoteBookmark == null) {
            repositoryLocation.remoteBookmark = BuildConfig.PROVIDER_SUFFIX;
        }
        return repositoryLocation;
    }
}
