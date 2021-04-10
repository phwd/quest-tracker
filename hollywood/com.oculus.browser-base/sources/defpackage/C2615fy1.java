package defpackage;

import org.chromium.chrome.browser.webapps.WebappRegistry;

/* renamed from: fy1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2615fy1 extends AbstractC2032cb {
    public final /* synthetic */ String i;
    public final /* synthetic */ AbstractC2786gy1 j;
    public final /* synthetic */ WebappRegistry k;

    public C2615fy1(WebappRegistry webappRegistry, String str, AbstractC2786gy1 gy1) {
        this.k = webappRegistry;
        this.i = str;
        this.j = gy1;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        Xx1 xx1 = new Xx1(this.i);
        xx1.b();
        return xx1;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Xx1 xx1 = (Xx1) obj;
        this.k.b.put(this.i, xx1);
        this.k.c.edit().putStringSet("webapp_set", this.k.b.keySet()).apply();
        xx1.c.edit().putLong("last_used", System.currentTimeMillis()).apply();
        AbstractC2786gy1 gy1 = this.j;
        if (gy1 != null) {
            gy1.a(xx1);
        }
    }
}
