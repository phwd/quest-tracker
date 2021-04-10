package defpackage;

import android.content.Intent;
import android.graphics.Bitmap;
import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.ShortcutHelper;
import org.chromium.chrome.browser.webapps.WebappRegistry;
import org.chromium.components.webapps.WebappsUtils;

/* renamed from: hV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2874hV0 extends AbstractC2032cb {
    public final /* synthetic */ Bitmap i;
    public final /* synthetic */ String j;
    public final /* synthetic */ String k;
    public final /* synthetic */ String l;
    public final /* synthetic */ String m;
    public final /* synthetic */ String n;
    public final /* synthetic */ int o;
    public final /* synthetic */ int p;
    public final /* synthetic */ long q;
    public final /* synthetic */ long r;
    public final /* synthetic */ String s;
    public final /* synthetic */ boolean t;
    public final /* synthetic */ int u;
    public final /* synthetic */ String v;

    public C2874hV0(Bitmap bitmap, String str, String str2, String str3, String str4, String str5, int i2, int i3, long j2, long j3, String str6, boolean z, int i4, String str7) {
        this.i = bitmap;
        this.j = str;
        this.k = str2;
        this.l = str3;
        this.m = str4;
        this.n = str5;
        this.o = i2;
        this.p = i3;
        this.q = j2;
        this.r = j3;
        this.s = str6;
        this.t = z;
        this.u = i4;
        this.v = str7;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        Intent a2 = ShortcutHelper.a(this.j, this.k, this.l, this.m, this.n, ShortcutHelper.b(this.i), 3, this.o, this.p, this.q, this.r, this.s.isEmpty(), this.t);
        a2.putExtra("org.chromium.chrome.browser.webapp_mac", ShortcutHelper.c(this.k));
        a2.putExtra("org.chromium.chrome.browser.webapp_source", this.u);
        return a2;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Intent intent = (Intent) obj;
        ShortcutHelper.b.a(this.v, this.i, this.t, intent);
        WebappRegistry webappRegistry = AbstractC2957hy1.f10115a;
        String str = this.j;
        C2703gV0 gv0 = new C2703gV0(intent, str);
        Objects.requireNonNull(webappRegistry);
        C2615fy1 fy1 = new C2615fy1(webappRegistry, str, gv0);
        fy1.f();
        PostTask.b(C3070if1.b, fy1.e, 0);
        if (!WebappsUtils.a()) {
            ShortcutHelper.e(this.v);
        }
    }
}
