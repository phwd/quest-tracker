package defpackage;

import android.util.LruCache;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: c9  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1964c9 {

    /* renamed from: a  reason: collision with root package name */
    public LruCache f9586a;
    public AbstractC1099Sa1 b;

    public C1964c9(AbstractC0124Ca1 ca1) {
        if (ca1 != null && (!SysUtils.isLowEndDevice())) {
            this.b = new C1613a9(this, ca1);
        }
    }

    public final LruCache a() {
        if (this.f9586a == null) {
            this.f9586a = new LruCache(100);
        }
        return this.f9586a;
    }

    public boolean b(Tab tab) {
        boolean d = AbstractC5154ur1.d(tab.s());
        if (!(!SysUtils.isLowEndDevice()) || tab.a() || !d) {
            return false;
        }
        return true;
    }
}
