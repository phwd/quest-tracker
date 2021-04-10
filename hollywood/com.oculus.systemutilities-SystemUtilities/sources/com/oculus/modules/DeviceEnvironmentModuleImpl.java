package com.oculus.modules;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.device.NetworkHeaders;
import com.oculus.modules.codegen.DeviceEnvironmentModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.os.PreferencesManager;
import com.oculus.panellib.AppDetails;
import com.oculus.panellib.SystemProperties;
import com.oculus.util.StringUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceEnvironmentModuleImpl extends DeviceEnvironmentModule {
    private static final String PAIRING_CODE_PREF_KEY = "pairing_code";
    private static final String TAG = DeviceEnvironmentModuleImpl.class.getSimpleName();
    private final Context mContext;
    private final String mDeviceOTACode = getDeviceOTACode(Build.SERIAL, 5);

    public DeviceEnvironmentModuleImpl(Context context) {
        this.mContext = context;
        saveDeviceOTACode(this.mDeviceOTACode);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceEnvironmentModule
    public DeviceEnvironmentModule.DeviceEnvironmentBlobType marshallJSConstantEnvironment() {
        DeviceEnvironmentModule.DeviceEnvironmentBlobType env = new DeviceEnvironmentModule.DeviceEnvironmentBlobType();
        env.deviceId = getDeviceId(this.mContext);
        env.deviceLocale = getDeviceLocale(this.mContext);
        env.deviceName = getDeviceName();
        env.deviceOTACode = this.mDeviceOTACode;
        env.deviceSerial = getDeviceSerial();
        env.osVersionName = Build.DISPLAY;
        env.repositoryLocation = getRepositoryLocation("{\"dirty\":true,\"nodes\":[{\"date\":1616459597.0252,\"landed\":false,\"node\":\"d671ba5b413593b8741d908a57923afe69b06208\",\"remoteBookmark\":\"remote/release-si-oculus-ovrs-2021.02.12\"}],\"remoteBookmark\":\"remote/release-si-oculus-ovrs-2021.02.12\"}");
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
            repositoryLocation.remoteBookmark = "";
        }
        return repositoryLocation;
    }

    private static String getDeviceOTACode(String serial, int length) {
        try {
            byte[] hashSum = MessageDigest.getInstance("SHA-256").digest(serial.getBytes());
            ByteBuffer buffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
            buffer.put(hashSum, 0, 4);
            int code = (int) ((long) (((double) buffer.getLong(0)) % Math.pow(10.0d, (double) length)));
            return String.format("%0" + length + "d", Integer.valueOf(code));
        } catch (Exception e) {
            Log.e(TAG, "Error calculating the headset code", e);
            return "";
        }
    }

    private void saveDeviceOTACode(String serial) {
        PreferencesManager pm = new PreferencesManager();
        if (((Boolean) pm.getString(PAIRING_CODE_PREF_KEY).first).booleanValue() && TextUtils.isEmpty((CharSequence) pm.getString(PAIRING_CODE_PREF_KEY).second)) {
            pm.set(PAIRING_CODE_PREF_KEY, serial);
        }
    }
}
