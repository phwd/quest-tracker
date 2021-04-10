package defpackage;

import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.ContextUtils;

/* renamed from: Gw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0420Gw0 {

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f8122a;
    public Map b;
    public Map c = new HashMap();

    public C0420Gw0() {
        SharedPreferences sharedPreferences = ContextUtils.getApplicationContext().getSharedPreferences("partner_bookmarks_favicon_throttle", 0);
        this.f8122a = sharedPreferences;
        this.b = sharedPreferences.getAll();
    }

    public void a() {
        if (!this.b.equals(this.c)) {
            SharedPreferences.Editor edit = this.f8122a.edit();
            edit.clear();
            for (Map.Entry entry : this.c.entrySet()) {
                edit.putLong((String) entry.getKey(), ((Long) entry.getValue()).longValue());
            }
            edit.apply();
        }
    }

    public void b(String str, int i) {
        boolean z = true;
        if (i == 1) {
            this.c.put(str, Long.valueOf(System.currentTimeMillis() + 2592000000L));
            return;
        }
        if (!(i == 5 || i == 6)) {
            z = false;
        }
        if (!z && !c(str) && System.currentTimeMillis() < ((Long) this.b.get(str)).longValue()) {
            this.c.put(str, (Long) this.b.get(str));
        }
    }

    public boolean c(String str) {
        Long l = this.b.containsKey(str) ? (Long) this.b.get(str) : null;
        return l == null || System.currentTimeMillis() >= l.longValue();
    }
}
