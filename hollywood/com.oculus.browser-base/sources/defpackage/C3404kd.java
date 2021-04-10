package defpackage;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* renamed from: kd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3404kd {

    /* renamed from: a  reason: collision with root package name */
    public Map f10291a = new HashMap();
    public Map b = new HashMap();
    public StringBuilder c = new StringBuilder();
    public String d;

    public static void a(C3404kd kdVar, String str) {
        Objects.requireNonNull(kdVar);
        if (!TextUtils.isEmpty(str)) {
            if (kdVar.c.length() > 0 && !kdVar.c.toString().endsWith(",")) {
                kdVar.c.append(",");
            }
            kdVar.c.append(str);
        }
    }

    public static C3233jd f() {
        return new C3233jd(new C3404kd(), null);
    }

    public boolean b() {
        return !TextUtils.isEmpty(e("TRIGGER_FIRST_TIME_USER")) && !TextUtils.isEmpty(e("TRIGGER_RETURNING_USER"));
    }

    public boolean c() {
        if (d("REQUEST_TRIGGER_SCRIPT") || (!TextUtils.isEmpty(e("TRIGGER_SCRIPTS_BASE64"))) || b()) {
            return true;
        }
        return false;
    }

    public final boolean d(String str) {
        Object obj = this.f10291a.get(str);
        if (!(obj instanceof Boolean)) {
            return false;
        }
        return ((Boolean) obj).booleanValue();
    }

    public final String e(String str) {
        Object obj = this.f10291a.get(str);
        if (!(obj instanceof String)) {
            return null;
        }
        try {
            return URLDecoder.decode((String) obj, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Encoding not available.", e);
        }
    }
}
