package defpackage;

import android.graphics.Bitmap;
import android.graphics.Rect;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.content_public.browser.ImageDownloadCallback;
import org.chromium.content_public.browser.WebContents;

/* renamed from: me0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3750me0 implements ImageDownloadCallback {

    /* renamed from: a  reason: collision with root package name */
    public WebContents f10436a;
    public final int b;
    public int c;
    public AbstractC3579le0 d;
    public String e;

    public C3750me0(int i, int i2) {
        this.b = i2;
        b();
    }

    @Override // org.chromium.content_public.browser.ImageDownloadCallback
    public void a(int i, int i2, String str, List list, List list2) {
        if (i == this.c) {
            Iterator it = list.iterator();
            Iterator it2 = list2.iterator();
            double d2 = 0.0d;
            Bitmap bitmap = null;
            while (it.hasNext() && it2.hasNext()) {
                Bitmap bitmap2 = (Bitmap) it.next();
                double c2 = c((Rect) it2.next());
                if (d2 < c2) {
                    bitmap = bitmap2;
                    d2 = c2;
                }
            }
            C0936Ph0 ph0 = (C0936Ph0) this.d;
            Objects.requireNonNull(ph0);
            Bitmap a2 = AbstractC5794ye0.a(bitmap);
            ph0.f = a2;
            ph0.g = null;
            ph0.m(a2);
            b();
        }
    }

    public final void b() {
        this.c = -1;
        this.d = null;
    }

    public final double c(Rect rect) {
        int max = Math.max(rect.width(), rect.height());
        double d2 = 0.8d;
        if (max != 0) {
            if (max < 114) {
                d2 = 0.0d;
            } else {
                int i = this.b;
                d2 = max <= i ? ((((double) (max - 114)) * 0.8d) / ((double) (i - 114))) + 0.2d : (((double) i) * 1.0d) / ((double) max);
            }
        }
        int width = rect.width();
        int height = rect.height();
        return d2 * (((double) Math.min(width, height)) / ((double) Math.max(width, height)));
    }
}
