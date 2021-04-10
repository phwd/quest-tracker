package X;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.pP  reason: case insensitive filesystem */
public final class C0947pP {
    public static final JM A04 = new JM(Collections.emptyList(), new ArrayList());
    public String A00;
    public String A01;
    public String A02;
    public String A03;

    public static final String A00(C0947pP pPVar) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(pPVar.A03)) {
            sb.append(pPVar.A03);
            sb.append(':');
        }
        if (!TextUtils.isEmpty(pPVar.A00)) {
            sb.append("//");
            sb.append(pPVar.A00);
        }
        if (!TextUtils.isEmpty(pPVar.A01)) {
            sb.append(pPVar.A01);
        }
        if (!TextUtils.isEmpty(pPVar.A02)) {
            sb.append('?');
            sb.append(pPVar.A02);
        }
        return sb.toString();
    }

    public final JSONObject A01() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.A03)) {
                jSONObject.put("scheme", this.A03);
            }
            if (!TextUtils.isEmpty(this.A00)) {
                jSONObject.put("authority", this.A00);
            }
            if (!TextUtils.isEmpty(this.A01)) {
                jSONObject.put("path", this.A01);
            }
            if (!TextUtils.isEmpty(this.A02)) {
                jSONObject.put("query", this.A02);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public C0947pP(String str, String str2, String str3, String str4) {
        this.A03 = str;
        this.A00 = str2;
        this.A01 = str3;
        this.A02 = str4;
    }
}
