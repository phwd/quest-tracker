package X;

import android.app.Application;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: X.wu  reason: case insensitive filesystem */
public class C1300wu implements X1 {
    public static final C0412Wp A03 = new C0412Wp();
    public String A00 = "system-dialog";
    public final Bundle A01;
    public final List A02 = new ArrayList();

    public void A05() {
    }

    public void A06() {
    }

    public void A07() {
    }

    public void A08() {
    }

    public JSONObject A04() {
        JSONObject jSONObject = new JSONObject();
        Bundle bundle = this.A01;
        for (String str : bundle.keySet()) {
            jSONObject.put(str, bundle.get(str));
        }
        List<XA> list = this.A02;
        if (!list.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            for (XA xa : list) {
                jSONArray.put(xa.A00());
            }
            jSONObject.put("components", jSONArray);
        }
        return jSONObject;
    }

    public final void A0D(XA xa) {
        C0514bB.A02(xa, "component");
        this.A02.add(xa);
    }

    public void A0E(String str) {
        C0514bB.A02(str, "<set-?>");
        this.A00 = str;
    }

    public final void A0F(String str) {
        C0514bB.A02(str, "filename");
        Application A002 = BX.A00();
        C0514bB.A01(A002, "ApplicationHolder.get()");
        this.A01.putString("hero", AnonymousClass08.A06("apk://", A002.getPackageName(), "/assets/", str));
    }

    public final void A0G(String str) {
        this.A01.putString("id", str);
    }

    @Override // X.X1
    public boolean A42(String str, String str2, Bundle bundle) {
        C0514bB.A02(str, "id");
        C0514bB.A02(str2, "action");
        if (!C0514bB.A05(this.A01.getString("id"), str)) {
            return false;
        }
        switch (str2.hashCode()) {
            case -1174796206:
                if (!str2.equals("tertiary")) {
                    return true;
                }
                A08();
                return true;
            case -817598092:
                if (!str2.equals("secondary")) {
                    return true;
                }
                A07();
                return true;
            case -314765822:
                if (!str2.equals("primary")) {
                    return true;
                }
                A06();
                return true;
            case 3015911:
                if (!str2.equals("back") || !(this instanceof C0333Rn)) {
                    return true;
                }
                C0112Aq.A00().A01(new C1284we(AnonymousClass09.A0C, false));
                return true;
            case 3202370:
                if (!str2.equals("hide")) {
                    return true;
                }
                A05();
                return true;
            default:
                return true;
        }
    }

    public C1300wu() {
        Bundle bundle = new Bundle();
        this.A01 = bundle;
        bundle.putString("id", UUID.randomUUID().toString());
    }

    public static final String A02(int i) {
        Application A002 = BX.A00();
        C0514bB.A01(A002, "ApplicationHolder.get()");
        String string = A002.getResources().getString(i);
        C0514bB.A01(string, "res.getString(stringResId)");
        return string;
    }

    public String A03() {
        return this.A00;
    }

    public final void A09(int i) {
        this.A01.putString("description", A02(i));
    }

    public final void A0A(int i) {
        this.A01.putString("primary", A02(i));
    }

    public final void A0B(int i) {
        this.A01.putString("secondary", A02(i));
    }

    public final void A0C(int i) {
        this.A01.putString("title", A02(i));
    }
}
