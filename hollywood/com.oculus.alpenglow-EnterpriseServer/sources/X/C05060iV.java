package X;

import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0iV  reason: invalid class name and case insensitive filesystem */
public final class C05060iV {
    public Pattern A00;
    public boolean A01;

    public static C05060iV A00(Object obj) throws JSONException {
        Pattern compile = Pattern.compile("");
        boolean z = false;
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject.has("pattern")) {
                compile = Pattern.compile(jSONObject.getString("pattern"), 32);
                if (jSONObject.has("negation") && jSONObject.getString("negation").equalsIgnoreCase("true")) {
                    z = true;
                }
            }
        } else if (obj instanceof String) {
            compile = Pattern.compile((String) obj, 32);
        }
        return new C05060iV(compile, z);
    }

    public final boolean A01(String str) {
        return this.A00.matcher(str).matches() ^ this.A01;
    }

    public C05060iV() {
        this(Pattern.compile(""), false);
    }

    public C05060iV(Pattern pattern, boolean z) {
        this.A00 = pattern;
        this.A01 = z;
    }
}
