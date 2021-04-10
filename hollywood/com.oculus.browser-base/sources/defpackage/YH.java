package defpackage;

import J.N;
import android.os.Build;
import org.chromium.base.ContentUriUtils;
import org.chromium.chrome.browser.download.DownloadManagerBridge;

/* renamed from: YH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YH extends AbstractC2032cb {
    public final /* synthetic */ String i;
    public final /* synthetic */ String j;
    public final /* synthetic */ String k;
    public final /* synthetic */ String l;
    public final /* synthetic */ long m;
    public final /* synthetic */ String n;
    public final /* synthetic */ String o;
    public final /* synthetic */ String p;
    public final /* synthetic */ long q;

    public YH(String str, String str2, String str3, String str4, long j2, String str5, String str6, String str7, long j3) {
        this.i = str;
        this.j = str2;
        this.k = str3;
        this.l = str4;
        this.m = j2;
        this.n = str5;
        this.o = str6;
        this.p = str7;
        this.q = j3;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        return Long.valueOf((ContentUriUtils.e(this.i) || Build.VERSION.SDK_INT >= 29) ? -1 : DownloadManagerBridge.a(this.j, this.k, this.l, this.i, this.m, this.n, this.o, this.p));
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        N.Mct0JWyi(this.q, ((Long) obj).longValue());
    }
}
