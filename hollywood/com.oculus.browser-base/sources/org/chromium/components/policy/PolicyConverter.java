package org.chromium.components.policy;

import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PolicyConverter {

    /* renamed from: a  reason: collision with root package name */
    public long f10881a;

    public PolicyConverter(long j) {
        this.f10881a = j;
    }

    public static PolicyConverter create(long j) {
        return new PolicyConverter(j);
    }

    public final JSONArray a(Bundle[] bundleArr) {
        JSONArray jSONArray = new JSONArray();
        for (Bundle bundle : bundleArr) {
            jSONArray.put(b(bundle));
        }
        return jSONArray;
    }

    public final JSONObject b(Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof Bundle) {
                obj = b((Bundle) obj);
            }
            if (obj instanceof Bundle[]) {
                obj = a((Bundle[]) obj);
            }
            jSONObject.put(str, JSONObject.wrap(obj));
        }
        return jSONObject;
    }

    public final void onNativeDestroyed() {
        this.f10881a = 0;
    }
}
