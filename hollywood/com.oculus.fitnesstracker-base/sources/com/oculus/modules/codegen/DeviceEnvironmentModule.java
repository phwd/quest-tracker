package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.fitnesstracker.database.FitnessTrackerMoveContract;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class DeviceEnvironmentModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = "DeviceEnvironmentModule";

    /* access modifiers changed from: protected */
    public abstract void getDeviceEnvironmentHeadersImpl(Resolver<DeviceEnvironmentHeadersType> resolver);

    /* access modifiers changed from: protected */
    public abstract DeviceEnvironmentBlobType marshallJSConstantEnvironment();

    public void shutdownModule() {
    }

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("getDeviceEnvironmentHeaders", "+ii"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ENVIRONMENT", marshallJSConstantEnvironment().convertToJSONObject());
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    /* access modifiers changed from: protected */
    public final void getDeviceEnvironmentHeaders(int i, int i2) {
        getDeviceEnvironmentHeadersImpl(ResolverFactory.createParcelResolver(this, i, i2));
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
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("deviceId", this.deviceId);
                jSONObject.put("deviceLocale", this.deviceLocale);
                jSONObject.put("deviceName", this.deviceName);
                jSONObject.put("deviceSerial", this.deviceSerial);
                jSONObject.put("osVersionName", this.osVersionName);
                jSONObject.put(FitnessTrackerMoveContract.Session.PACKAGE_NAME, this.packageName);
                jSONObject.put("repositoryLocation", this.repositoryLocation.convertToJSONObject());
                jSONObject.put("unsafeDeviceProductUseFeatureDetectionInstead", this.unsafeDeviceProductUseFeatureDetectionInstead);
                jSONObject.put("userAgent", this.userAgent);
                jSONObject.put("versionCode", this.versionCode);
                jSONObject.put("versionName", this.versionName);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.deviceId = jSONObject.optString("deviceId");
            this.deviceLocale = jSONObject.optString("deviceLocale");
            this.deviceName = jSONObject.optString("deviceName");
            this.deviceSerial = jSONObject.optString("deviceSerial");
            this.osVersionName = jSONObject.optString("osVersionName");
            this.packageName = jSONObject.optString(FitnessTrackerMoveContract.Session.PACKAGE_NAME);
            this.repositoryLocation = RepositoryLocationType.makeFromJSONObject(jSONObject.optJSONObject("repositoryLocation"));
            this.unsafeDeviceProductUseFeatureDetectionInstead = jSONObject.isNull("unsafeDeviceProductUseFeatureDetectionInstead") ? null : jSONObject.optString("unsafeDeviceProductUseFeatureDetectionInstead");
            this.userAgent = jSONObject.optString("userAgent");
            this.versionCode = jSONObject.optDouble("versionCode", 0.0d);
            this.versionName = jSONObject.optString("versionName");
        }

        public static final DeviceEnvironmentBlobType makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DeviceEnvironmentBlobType deviceEnvironmentBlobType = new DeviceEnvironmentBlobType();
            deviceEnvironmentBlobType.setFromJSONObject(jSONObject);
            return deviceEnvironmentBlobType;
        }
    }

    public static class RepositoryLocationType extends NativeModuleParcel {
        public boolean dirty;
        public List<RepositoryNodeDataType> nodes;
        public String remoteBookmark;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("dirty", this.dirty);
                jSONObject.put("nodes", NativeModuleParcel.convertParcelListToJSONArray(this.nodes));
                jSONObject.put("remoteBookmark", this.remoteBookmark);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.dirty = jSONObject.optBoolean("dirty");
            this.nodes = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("nodes"), RepositoryNodeDataType.class);
            this.remoteBookmark = jSONObject.optString("remoteBookmark");
        }

        public static final RepositoryLocationType makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            RepositoryLocationType repositoryLocationType = new RepositoryLocationType();
            repositoryLocationType.setFromJSONObject(jSONObject);
            return repositoryLocationType;
        }
    }

    public static class RepositoryNodeDataType extends NativeModuleParcel {
        public double date;
        public boolean landed;
        public String node;
        public String remoteBookmark;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(FitnessTrackerMoveContract.Days.DATE, this.date);
                jSONObject.put("landed", this.landed);
                jSONObject.put("node", this.node);
                jSONObject.put("remoteBookmark", this.remoteBookmark);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.date = jSONObject.optDouble(FitnessTrackerMoveContract.Days.DATE, 0.0d);
            this.landed = jSONObject.optBoolean("landed");
            this.node = jSONObject.optString("node");
            this.remoteBookmark = jSONObject.isNull("remoteBookmark") ? null : jSONObject.optString("remoteBookmark");
        }

        public static final RepositoryNodeDataType makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            RepositoryNodeDataType repositoryNodeDataType = new RepositoryNodeDataType();
            repositoryNodeDataType.setFromJSONObject(jSONObject);
            return repositoryNodeDataType;
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
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("Accept-Language", this.Accept_Language);
                jSONObject.put("User-Agent", this.User_Agent);
                jSONObject.put("X-ANDROID-ID", this.X_ANDROID_ID);
                jSONObject.put("X-Build-Model", this.X_Build_Model);
                jSONObject.put("X-Build-Number", this.X_Build_Number);
                jSONObject.put("X-Build-Version-Incremental", this.X_Build_Version_Incremental);
                jSONObject.put("X-OC-Selected-Headset-Serial", this.X_OC_Selected_Headset_Serial);
                jSONObject.put("X-OC-VrShell-Build-Name", this.X_OC_VrShell_Build_Name);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.Accept_Language = jSONObject.optString("Accept-Language");
            this.User_Agent = jSONObject.optString("User-Agent");
            this.X_ANDROID_ID = jSONObject.optString("X-ANDROID-ID");
            this.X_Build_Model = jSONObject.optString("X-Build-Model");
            this.X_Build_Number = jSONObject.optString("X-Build-Number");
            this.X_Build_Version_Incremental = jSONObject.optString("X-Build-Version-Incremental");
            this.X_OC_Selected_Headset_Serial = jSONObject.optString("X-OC-Selected-Headset-Serial");
            this.X_OC_VrShell_Build_Name = jSONObject.optString("X-OC-VrShell-Build-Name");
        }

        public static final DeviceEnvironmentHeadersType makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DeviceEnvironmentHeadersType deviceEnvironmentHeadersType = new DeviceEnvironmentHeadersType();
            deviceEnvironmentHeadersType.setFromJSONObject(jSONObject);
            return deviceEnvironmentHeadersType;
        }
    }
}
