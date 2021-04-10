package defpackage;

import org.json.JSONObject;

/* renamed from: TX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TX0 {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f8963a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public String i;

    public TX0(C1523Yz0 yz0, C2875hW hWVar) {
        boolean z = true;
        this.f8963a = yz0 != null && yz0.f;
        this.b = yz0 != null && yz0.d;
        this.c = yz0 != null && yz0.e;
        this.d = (yz0 == null || !yz0.g) ? false : z;
        this.e = hWVar.e;
        this.f = hWVar.f;
        this.g = hWVar.g;
        this.h = hWVar.h;
    }

    public final void a(JSONObject jSONObject, C2825hB0 hb0) {
        JSONObject optJSONObject = jSONObject == null ? null : jSONObject.optJSONObject("billingAddress");
        if (optJSONObject != null) {
            if (this.b) {
                hb0.h.e = optJSONObject.optString("name");
            }
            if (this.f8963a) {
                hb0.h.f = optJSONObject.optString("phoneNumber");
            }
            if (this.f) {
                jSONObject.remove("billingAddress");
            } else if (this.e) {
                optJSONObject.remove("phoneNumber");
            }
        }
    }
}
