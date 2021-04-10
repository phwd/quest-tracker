package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class DeviceEnvironmentModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = DeviceEnvironmentModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getDeviceEnvironmentHeadersImpl(Resolver<DeviceEnvironmentHeadersType> resolver);

    /* access modifiers changed from: protected */
    public abstract DeviceEnvironmentBlobType marshallJSConstantEnvironment();

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getDeviceEnvironmentHeaders", "+ii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        JSONObject result = new JSONObject();
        try {
            result.put("ENVIRONMENT", marshallJSConstantEnvironment().convertToJSONObject());
        } catch (JSONException e) {
        }
        return result.toString();
    }

    /* access modifiers changed from: protected */
    public final void getDeviceEnvironmentHeaders(int resolveID, int rejectID) {
        getDeviceEnvironmentHeadersImpl(ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class DeviceEnvironmentBlobType extends NativeModuleParcel {
        public String deviceId;
        public String deviceLocale;
        public String deviceName;
        public String deviceSerial;
        public String osVersionName;
        public String packageName;
        public RepositoryLocationType repositoryLocation;
        public String unsafeDeviceProductUseFeatureDetectionInstead;
        public String userAgent;
        public double versionCode;
        public String versionName;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("deviceId", this.deviceId);
                parcel.put("deviceLocale", this.deviceLocale);
                parcel.put("deviceName", this.deviceName);
                parcel.put("deviceSerial", this.deviceSerial);
                parcel.put("osVersionName", this.osVersionName);
                parcel.put("packageName", this.packageName);
                parcel.put("repositoryLocation", this.repositoryLocation.convertToJSONObject());
                parcel.put("unsafeDeviceProductUseFeatureDetectionInstead", this.unsafeDeviceProductUseFeatureDetectionInstead);
                parcel.put("userAgent", this.userAgent);
                parcel.put("versionCode", this.versionCode);
                parcel.put("versionName", this.versionName);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.deviceId = json.optString("deviceId");
            this.deviceLocale = json.optString("deviceLocale");
            this.deviceName = json.optString("deviceName");
            this.deviceSerial = json.optString("deviceSerial");
            this.osVersionName = json.optString("osVersionName");
            this.packageName = json.optString("packageName");
            this.repositoryLocation = RepositoryLocationType.makeFromJSONObject(json.optJSONObject("repositoryLocation"));
            this.unsafeDeviceProductUseFeatureDetectionInstead = json.isNull("unsafeDeviceProductUseFeatureDetectionInstead") ? null : json.optString("unsafeDeviceProductUseFeatureDetectionInstead");
            this.userAgent = json.optString("userAgent");
            this.versionCode = json.optDouble("versionCode", 0.0d);
            this.versionName = json.optString("versionName");
        }
    }

    public static class RepositoryLocationType extends NativeModuleParcel {
        public boolean dirty;
        public List<RepositoryNodeDataType> nodes;
        public String remoteBookmark;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("dirty", this.dirty);
                parcel.put("nodes", NativeModuleParcel.convertParcelListToJSONArray(this.nodes));
                parcel.put("remoteBookmark", this.remoteBookmark);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.dirty = json.optBoolean("dirty");
            this.nodes = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("nodes"), RepositoryNodeDataType.class);
            this.remoteBookmark = json.optString("remoteBookmark");
        }

        public static final RepositoryLocationType makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            RepositoryLocationType result = new RepositoryLocationType();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class RepositoryNodeDataType extends NativeModuleParcel {
        public double date;
        public boolean landed;
        public String node;
        public String remoteBookmark;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("date", this.date);
                parcel.put("landed", this.landed);
                parcel.put("node", this.node);
                parcel.put("remoteBookmark", this.remoteBookmark);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.date = json.optDouble("date", 0.0d);
            this.landed = json.optBoolean("landed");
            this.node = json.optString("node");
            this.remoteBookmark = json.isNull("remoteBookmark") ? null : json.optString("remoteBookmark");
        }
    }

    public static class DeviceEnvironmentHeadersType extends NativeModuleParcel {
        public String Accept_Language;
        public String User_Agent;
        public String X_ANDROID_ID;
        public String X_Build_Model;
        public String X_Build_Number;
        public String X_Build_Version_Incremental;
        public String X_OC_Selected_Headset_Serial;
        public String X_OC_VrShell_Build_Name;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("Accept-Language", this.Accept_Language);
                parcel.put("User-Agent", this.User_Agent);
                parcel.put("X-ANDROID-ID", this.X_ANDROID_ID);
                parcel.put("X-Build-Model", this.X_Build_Model);
                parcel.put("X-Build-Number", this.X_Build_Number);
                parcel.put("X-Build-Version-Incremental", this.X_Build_Version_Incremental);
                parcel.put("X-OC-Selected-Headset-Serial", this.X_OC_Selected_Headset_Serial);
                parcel.put("X-OC-VrShell-Build-Name", this.X_OC_VrShell_Build_Name);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.Accept_Language = json.optString("Accept-Language");
            this.User_Agent = json.optString("User-Agent");
            this.X_ANDROID_ID = json.optString("X-ANDROID-ID");
            this.X_Build_Model = json.optString("X-Build-Model");
            this.X_Build_Number = json.optString("X-Build-Number");
            this.X_Build_Version_Incremental = json.optString("X-Build-Version-Incremental");
            this.X_OC_Selected_Headset_Serial = json.optString("X-OC-Selected-Headset-Serial");
            this.X_OC_VrShell_Build_Name = json.optString("X-OC-VrShell-Build-Name");
        }
    }
}
