package defpackage;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: uu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5162uu0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Map f11444a = new C4992tu0();

    public static AbstractC4822su0 a(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("type");
            if (string == null) {
                string = "UNKNOWN";
            }
            char c = 65535;
            int hashCode = string.hashCode();
            if (hashCode != -1929630640) {
                if (hashCode != 433141802) {
                    if (hashCode == 685677071 && string.equals("PRODUCT_PRICE_UPDATE")) {
                        c = 1;
                    }
                } else if (string.equals("UNKNOWN")) {
                    c = 2;
                }
            } else if (string.equals("BUYABLE_PRODUCT")) {
                c = 0;
            }
            if (c == 0) {
                return C0760Mk.a(jSONObject);
            }
            if (c != 1) {
                return null;
            }
            return SG0.a(jSONObject);
        } catch (JSONException e) {
            AbstractC1220Ua0.d("PAU", String.format(Locale.US, "Failed to parse PageAnnotation.Details: %s", e.toString()), new Object[0]);
            return null;
        }
    }

    public static AbstractC4822su0 b(List list, Class cls) {
        String str;
        if (list == null || list.size() == 0 || (str = (String) f11444a.get(cls)) == null) {
            return null;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AbstractC4822su0 su0 = (AbstractC4822su0) it.next();
            if (str.equals(su0.f11308a)) {
                return su0;
            }
        }
        return null;
    }

    public static Long c(String str) {
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException unused) {
            return null;
        }
    }
}
