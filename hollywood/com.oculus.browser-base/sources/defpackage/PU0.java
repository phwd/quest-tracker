package defpackage;

import android.content.SharedPreferences;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* renamed from: PU0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PU0 {

    /* renamed from: a  reason: collision with root package name */
    public C1696ag f8694a;
    public final Map b = new HashMap();

    public PU0(MU0 mu0) {
        if (this.f8694a == null) {
            this.f8694a = new C1696ag();
        }
    }

    public void a(OU0 ou0) {
        LU0 lu0 = new LU0(ou0);
        this.b.put(ou0, lu0);
        AbstractC3983nz.f10523a.registerOnSharedPreferenceChangeListener(lu0);
    }

    public boolean b(String str) {
        this.f8694a.a(str);
        return AbstractC3983nz.f10523a.contains(str);
    }

    public int c(String str) {
        this.f8694a.a(str);
        int i = AbstractC3983nz.f10523a.getInt(str, 0) + 1;
        SharedPreferences.Editor edit = AbstractC3983nz.f10523a.edit();
        edit.putInt(str, i);
        edit.apply();
        return i;
    }

    public boolean d(String str, boolean z) {
        this.f8694a.a(str);
        P21 f0 = P21.f0();
        try {
            boolean z2 = AbstractC3983nz.f10523a.getBoolean(str, z);
            f0.close();
            return z2;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public int e(String str) {
        return f(str, 0);
    }

    public int f(String str, int i) {
        this.f8694a.a(str);
        P21 f0 = P21.f0();
        try {
            int i2 = AbstractC3983nz.f10523a.getInt(str, i);
            f0.close();
            return i2;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public long g(String str) {
        return h(str, 0);
    }

    public long h(String str, long j) {
        this.f8694a.a(str);
        P21 f0 = P21.f0();
        try {
            long j2 = AbstractC3983nz.f10523a.getLong(str, j);
            f0.close();
            return j2;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public String i(String str, String str2) {
        this.f8694a.a(str);
        P21 f0 = P21.f0();
        try {
            String string = AbstractC3983nz.f10523a.getString(str, str2);
            f0.close();
            return string;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public Set j(String str) {
        return k(str, Collections.emptySet());
    }

    public Set k(String str, Set set) {
        this.f8694a.a(str);
        Set<String> stringSet = AbstractC3983nz.f10523a.getStringSet(str, set);
        if (stringSet != null) {
            return Collections.unmodifiableSet(stringSet);
        }
        return null;
    }

    public void l(String str) {
        this.f8694a.a(str);
        SharedPreferences.Editor edit = AbstractC3983nz.f10523a.edit();
        edit.remove(str);
        edit.apply();
    }

    public void m(String str, boolean z) {
        this.f8694a.a(str);
        SharedPreferences.Editor edit = AbstractC3983nz.f10523a.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public void n(String str, int i) {
        this.f8694a.a(str);
        SharedPreferences.Editor edit = AbstractC3983nz.f10523a.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public void o(String str, long j) {
        this.f8694a.a(str);
        SharedPreferences.Editor edit = AbstractC3983nz.f10523a.edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public void p(String str, String str2) {
        this.f8694a.a(str);
        SharedPreferences.Editor edit = AbstractC3983nz.f10523a.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public void q(String str, Set set) {
        this.f8694a.a(str);
        AbstractC3983nz.f10523a.edit().putStringSet(str, set).apply();
    }
}
