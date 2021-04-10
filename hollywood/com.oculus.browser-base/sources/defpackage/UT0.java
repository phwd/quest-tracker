package defpackage;

import android.net.Uri;
import org.chromium.base.ApplicationStatus;

/* renamed from: UT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UT0 extends AbstractC2032cb {
    public final /* synthetic */ String i;
    public final /* synthetic */ YT0 j;
    public final /* synthetic */ boolean k;
    public final /* synthetic */ String l;
    public final /* synthetic */ XT0 m;
    public final /* synthetic */ ZT0 n;

    public UT0(String str, YT0 yt0, boolean z, String str2, XT0 xt0, ZT0 zt0) {
        this.i = str;
        this.j = yt0;
        this.k = z;
        this.l = str2;
        this.m = xt0;
        this.n = zt0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.io.Closeable, java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0030, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0032 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0002] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00d8  */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // defpackage.AbstractC2032cb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object c() {
        /*
        // Method dump skipped, instructions count: 228
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.UT0.c():java.lang.Object");
    }

    @Override // defpackage.AbstractC2032cb
    public void i() {
        this.n.b(this.i);
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Uri uri = (Uri) obj;
        if (uri == null) {
            this.n.b(this.i);
        } else if (ApplicationStatus.getStateForApplication() != 4) {
            this.n.a(uri, this.i);
        }
    }
}
