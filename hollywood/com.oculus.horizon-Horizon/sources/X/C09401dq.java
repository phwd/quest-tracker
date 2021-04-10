package X;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.LoggingBehavior;
import com.facebook.internal.Logger;
import com.squareup.okhttp.internal.DiskLruCache;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.1dq  reason: invalid class name and case insensitive filesystem */
public class C09401dq implements Serializable {
    public static final HashSet<String> A00 = new HashSet<>();
    public static final long serialVersionUID = 1;
    public boolean isImplicit;
    public JSONObject jsonObject;
    public String name;

    public final String toString() {
        return String.format("\"%s\", implicit: %b, json: %s", this.jsonObject.optString("_eventName"), Boolean.valueOf(this.isImplicit), this.jsonObject.toString());
    }

    public static void A00(String str) throws FacebookException {
        String str2;
        boolean contains;
        if (str != null) {
            int length = str.length();
            if (length != 0 && length <= 40) {
                HashSet<String> hashSet = A00;
                synchronized (hashSet) {
                    contains = hashSet.contains(str);
                }
                if (contains) {
                    return;
                }
                if (str.matches("^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$")) {
                    synchronized (hashSet) {
                        hashSet.add(str);
                    }
                    return;
                }
                str2 = String.format("Skipping event named '%s' due to illegal name - must be under 40 chars and alphanumeric, _, - or space, and not start with a space or hyphen.", str);
                throw new FacebookException(str2);
            }
        } else {
            str = "<None Provided>";
        }
        str2 = String.format(Locale.ROOT, "Identifier '%s' must be less than %d characters", str, 40);
        throw new FacebookException(str2);
    }

    private Object writeReplace() {
        return new C09461gr(this.jsonObject.toString(), this.isImplicit);
    }

    public C09401dq(String str, String str2, Bundle bundle, boolean z) {
        LoggingBehavior loggingBehavior;
        Object[] objArr;
        String str3;
        try {
            A00(str2);
            this.name = str2;
            this.isImplicit = z;
            JSONObject jSONObject = new JSONObject();
            this.jsonObject = jSONObject;
            jSONObject.put("_eventName", str2);
            this.jsonObject.put("_logTime", System.currentTimeMillis() / 1000);
            this.jsonObject.put("_ui", str);
            if (this.isImplicit) {
                this.jsonObject.put("_implicitlyLogged", DiskLruCache.VERSION_1);
            }
            if (bundle != null) {
                for (String str4 : bundle.keySet()) {
                    A00(str4);
                    Object obj = bundle.get(str4);
                    if ((obj instanceof String) || (obj instanceof Number)) {
                        this.jsonObject.put(str4, obj.toString());
                    } else {
                        throw new FacebookException(String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", obj, str4));
                    }
                }
            }
            if (!this.isImplicit) {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Created app event '%s'", this.jsonObject.toString());
            }
        } catch (JSONException e) {
            loggingBehavior = LoggingBehavior.APP_EVENTS;
            objArr = new Object[]{e.toString()};
            str3 = "JSON encoding for app event failed: '%s'";
            Logger.log(loggingBehavior, "AppEvents", str3, objArr);
            this.jsonObject = null;
        } catch (FacebookException e2) {
            loggingBehavior = LoggingBehavior.APP_EVENTS;
            objArr = new Object[]{e2.toString()};
            str3 = "Invalid app event name or parameter:";
            Logger.log(loggingBehavior, "AppEvents", str3, objArr);
            this.jsonObject = null;
        }
    }

    public C09401dq(String str, boolean z) throws JSONException {
        this.jsonObject = new JSONObject(str);
        this.isImplicit = z;
    }
}
