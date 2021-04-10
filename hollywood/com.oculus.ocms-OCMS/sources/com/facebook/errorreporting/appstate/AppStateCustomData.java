package com.facebook.errorreporting.appstate;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
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
    @Nullable
    @GuardedBy("this")
    private CustomDataChangeListener mListener;

    public interface CustomDataChangeListener {
        void onCustomDataChanged(String str);
    }

    public AppStateCustomData() {
    }

    public AppStateCustomData(AppStateCustomData appStateCustomData) {
        synchronized (this.mCustomData) {
            synchronized (appStateCustomData.mCustomData) {
                this.mCustomData.putAll(appStateCustomData.mCustomData);
            }
        }
    }

    public void addCustomAppData(String str, String str2) {
        synchronized (this.mCustomData) {
            this.mCustomData.put(str, str2);
        }
        synchronized (this) {
            if (this.mListener != null) {
                this.mListener.onCustomDataChanged(str);
            }
        }
    }

    public void removeCustomAppData(String str) {
        synchronized (this.mCustomData) {
            this.mCustomData.remove(str);
        }
        synchronized (this) {
            if (this.mListener != null) {
                this.mListener.onCustomDataChanged(str);
            }
        }
    }

    public void setCustomDataChangeListener(@Nullable CustomDataChangeListener customDataChangeListener) {
        synchronized (this) {
            this.mListener = customDataChangeListener;
        }
    }

    public void serializeAndWriteAsJsonObject(Writer writer) throws IOException, JSONException {
        String str;
        try {
            HashMap hashMap = new HashMap();
            synchronized (this.mCustomData) {
                hashMap.putAll(this.mCustomData);
            }
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry entry : hashMap.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
            str = jSONObject.toString();
        } catch (OutOfMemoryError unused) {
            str = "{}";
        }
        writer.append((CharSequence) str);
    }

    public boolean isEmpty() {
        boolean isEmpty;
        synchronized (this.mCustomData) {
            isEmpty = this.mCustomData.isEmpty();
        }
        return isEmpty;
    }
}
