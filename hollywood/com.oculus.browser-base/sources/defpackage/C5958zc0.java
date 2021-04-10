package defpackage;

import android.util.SparseArray;
import org.chromium.content_public.browser.WebContents;

/* renamed from: zc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5958zc0 {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f11755a = {1, 2, 3, 4};
    public final WebContents b;
    public final SparseArray c = new SparseArray();
    public C4112ol d;
    public boolean e;
    public final AbstractC6022zx1 f;

    public C5958zc0(WebContents webContents) {
        this.b = webContents;
        if (webContents == null) {
            this.f = null;
            return;
        }
        this.e = true;
        C5618xc0 xc0 = new C5618xc0(this, webContents);
        this.f = xc0;
        webContents.c0(xc0);
    }

    public void a() {
        WebContents webContents = this.b;
        if (webContents != null) {
            webContents.Q(this.f);
        }
        this.d = null;
        this.c.clear();
        this.e = false;
    }

    public final C5788yc0 b(int i) {
        C5788yc0 yc0 = (C5788yc0) this.c.get(i);
        if (yc0 != null) {
            return yc0;
        }
        this.c.put(i, new C5788yc0(null));
        return (C5788yc0) this.c.get(i);
    }
}
