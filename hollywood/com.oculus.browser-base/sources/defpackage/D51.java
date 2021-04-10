package defpackage;

import J.N;
import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Handler;
import com.oculus.browser.R;
import java.util.Iterator;
import java.util.Objects;
import java.util.PriorityQueue;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.download.MimeUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: D51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class D51 {

    /* renamed from: a  reason: collision with root package name */
    public final PriorityQueue f7863a = new PriorityQueue(5, new C5877z51());
    public Handler b;
    public C5234vI c;
    public boolean d;

    public void a(C51 c51) {
        long k = k(c51.b.z);
        if (k > 0) {
            c51.d = k;
        }
        if (this.d) {
            this.f7863a.add(c51);
            return;
        }
        this.d = true;
        l(c51);
        if (this.b == null) {
            this.b = new Handler();
        }
        this.b.postDelayed(new A51(this), 220);
    }

    public C5234vI b() {
        if (this.c == null) {
            this.c = AbstractC5064uI.f11404a;
        }
        return this.c;
    }

    public final void c() {
        C51 c51 = (C51) this.f7863a.poll();
        if (c51 != null) {
            l(c51);
            if (this.b == null) {
                this.b = new Handler();
            }
            this.b.postDelayed(new B51(this), 220);
            return;
        }
        this.d = false;
    }

    public void d(C0788My my) {
        k(my);
        b().e(my);
    }

    public void e(DownloadInfo downloadInfo) {
        if (downloadInfo.H == null) {
            a(new C51(3, downloadInfo, 0));
        }
    }

    public void f(DownloadInfo downloadInfo, boolean z, int i) {
        if (downloadInfo.H == null) {
            C51 c51 = new C51(4, downloadInfo, 0);
            c51.j = z;
            c51.k = i;
            a(c51);
        }
    }

    public void g(DownloadInfo downloadInfo) {
        if (downloadInfo.H == null) {
            a(new C51(1, downloadInfo, 0));
        }
    }

    public void h(DownloadInfo downloadInfo, long j, boolean z) {
        if (downloadInfo.H == null) {
            C51 c51 = new C51(0, downloadInfo, 1);
            c51.e = j;
            c51.i = z;
            a(c51);
        }
    }

    public void i(DownloadInfo downloadInfo, long j, boolean z, boolean z2) {
        if (downloadInfo.H == null) {
            C51 c51 = new C51(2, downloadInfo, 0);
            c51.f = j;
            c51.g = z;
            c51.h = z2;
            a(c51);
        }
    }

    public void j(int i, DownloadInfo downloadInfo) {
        k(downloadInfo.z);
        C5234vI b2 = b();
        C0788My my = downloadInfo.z;
        ((C0771Mp0) b2.b).b.cancel(i);
        b2.d.c(my);
        b2.f11472a.remove(my);
    }

    public final long k(C0788My my) {
        Iterator it = this.f7863a.iterator();
        while (it.hasNext()) {
            C51 c51 = (C51) it.next();
            if (c51.b.z.equals(my)) {
                long j = c51.d;
                it.remove();
                return j;
            }
        }
        return -1;
    }

    public final void l(C51 c51) {
        boolean z;
        boolean z2;
        int i;
        String str;
        boolean z3;
        String str2;
        Context context;
        Profile profile;
        DownloadInfo downloadInfo = c51.b;
        int i2 = c51.f7784a;
        if (i2 == 0) {
            b().j(downloadInfo.z, downloadInfo.e, downloadInfo.p, downloadInfo.q, c51.e, downloadInfo.t, c51.i, downloadInfo.B, downloadInfo.D, downloadInfo.i, downloadInfo.G, 0);
        } else if (i2 == 1) {
            b().g(downloadInfo.z, downloadInfo.e, true, false, downloadInfo.t, downloadInfo.B, downloadInfo.D, downloadInfo.i, downloadInfo.G, false, true, downloadInfo.E);
        } else if (i2 == 2) {
            C5234vI b2 = b();
            C0788My my = downloadInfo.z;
            String str3 = downloadInfo.g;
            String str4 = downloadInfo.e;
            long j = c51.f;
            boolean z4 = downloadInfo.t;
            boolean z5 = c51.h;
            boolean z6 = downloadInfo.A;
            Bitmap bitmap = downloadInfo.D;
            String str5 = downloadInfo.i;
            boolean z7 = downloadInfo.G;
            String str6 = downloadInfo.h;
            long j2 = downloadInfo.k;
            Objects.requireNonNull(b2);
            Context applicationContext = ContextUtils.getApplicationContext();
            int d2 = b2.d(my);
            boolean z8 = bitmap == null || z4;
            if (b2.c != null || !z8) {
                context = applicationContext;
                str2 = str6;
                i = d2;
                z2 = z5;
                z = z6;
                str = str5;
                z3 = z7;
            } else {
                Bitmap decodeResource = BitmapFactory.decodeResource(applicationContext.getResources(), R.drawable.f34330_resource_name_obfuscated_RES_2131231473);
                Resources resources = ContextUtils.getApplicationContext().getResources();
                context = applicationContext;
                int dimension = (int) resources.getDimension(17104902);
                str2 = str6;
                int dimension2 = (int) resources.getDimension(17104901);
                z3 = z7;
                OvalShape ovalShape = new OvalShape();
                str = str5;
                i = d2;
                ovalShape.resize((float) dimension2, (float) dimension);
                Paint paint = new Paint();
                paint.setColor(resources.getColor(R.color.f12570_resource_name_obfuscated_RES_2131099947));
                Bitmap createBitmap = Bitmap.createBitmap(dimension2, dimension, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                ovalShape.draw(canvas, paint);
                float width = ((float) (dimension2 - decodeResource.getWidth())) / 2.0f;
                z = z6;
                float height = ((float) (dimension - decodeResource.getHeight())) / 2.0f;
                if (width < 0.0f || height < 0.0f) {
                    z2 = z5;
                    canvas.drawBitmap(decodeResource, new Rect(0, 0, decodeResource.getWidth(), decodeResource.getHeight()), new Rect(0, 0, dimension2, dimension), (Paint) null);
                } else {
                    canvas.drawBitmap(decodeResource, width, height, (Paint) null);
                    z2 = z5;
                }
                b2.c = createBitmap;
            }
            Bitmap bitmap2 = z8 ? b2.c : bitmap;
            MI mi = new MI();
            mi.f8469a = my;
            mi.b = str4;
            mi.c = str3;
            mi.o = j;
            mi.e = z4;
            mi.g = z2;
            mi.f = z;
            mi.d = bitmap2;
            mi.i = i;
            mi.j = str;
            mi.k = z3;
            mi.m = str2;
            mi.q = j2;
            Notification b3 = AbstractC4894tI.b(context, 2, mi.a(), i);
            b2.k(i, b3, my, null);
            b2.e.e(context, 2, i, b3);
            b2.f11472a.remove(my);
            if (downloadInfo.A) {
                DownloadManagerService p = DownloadManagerService.p();
                boolean z9 = c51.g;
                long j3 = c51.f;
                Objects.requireNonNull(p);
                if (!N.M09VlOh_("UseDownloadOfflineContentProvider")) {
                    if (!z9 || !MimeUtils.canAutoOpenMimeType(downloadInfo.c) || !downloadInfo.m) {
                        KH r = p.r(downloadInfo.t);
                        if (r != null) {
                            r.M.put(downloadInfo.z, Integer.valueOf(i));
                        }
                    } else {
                        DownloadItem downloadItem = new DownloadItem(false, downloadInfo);
                        downloadItem.d = j3;
                        downloadItem.f10660a.b = downloadItem.b();
                        p.u(downloadItem);
                    }
                } else if (p.r(downloadInfo.t) != null) {
                    p.r(downloadInfo.t).M.put(downloadInfo.z, Integer.valueOf(i));
                }
                if (((BrowserStartupControllerImpl) AbstractC4280pk.a()).f()) {
                    if (downloadInfo.t) {
                        profile = Profile.b().c();
                    } else {
                        profile = Profile.b();
                    }
                    Um1.a(profile).notifyEvent("download_completed");
                }
            }
        } else if (i2 == 3) {
            b().f(downloadInfo.z, downloadInfo.e, downloadInfo.D, downloadInfo.i, downloadInfo.G, downloadInfo.t, downloadInfo.F);
        } else if (i2 == 4) {
            b().g(downloadInfo.z, downloadInfo.e, downloadInfo.r, c51.j, downloadInfo.t, downloadInfo.B, downloadInfo.D, downloadInfo.i, downloadInfo.G, false, false, c51.k);
        }
    }
}
