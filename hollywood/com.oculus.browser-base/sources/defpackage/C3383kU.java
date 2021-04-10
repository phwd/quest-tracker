package defpackage;

import android.os.Bundle;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.json.JSONObject;

/* renamed from: kU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3383kU {

    /* renamed from: a  reason: collision with root package name */
    public final String f10282a;
    public final String b;
    public final String c;
    public final String d;
    public final byte[] e;
    public final String f;
    public final String[] g;

    public C3383kU(String str, Bundle bundle) {
        if (bundle.containsKey("subtype")) {
            this.f10282a = str;
            this.b = bundle.getString("subtype");
            this.d = bundle.getString("collapse_key");
            this.e = bundle.getByteArray("rawData");
            this.f = bundle.getString("google.original_priority");
            this.c = bundle.getString("google.message_id");
            ArrayList arrayList = new ArrayList();
            for (String str2 : bundle.keySet()) {
                if (!str2.equals("subtype") && !str2.equals("from") && !str2.equals("collapse_key") && !str2.equals("rawData") && !str2.equals("google.original_priority") && !str2.startsWith("com.google.ipc.invalidation.gcmmplex.") && !str2.equals("google.message_id")) {
                    Object obj = bundle.get(str2);
                    if (obj instanceof String) {
                        arrayList.add(str2);
                        arrayList.add((String) obj);
                    }
                }
            }
            this.g = (String[]) arrayList.toArray(new String[arrayList.size()]);
            return;
        }
        throw new IllegalArgumentException("Received push message with no subtype");
    }

    public static C3383kU a(Object obj, AbstractC3042iU iUVar) {
        if (!(iUVar.c(obj, "appId") && iUVar.c(obj, "collapseKey") && iUVar.c(obj, "data") && iUVar.c(obj, "rawData") && iUVar.c(obj, "senderId") && iUVar.c(obj, "originalPriority") && iUVar.c(obj, "messageId")) || iUVar.a(obj, "appId") == null || iUVar.a(obj, "senderId") == null) {
            return null;
        }
        return new C3383kU(obj, iUVar);
    }

    public static String c(JSONObject jSONObject) {
        return jSONObject.optString("senderId", null);
    }

    public int b() {
        String str = this.f;
        if (str == null) {
            return 0;
        }
        str.hashCode();
        if (str.equals("normal")) {
            return 1;
        }
        if (!str.equals("high")) {
            return 0;
        }
        return 2;
    }

    public final Object d(AbstractC3212jU jUVar) {
        Object a2 = jUVar.a();
        jUVar.c(a2, "version", "v1");
        jUVar.c(a2, "senderId", this.f10282a);
        jUVar.c(a2, "appId", this.b);
        jUVar.c(a2, "collapseKey", this.d);
        jUVar.c(a2, "originalPriority", this.f);
        jUVar.c(a2, "messageId", this.c);
        byte[] bArr = this.e;
        if (bArr == null) {
            jUVar.c(a2, "rawData", null);
        } else if (bArr.length > 0) {
            jUVar.c(a2, "rawData", new String(this.e, Charset.forName("ISO-8859-1")));
        } else {
            jUVar.c(a2, "rawData", "");
        }
        jUVar.b(a2, "data", this.g);
        return a2;
    }

    public C3383kU(Object obj, AbstractC3042iU iUVar) {
        this.f10282a = iUVar.a(obj, "senderId");
        this.b = iUVar.a(obj, "appId");
        this.d = iUVar.a(obj, "collapseKey");
        this.f = iUVar.a(obj, "originalPriority");
        this.c = iUVar.a(obj, "messageId");
        String a2 = iUVar.a(obj, "rawData");
        if (a2 == null) {
            this.e = null;
        } else if (a2.length() > 0) {
            this.e = a2.getBytes(Charset.forName("ISO-8859-1"));
        } else {
            this.e = new byte[0];
        }
        this.g = iUVar.b(obj, "data");
    }
}
