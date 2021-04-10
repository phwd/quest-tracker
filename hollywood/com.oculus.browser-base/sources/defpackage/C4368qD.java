package defpackage;

import android.content.ContentResolver;
import android.net.Uri;
import java.util.List;
import java.util.Locale;

/* renamed from: qD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4368qD extends AbstractC2032cb {
    public AbstractC4197pD i;
    public Uri j;
    public int k;
    public boolean l;
    public int m;
    public long n;
    public ContentResolver o;
    public int p;
    public String q;
    public float r;

    public C4368qD(AbstractC4197pD pDVar, ContentResolver contentResolver, Uri uri, int i2, boolean z, int i3, long j2) {
        this.i = pDVar;
        this.o = contentResolver;
        this.j = uri;
        this.k = i2;
        this.l = z;
        this.m = i3;
        this.n = j2;
    }

    public static String m(Long l2) {
        if (l2 == null) {
            return null;
        }
        long longValue = l2.longValue() / 1000;
        long j2 = longValue / 3600;
        long j3 = longValue - (3600 * j2);
        long j4 = j3 / 60;
        long j5 = j3 - (60 * j4);
        if (j2 > 0) {
            return String.format(Locale.US, "%d:%02d:%02d", Long.valueOf(j2), Long.valueOf(j4), Long.valueOf(j5));
        }
        return String.format(Locale.US, "%d:%02d", Long.valueOf(j4), Long.valueOf(j5));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c2, code lost:
        if (r5 != null) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c4, code lost:
        r5.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00cd, code lost:
        if (r5 != null) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00d4, code lost:
        if (r5 != null) goto L_0x00c4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ad A[SYNTHETIC, Splitter:B:35:0x00ad] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00dc  */
    @Override // defpackage.AbstractC2032cb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object c() {
        /*
        // Method dump skipped, instructions count: 224
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4368qD.c():java.lang.Object");
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        List list = (List) obj;
        if (!h()) {
            if (list == null) {
                ((BinderC5899zD) this.i).e0(this.j, null, "", this.l, this.p, 1.0f);
                return;
            }
            ((BinderC5899zD) this.i).e0(this.j, list, this.q, this.l, this.p, this.r);
        }
    }
}
