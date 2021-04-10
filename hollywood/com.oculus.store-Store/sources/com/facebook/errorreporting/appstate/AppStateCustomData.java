package com.facebook.errorreporting.appstate;

import androidx.annotation.GuardedBy;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ThreadSafe;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
@ThreadSafe
public class AppStateCustomData {
    @GuardedBy("itself")
    private final Map<String, String> mCustomData = new HashMap();

    public AppStateCustomData() {
    }

    public AppStateCustomData(AppStateCustomData appStateCustomData) {
        synchronized (this.mCustomData) {
            synchronized (appStateCustomData.mCustomData) {
                this.mCustomData.putAll(appStateCustomData.mCustomData);
            }
        }
    }

    public void addCustomAppData(String key, String value) {
        synchronized (this.mCustomData) {
            this.mCustomData.put(key, value);
        }
    }

    public void removeCustomAppData(String key) {
        synchronized (this.mCustomData) {
            this.mCustomData.remove(key);
        }
    }

    public void serializeAndWriteAsJsonObject(Writer outputWriter) throws IOException, JSONException {
        String payload;
        try {
            Map<String, String> customDataCopy = new HashMap<>();
            synchronized (this.mCustomData) {
                customDataCopy.putAll(this.mCustomData);
            }
            JSONObject jsonObject = new JSONObject();
            for (Map.Entry<String, String> entry : customDataCopy.entrySet()) {
                jsonObject.put(entry.getKey(), entry.getValue());
            }
            payload = jsonObject.toString();
        } catch (OutOfMemoryError e) {
            payload = "{}";
        }
        outputWriter.append((CharSequence) payload);
    }

    public boolean isEmpty() {
        boolean isEmpty;
        synchronized (this.mCustomData) {
            isEmpty = this.mCustomData.isEmpty();
        }
        return isEmpty;
    }
}
