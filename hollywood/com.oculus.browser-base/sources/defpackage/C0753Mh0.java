package defpackage;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.chromium.services.media_session.MediaImage;
import org.chromium.services.media_session.MediaMetadata;
import org.chromium.services.media_session.MediaPosition;

/* renamed from: Mh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0753Mh0 extends AbstractC1180Th0 {
    public final /* synthetic */ C0936Ph0 b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0753Mh0(C0936Ph0 ph0, AbstractC5123uh0 uh0) {
        super(uh0);
        this.b = ph0;
    }

    @Override // defpackage.AbstractC1180Th0
    public void a(Set set) {
        C0936Ph0 ph0 = this.b;
        ph0.p = set;
        if (!ph0.i()) {
            ph0.k.n = ph0.p;
            ph0.k();
        }
    }

    @Override // defpackage.AbstractC1180Th0
    public void b(List list) {
        MediaImage mediaImage;
        double d;
        C0936Ph0 ph0 = this.b;
        C3750me0 me0 = ph0.e;
        if (me0.f10436a != null) {
            me0.d = ph0;
            if (list == null) {
                mediaImage = null;
            } else {
                Iterator it = list.iterator();
                mediaImage = null;
                double d2 = 0.0d;
                while (it.hasNext()) {
                    MediaImage mediaImage2 = (MediaImage) it.next();
                    if (mediaImage2 == null) {
                        d = 0.0d;
                    } else {
                        d = 0.4d;
                        if (!mediaImage2.c.isEmpty()) {
                            double d3 = 0.0d;
                            for (Rect rect : mediaImage2.c) {
                                d3 = Math.max(d3, me0.c(rect));
                            }
                            String str = mediaImage2.f11008a;
                            String str2 = mediaImage2.b;
                            String c = AbstractC3375kQ.c(str);
                            if ("bmp".equals(c) || "image/bmp".equals(str2)) {
                                d = 0.5d;
                            } else if ("gif".equals(c) || "image/gif".equals(str2)) {
                                d = 0.3d;
                            } else if (!"icon".equals(c) && !"image/x-icon".equals(str2)) {
                                d = ("png".equals(c) || "image/png".equals(str2)) ? 1.0d : ("jpeg".equals(c) || "jpg".equals(c) || "image/jpeg".equals(str2)) ? 0.7d : 0.6d;
                            }
                            d *= d3;
                        }
                    }
                    if (d > d2) {
                        mediaImage = mediaImage2;
                        d2 = d;
                    }
                }
            }
            if (mediaImage == null) {
                me0.e = null;
                C0936Ph0 ph02 = (C0936Ph0) me0.d;
                Objects.requireNonNull(ph02);
                Bitmap a2 = AbstractC5794ye0.a(null);
                ph02.f = a2;
                ph02.g = null;
                ph02.m(a2);
                me0.b();
            } else if (!TextUtils.equals(mediaImage.f11008a, me0.e)) {
                String str3 = mediaImage.f11008a;
                me0.e = str3;
                me0.c = me0.f10436a.P(str3, false, 2048, false, me0);
            }
        }
        C0936Ph0.a(this.b);
    }

    @Override // defpackage.AbstractC1180Th0
    public void c() {
        this.b.g();
        this.b.c();
    }

    @Override // defpackage.AbstractC1180Th0
    public void d(MediaMetadata mediaMetadata) {
        C0936Ph0 ph0 = this.b;
        ph0.n = mediaMetadata;
        C0936Ph0.a(ph0);
    }

    @Override // defpackage.AbstractC1180Th0
    public void e(MediaPosition mediaPosition) {
        C0936Ph0 ph0 = this.b;
        ph0.q = mediaPosition;
        if (!ph0.i()) {
            ph0.k.o = ph0.q;
            ph0.k();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00ea, code lost:
        if (r1 == false) goto L_0x00ec;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    @Override // defpackage.AbstractC1180Th0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void f(boolean r6, boolean r7) {
        /*
        // Method dump skipped, instructions count: 263
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0753Mh0.f(boolean, boolean):void");
    }
}
