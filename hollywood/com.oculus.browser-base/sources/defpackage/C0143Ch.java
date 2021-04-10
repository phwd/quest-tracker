package defpackage;

import android.graphics.Bitmap;
import android.os.Looper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;

/* renamed from: Ch  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0143Ch {

    /* renamed from: a  reason: collision with root package name */
    public static Map f7829a = new HashMap();
    public static int b;
    public final int c;
    public OF d;
    public final PF e;

    public C0143Ch(PF pf, int i) {
        Object obj = ThreadUtils.f10596a;
        this.e = pf;
        this.c = i;
        OF of = new OF(new C0082Bh(i, null), null);
        pf.f8679a.add(of);
        this.d = of;
    }

    public static void c() {
        int i = b + 1;
        b = i;
        if (i >= f7829a.size()) {
            b = 0;
            Looper.myQueue().addIdleHandler(new C5972zh());
        }
    }

    public Bitmap a(String str) {
        Object obj = ThreadUtils.f10596a;
        if (this.d == null) {
            return null;
        }
        Bitmap bitmap = (Bitmap) b().b(str);
        c();
        return bitmap;
    }

    public final C0082Bh b() {
        C0082Bh bh = (C0082Bh) this.d.f8611a;
        if (bh != null) {
            return bh;
        }
        C0082Bh bh2 = new C0082Bh(this.c, null);
        PF pf = this.e;
        OF of = new OF(bh2, null);
        pf.f8679a.add(of);
        this.d = of;
        return bh2;
    }

    public void d(String str, Bitmap bitmap) {
        Object obj = ThreadUtils.f10596a;
        if (this.d != null) {
            if (!SysUtils.isLowEndDevice()) {
                b().c(str, bitmap);
            }
            c();
            f7829a.put(str, new WeakReference(bitmap));
        }
    }
}
