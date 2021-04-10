package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.util.regex.Pattern;
import org.json.JSONObject;

public final class JV {
    public Pattern A00;
    public boolean A01;

    public static JV A00(Object obj) {
        Pattern compile = Pattern.compile(OacrConstants.AUTO_SPEECH_DOMAIN);
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
        return new JV(compile, z);
    }

    public final boolean A01(String str) {
        return this.A00.matcher(str).matches() ^ this.A01;
    }

    public JV() {
        this(Pattern.compile(OacrConstants.AUTO_SPEECH_DOMAIN), false);
    }

    public JV(Pattern pattern, boolean z) {
        this.A00 = pattern;
        this.A01 = z;
    }
}
