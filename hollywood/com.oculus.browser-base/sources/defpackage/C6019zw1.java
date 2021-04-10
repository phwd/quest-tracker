package defpackage;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: zw1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C6019zw1 extends AbstractC2032cb {
    public final /* synthetic */ String i;
    public final /* synthetic */ Bw1 j;

    public C6019zw1(Bw1 bw1, String str) {
        this.j = bw1;
        this.i = str;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        TraceEvent j0 = TraceEvent.j0("WarmupManager.prefetchDnsForUrlInBackground");
        try {
            InetAddress.getByName(new URL(this.i).getHost());
            if (j0 == null) {
                return null;
            }
            try {
                j0.close();
                return null;
            } catch (MalformedURLException | UnknownHostException unused) {
                return null;
            }
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Void r3 = (Void) obj;
        this.j.b.remove(this.i);
        if (this.j.c.containsKey(this.i)) {
            this.j.c.remove(this.i);
            this.j.c((Profile) this.j.c.get(this.i), this.i);
        }
    }
}
