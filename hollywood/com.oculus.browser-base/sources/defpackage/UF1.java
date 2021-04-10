package defpackage;

import com.google.android.gms.common.images.WebImage;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: UF1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class UF1 {

    /* renamed from: a  reason: collision with root package name */
    public static final NF1 f9016a = new NF1("MetadataUtils");
    public static final String[] b;
    public static final String c;

    static {
        String[] strArr = {"Z", "+hh", "+hhmm", "+hh:mm"};
        b = strArr;
        String valueOf = String.valueOf(strArr[0]);
        c = valueOf.length() != 0 ? "yyyyMMdd'T'HHmmss".concat(valueOf) : new String("yyyyMMdd'T'HHmmss");
    }

    public static void a(List list, JSONArray jSONArray) {
        try {
            list.clear();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    list.add(new WebImage(jSONArray.getJSONObject(i)));
                } catch (IllegalArgumentException unused) {
                }
            }
        } catch (JSONException unused2) {
        }
    }

    public static JSONArray b(List list) {
        if (list == null && list.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WebImage webImage = (WebImage) it.next();
            Objects.requireNonNull(webImage);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("url", webImage.G.toString());
                jSONObject.put("width", webImage.H);
                jSONObject.put("height", webImage.I);
            } catch (JSONException unused) {
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x015e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Calendar c(java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 393
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.UF1.c(java.lang.String):java.util.Calendar");
    }
}
