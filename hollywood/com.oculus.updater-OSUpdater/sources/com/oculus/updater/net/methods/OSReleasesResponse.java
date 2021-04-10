package com.oculus.updater.net.methods;

import com.facebook.debug.log.BLog;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

public class OSReleasesResponse {
    @SerializedName("update_interval")
    public Long updateInterval;
    @SerializedName("ota")
    @Nullable
    public List<OSReleaseInfo> updates;

    public static class OSReleaseInfo {
        @SerializedName("base_version")
        public Long base;
        @SerializedName("file_checksum")
        public String fileChecksum;
        @SerializedName("install_options")
        @Nullable
        public String installOptions;
        @SerializedName("release_channel_id")
        public String releaseChannelId;
        @SerializedName("release_channel_name")
        public String releaseChannelName;
        @SerializedName("target_version")
        public Long target;
        @SerializedName("download_uri")
        public String uri;

        public String toString() {
            try {
                JSONStringer jSONStringer = new JSONStringer();
                jSONStringer.object();
                jSONStringer.key("uri").value(this.uri);
                jSONStringer.key("base").value(this.base);
                jSONStringer.key("target").value(this.target);
                jSONStringer.key("install_options").value(this.installOptions);
                jSONStringer.key("file_checksum").value(this.fileChecksum);
                jSONStringer.key("release_channel_id").value(this.releaseChannelId);
                jSONStringer.key("release_channel_name").value(this.releaseChannelName);
                jSONStringer.endObject();
                return jSONStringer.toString();
            } catch (JSONException e) {
                BLog.e(OSReleasesResponse.class.getSimpleName(), "could not create JSON object for OSReleaseInfo", e);
                return "{}";
            }
        }

        @Nullable
        public ReleaseInstallOptions getReleaseInstallOptions() {
            String str = this.installOptions;
            if (str == null) {
                return null;
            }
            try {
                JSONObject jSONObject = (JSONObject) new JSONTokener(str).nextValue();
                if (jSONObject.has("type")) {
                    return new ReleaseInstallOptions(jSONObject.optLong("offset", Long.MIN_VALUE), jSONObject.optLong("size", Long.MIN_VALUE), jSONObject.optString("headers").split("\n"), jSONObject.optString("type"));
                }
                return null;
            } catch (JSONException unused) {
                return null;
            }
        }
    }

    public String toString() {
        try {
            JSONStringer jSONStringer = new JSONStringer();
            jSONStringer.object();
            jSONStringer.key("update_interval").value(this.updateInterval);
            jSONStringer.key("updates").array();
            if (this.updates != null) {
                for (OSReleaseInfo oSReleaseInfo : this.updates) {
                    jSONStringer.value(oSReleaseInfo.toString());
                }
            }
            jSONStringer.endArray();
            jSONStringer.endObject();
            return jSONStringer.toString();
        } catch (JSONException e) {
            BLog.e(OSReleasesResponse.class.getSimpleName(), "could not create JSON string for OSReleasesResponse", e);
            return "{}";
        }
    }
}
