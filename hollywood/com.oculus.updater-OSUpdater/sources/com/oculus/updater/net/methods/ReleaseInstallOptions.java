package com.oculus.updater.net.methods;

import com.facebook.debug.log.BLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONStringer;

public class ReleaseInstallOptions {
    private static final String TAG = "ReleaseInstallOptions";
    public final String[] headers;
    public final Long offset;
    public final Long size;
    public final String type;

    public ReleaseInstallOptions(long j, long j2, String[] strArr, String str) {
        this.offset = Long.valueOf(j);
        this.size = Long.valueOf(j2);
        this.headers = strArr;
        this.type = str;
    }

    public String toString() {
        try {
            JSONStringer jSONStringer = new JSONStringer();
            jSONStringer.object();
            jSONStringer.key("offset").value(this.offset);
            jSONStringer.key("size").value(this.size);
            jSONStringer.key("headers").value(new JSONArray(this.headers));
            jSONStringer.key("type").value(this.type);
            jSONStringer.endObject();
            return jSONStringer.toString();
        } catch (JSONException e) {
            BLog.e(TAG, "could not create JSON string for ReleaseInstallOptions", e);
            return "{}";
        }
    }
}
