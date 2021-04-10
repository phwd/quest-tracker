package X;

import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.fL  reason: case insensitive filesystem */
public final class C0206fL {
    public Pattern A00;
    public boolean A01;

    public static C0206fL A00(Object obj) throws JSONException {
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
        return new C0206fL(compile, z);
    }

    public final boolean A01(String str) {
        return this.A00.matcher(str).matches() ^ this.A01;
    }

    public C0206fL() {
        this(Pattern.compile(""), false);
    }

    public C0206fL(Pattern pattern, boolean z) {
        this.A00 = pattern;
        this.A01 = z;
    }
}
