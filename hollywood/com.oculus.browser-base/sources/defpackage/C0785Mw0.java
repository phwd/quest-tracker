package defpackage;

import J.N;
import android.content.Context;
import java.util.HashSet;
import java.util.Set;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;

/* renamed from: Mw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0785Mw0 {

    /* renamed from: a  reason: collision with root package name */
    public static Set f8512a = new HashSet();
    public C0420Gw0 b;
    public long c;
    public Context d;
    public final Object e = new Object();
    public int f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;

    public C0785Mw0(Context context, PartnerBrowserCustomizations partnerBrowserCustomizations) {
        this.d = context;
        this.c = N.MQQiT1PE(this);
        if (!partnerBrowserCustomizations.e) {
            partnerBrowserCustomizations.d(context);
        }
        partnerBrowserCustomizations.f(new RunnableC0542Iw0(this, partnerBrowserCustomizations));
    }

    public void a() {
        boolean z = true;
        this.i = true;
        if (this.j) {
            N.MZy4XMIu(this.c, this);
        }
        synchronized (this.e) {
            if (this.f != 0 || !this.i || !this.j) {
                z = false;
            }
            if (z) {
                b();
            }
        }
    }

    public void b() {
        synchronized (this.e) {
            if (!this.g) {
                C0420Gw0 gw0 = this.b;
                if (gw0 != null) {
                    gw0.a();
                }
                if (this.h) {
                    for (AbstractC0664Kw0 kw0 : f8512a) {
                        kw0.b();
                    }
                }
                N.Mp38r97L(this.c, this);
                this.c = 0;
                this.g = true;
            }
        }
    }
}
