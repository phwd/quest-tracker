package defpackage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.ContextUtils;

/* renamed from: M20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class M20 {

    /* renamed from: a  reason: collision with root package name */
    public static final Map f8458a = new HashMap();
    public static final Object b = new Object();
    public final D20 c;

    public M20(D20 d20) {
        this.c = d20;
    }

    public static M20 a(String str) {
        M20 m20;
        if (!TextUtils.isEmpty(str)) {
            synchronized (b) {
                Map map = f8458a;
                m20 = (M20) map.get(str);
                if (m20 == null) {
                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("subtype", str);
                    M20 m202 = new M20(D20.b(ContextUtils.getApplicationContext(), bundle));
                    map.put(str, m202);
                    m20 = m202;
                }
            }
            return m20;
        }
        throw new IllegalArgumentException("subtype must not be empty");
    }

    public String b(String str, String str2) {
        String str3;
        String string;
        long j;
        D20 d20 = this.c;
        Objects.requireNonNull(d20);
        if (Looper.getMainLooper() != Looper.myLooper()) {
            C4881tD1 td1 = D20.d;
            synchronized (td1) {
                str3 = null;
                string = td1.f11331a.getString("appVersion", null);
            }
            boolean z = true;
            if (string != null && string.equals(D20.f)) {
                C4881tD1 td12 = D20.d;
                String str4 = d20.h;
                synchronized (td12) {
                    j = td12.f11331a.getLong(C4881tD1.b(str4, str, str2), -1);
                }
                if (j >= 0 && System.currentTimeMillis() - j < D20.c) {
                    z = false;
                }
            }
            if (!z) {
                C4881tD1 td13 = D20.d;
                String str5 = d20.h;
                synchronized (td13) {
                    str3 = td13.f11331a.getString(C4881tD1.a(str5, str, str2), null);
                }
            }
            if (str3 == null) {
                Bundle bundle = new Bundle();
                if (str2 != null) {
                    bundle.putString("scope", str2);
                }
                bundle.putString("sender", str);
                String str6 = "".equals(d20.h) ? str : d20.h;
                if (!bundle.containsKey("legacy.register")) {
                    bundle.putString("subscription", str);
                    bundle.putString("subtype", str6);
                    bundle.putString("X-subscription", str);
                    bundle.putString("X-subtype", str6);
                }
                str3 = YC1.g(D20.e.a(bundle, d20.c()));
                if ("RST".equals(str3) || str3.startsWith("RST|")) {
                    L20.b(d20.g, D20.d);
                    throw new IOException("SERVICE_NOT_AVAILABLE");
                }
                Objects.requireNonNull(D20.f7857a);
                if (!str3.contains(":") || str3.startsWith(String.valueOf(D20.d(d20.c())).concat(":"))) {
                    C4881tD1 td14 = D20.d;
                    String str7 = d20.h;
                    String str8 = D20.f;
                    synchronized (td14) {
                        String a2 = C4881tD1.a(str7, str, str2);
                        String b2 = C4881tD1.b(str7, str, str2);
                        SharedPreferences.Editor edit = td14.f11331a.edit();
                        edit.putString(a2, str3);
                        edit.putLong(b2, System.currentTimeMillis());
                        edit.putString("appVersion", str8);
                        edit.commit();
                    }
                } else {
                    L20.b(d20.g, D20.d);
                    throw new IOException("SERVICE_NOT_AVAILABLE");
                }
            }
            return str3;
        }
        throw new IOException("MAIN_THREAD");
    }
}
