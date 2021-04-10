package defpackage;

import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.ContextUtils;

/* renamed from: go1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2756go1 {

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f10023a;

    public C2756go1() {
        P21 f0 = P21.f0();
        try {
            this.f10023a = ContextUtils.getApplicationContext().getSharedPreferences("twa_permission_registry", 0);
            f0.close();
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public Boolean a(int i, C4649rt0 rt0) {
        String d = d(i, rt0);
        if (!this.f10023a.contains(d)) {
            return null;
        }
        return Boolean.valueOf(this.f10023a.getBoolean(d, false));
    }

    public final String b(C4649rt0 rt0) {
        StringBuilder i = AbstractC2531fV.i("app_name.");
        i.append(rt0.toString());
        return i.toString();
    }

    public final String c(C4649rt0 rt0) {
        StringBuilder i = AbstractC2531fV.i("package_name.");
        i.append(rt0.toString());
        return i.toString();
    }

    public final String d(int i, C4649rt0 rt0) {
        String str;
        StringBuilder sb = new StringBuilder();
        if (i == 5) {
            str = "geolocation_permission.";
        } else if (i == 6) {
            str = "notification_permission.";
        } else {
            throw new IllegalStateException("Unsupported permission type.");
        }
        sb.append(str);
        sb.append(rt0.toString());
        return sb.toString();
    }

    public Set e() {
        P21 f0 = P21.f0();
        try {
            HashSet hashSet = new HashSet(this.f10023a.getStringSet("origins", new HashSet()));
            f0.close();
            return hashSet;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
