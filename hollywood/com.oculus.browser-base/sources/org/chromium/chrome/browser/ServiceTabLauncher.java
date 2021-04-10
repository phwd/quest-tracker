package org.chromium.chrome.browser;

import J.N;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.os.SystemClock;
import java.util.List;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.components.payments.PaymentApp;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ServiceTabLauncher {
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(int r24, boolean r25, java.lang.String r26, java.lang.String r27, int r28, java.lang.String r29, org.chromium.content_public.common.ResourceRequestBody r30, java.util.List r31) {
        /*
        // Method dump skipped, instructions count: 436
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ServiceTabLauncher.a(int, boolean, java.lang.String, java.lang.String, int, java.lang.String, org.chromium.content_public.common.ResourceRequestBody, java.util.List):void");
    }

    public static void launchTab(int i, boolean z, GURL gurl, int i2, String str, int i3, String str2, ResourceRequestBody resourceRequestBody) {
        long j;
        C0289Es es;
        C5177uz0 uz0;
        Px1.a().f = true;
        WebContents webContents = null;
        if (i2 == 5) {
            EA0 ea0 = EA0.f7940a;
            if (ea0 != null) {
                PaymentApp paymentApp = ea0.f7941J;
                AbstractC3254jk jkVar = ea0.B;
                boolean z2 = ea0.m;
                long r = paymentApp.r();
                C0289Es es2 = (C0289Es) jkVar;
                AB0 ab0 = es2.f;
                if (ab0.j != null) {
                    es = es2;
                    j = r;
                } else {
                    C5177uz0 uz02 = new C5177uz0();
                    WebContents webContents2 = ab0.n;
                    ChromeActivity J0 = ChromeActivity.J0(webContents2);
                    if (J0 == null) {
                        es = es2;
                        j = r;
                        uz0 = uz02;
                    } else {
                        WebContents a2 = AbstractC5342vx1.a(Z00.c(J0.b0, z2), false);
                        uz02.b = a2;
                        if (a2 != null && !a2.g()) {
                            N.Ma2gt_BX(a2);
                        }
                        WebContents webContents3 = uz02.b;
                        int i4 = AbstractViewGroup$OnHierarchyChangeListenerC1520Yy.F;
                        C1459Xy xy = new C1459Xy(J0, null, webContents3);
                        uz02.b.J("89.0.4389.105", new ViewAndroidDelegate(xy), xy, J0.b0, new C3466kx1());
                        SelectionPopupControllerImpl r2 = SelectionPopupControllerImpl.r(uz02.b);
                        r2.L = new ActionMode$CallbackC4155oz0(uz02.b);
                        r2.F(AbstractC2185dS0.b(uz02.b));
                        uz02.b.f().c(new LoadUrlParams(gurl.h(), 0));
                        uz02.c = new C0426Gz0(J0, uz02.b, gurl);
                        UH0 uh0 = new UH0(UH0.c(AbstractC0182Cz0.c), null);
                        j = r;
                        es = es2;
                        uz0 = uz02;
                        View$OnLayoutChangeListenerC6027zz0 zz0 = new View$OnLayoutChangeListenerC6027zz0(uh0, new RunnableC4326pz0(uz02), webContents2, uz02.b, ab0, J0.K0().b(), uz02.c.f8126a.f8452a, J0.Y, AbstractC5978zj.a(J0.b0));
                        J0.getWindow().getDecorView().addOnLayoutChangeListener(zz0);
                        C5638xj xjVar = (C5638xj) AbstractC5978zj.a(J0.b0);
                        xjVar.j(zz0);
                        uz0.b.c0(zz0);
                        uz0.c.f.m(AbstractC0670Kz0.h, new RunnableC4497qz0(zz0));
                        C1544Zg1 zg1 = new C1544Zg1(J0, new C1483Yg1());
                        zg1.a(uz0.b, xy, null);
                        C0913Oz0 oz0 = new C0913Oz0(J0, uz0.b, uz0.c.f8126a.b, zg1);
                        uz0.f11451a = new RunnableC4837sz0(uz0, ZH0.a(uh0, oz0, new C4667rz0()), xjVar, zz0, oz0, ab0, J0, zg1);
                        webContents = xjVar.u(oz0, true) ? uz0.b : null;
                    }
                    if (webContents != null) {
                        ab0.j = uz0;
                    }
                }
                if (webContents != null) {
                    WebContents webContents4 = es.d;
                    if (webContents4 != null && !webContents4.g()) {
                        N.MRjWfZEk(webContents4, webContents);
                    }
                    H40 h40 = es.e;
                    N.MMsq7cME(h40.f8134a, h40, j);
                }
            }
            if (webContents != null) {
                N.MMtVSAe3(i, webContents);
                return;
            } else {
                PostTask.b(Zo1.f9374a, new JS0(i), 0);
                return;
            }
        } else {
            String str3 = null;
            String h = gurl.h();
            Context applicationContext = ContextUtils.getApplicationContext();
            long uptimeMillis = SystemClock.uptimeMillis();
            try {
                List d = AbstractC2612fx1.d(applicationContext, h);
                AbstractC3364kK0.j("BrowserServices.ServiceTabResolveInfoQuery", SystemClock.uptimeMillis() - uptimeMillis);
                ResolveInfo a3 = AbstractC2612fx1.a(applicationContext, d);
                if (a3 != null) {
                    str3 = a3.activityInfo.packageName;
                }
                if (str3 != null) {
                    AbstractC3626lu.a(str3, new KS0(str3, h, i, z, str, i3, str2, resourceRequestBody, d));
                    return;
                } else {
                    a(i, z, h, str, i3, str2, resourceRequestBody, d);
                    return;
                }
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        throw th;
    }
}
