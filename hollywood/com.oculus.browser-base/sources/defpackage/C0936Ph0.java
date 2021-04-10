package defpackage;

import J.N;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.SysUtils;
import org.chromium.content.browser.MediaSessionImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.services.media_session.MediaMetadata;
import org.chromium.services.media_session.MediaPosition;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Ph0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0936Ph0 implements AbstractC3579le0 {

    /* renamed from: a  reason: collision with root package name */
    public C1302Vh0 f8706a;
    public WebContents b;
    public AbstractC6022zx1 c;
    public AbstractC1180Th0 d;
    public C3750me0 e;
    public Bitmap f;
    public Bitmap g;
    public Bitmap h;
    public String i;
    public int j = Integer.MIN_VALUE;
    public C0013Ae0 k;
    public String l;
    public boolean m;
    public MediaMetadata n;
    public MediaMetadata o;
    public Set p = Collections.emptySet();
    public MediaPosition q;
    public Handler r;
    public Runnable s;
    public X60 t;
    public AbstractC0135Ce0 u = new C0632Kh0(this);

    public C0936Ph0(WebContents webContents, C1302Vh0 vh0) {
        this.f8706a = vh0;
        this.e = new C3750me0(114, AbstractC5794ye0.b());
        this.r = new Handler();
        j(webContents);
        Activity e2 = e();
        if (e2 != null) {
            this.j = e2.getVolumeControlStream();
        }
    }

    public static void a(C0936Ph0 ph0) {
        if (!ph0.i()) {
            MediaMetadata f2 = ph0.f();
            if (!ph0.o.equals(f2)) {
                ph0.o = f2;
                ph0.k.f7683a = f2;
                ph0.k();
            }
        }
    }

    public static String b(C0936Ph0 ph0, String str) {
        Objects.requireNonNull(ph0);
        String trim = str.trim();
        return trim.startsWith("▶") ? trim.substring(1).trim() : trim;
    }

    public static Integer d(int i2) {
        if (i2 == 1000) {
            return 0;
        }
        if (i2 == 1001) {
            return 1;
        }
        return i2 == 1002 ? 2 : null;
    }

    public final void c() {
        AbstractC1180Th0 th0 = this.d;
        if (th0 != null) {
            th0.g();
            this.d = null;
            this.p = Collections.emptySet();
        }
    }

    public final Activity e() {
        WindowAndroid I = this.b.I();
        if (I == null) {
            return null;
        }
        return (Activity) I.s0().get();
    }

    public final MediaMetadata f() {
        String str;
        String str2 = this.l;
        MediaMetadata mediaMetadata = this.n;
        String str3 = "";
        if (mediaMetadata == null) {
            str = str3;
        } else if (!TextUtils.isEmpty(mediaMetadata.f11009a)) {
            return this.n;
        } else {
            MediaMetadata mediaMetadata2 = this.n;
            str3 = mediaMetadata2.b;
            str = mediaMetadata2.c;
        }
        MediaMetadata mediaMetadata3 = this.o;
        if (mediaMetadata3 == null || !TextUtils.equals(str2, mediaMetadata3.f11009a) || !TextUtils.equals(str3, this.o.b) || !TextUtils.equals(str, this.o.c)) {
            return new MediaMetadata(str2, str3, str);
        }
        return this.o;
    }

    public final void g() {
        if (this.c != null) {
            Runnable runnable = this.s;
            if (runnable != null) {
                this.r.removeCallbacks(runnable);
                this.s = null;
            }
            h();
            this.k = null;
        }
    }

    public final void h() {
        C0074Be0 be0;
        int id = this.f8706a.f9100a.getId();
        C5624xe0 a2 = AbstractC0196De0.a(R.id.media_playback_notification);
        if (!(a2 == null || (be0 = a2.e) == null || id != be0.e)) {
            a2.b();
        }
        Activity e2 = e();
        if (e2 != null) {
            e2.setVolumeControlStream(this.j);
        }
    }

    public final boolean i() {
        return this.k == null;
    }

    public void j(WebContents webContents) {
        if (this.b != webContents) {
            this.b = webContents;
            AbstractC6022zx1 zx1 = this.c;
            if (zx1 != null) {
                zx1.destroy();
            }
            this.c = new C0814Nh0(this, webContents, webContents);
            MediaSessionImpl mediaSessionImpl = (MediaSessionImpl) N.Mtun$qW8(webContents);
            AbstractC1180Th0 th0 = this.d;
            if (th0 == null || mediaSessionImpl != th0.f8975a) {
                c();
                C3750me0 me0 = this.e;
                me0.f10436a = webContents;
                me0.b();
                if (mediaSessionImpl != null) {
                    this.d = new C0753Mh0(this, mediaSessionImpl);
                }
            }
        }
    }

    public final void k() {
        Runnable runnable = this.s;
        if (runnable != null) {
            this.r.removeCallbacks(runnable);
            this.s = null;
        }
        C1302Vh0 vh0 = this.f8706a;
        C0074Be0 a2 = this.k.a();
        Objects.requireNonNull(vh0);
        AbstractC1384Wr.a(a2);
    }

    public void l(Bitmap bitmap) {
        if (bitmap != null) {
            boolean z = true;
            this.m = true;
            if (!i() && this.f == null) {
                if (Build.VERSION.SDK_INT < 26 || !SysUtils.isLowEndDevice()) {
                    if (bitmap.getWidth() < 114 || bitmap.getHeight() < 114) {
                        z = false;
                    }
                    if (z) {
                        if (this.g == null || (bitmap.getWidth() >= this.g.getWidth() && bitmap.getHeight() >= this.g.getHeight())) {
                            Bitmap a2 = AbstractC5794ye0.a(bitmap);
                            this.g = a2;
                            m(a2);
                        }
                    }
                }
            }
        }
    }

    public final void m(Bitmap bitmap) {
        if (this.h != bitmap) {
            this.h = bitmap;
            if (!i()) {
                C0013Ae0 ae0 = this.k;
                ae0.g = this.h;
                ae0.i = this.f;
                k();
            }
        }
    }
}
