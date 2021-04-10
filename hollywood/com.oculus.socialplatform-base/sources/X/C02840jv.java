package X;

import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0jv  reason: invalid class name and case insensitive filesystem */
public final class C02840jv {
    public Pattern A00;
    public boolean A01;

    public static C02840jv A00(Object obj) throws JSONException {
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
        return new C02840jv(compile, z);
    }

    public final boolean A01(String str) {
        return this.A00.matcher(str).matches() ^ this.A01;
    }

    public C02840jv() {
        this(Pattern.compile(""), false);
    }

    public C02840jv(Pattern pattern, boolean z) {
        this.A00 = pattern;
        this.A01 = z;
    }
}
