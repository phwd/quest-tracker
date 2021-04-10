package org.chromium.chrome.browser.webapps;

import android.graphics.Bitmap;
import android.util.Pair;
import java.util.HashMap;
import java.util.Map;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PwaBottomSheetControllerProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final Ip1 f10801a = new Ip1(PwaBottomSheetController.class);

    public static PwaBottomSheetController a(WebContents webContents) {
        WindowAndroid I = webContents.I();
        if (I == null) {
            return null;
        }
        return (PwaBottomSheetController) f10801a.e(I.U);
    }

    public static boolean canShowPwaBottomSheetInstaller(WebContents webContents) {
        return a(webContents) != null;
    }

    public static void showPwaBottomSheetInstaller(long j, WebContents webContents, boolean z, Bitmap bitmap, boolean z2, String str, String str2, String str3, String str4) {
        PwaBottomSheetController a2 = a(webContents);
        if (a2 != null) {
            WindowAndroid I = webContents.I();
            a2.G = j;
            a2.L = webContents;
            AbstractC4448qj qjVar = (AbstractC4448qj) AbstractC5978zj.f11762a.e(I.U);
            a2.H = qjVar;
            if (qjVar != null) {
                OI0 oi0 = new OI0(a2, a2.F);
                a2.K = oi0;
                RI0 ri0 = new RI0(a2.F, oi0);
                a2.I = new PI0(ri0, a2);
                Map c = UH0.c(AbstractC5869z3.k);
                TH0 th0 = AbstractC5869z3.e;
                Pair pair = new Pair(bitmap, Boolean.valueOf(z2));
                LH0 lh0 = new LH0(null);
                lh0.f8415a = pair;
                HashMap hashMap = (HashMap) c;
                hashMap.put(th0, lh0);
                TH0 th02 = AbstractC5869z3.f11718a;
                LH0 lh02 = new LH0(null);
                lh02.f8415a = str;
                hashMap.put(th02, lh02);
                TH0 th03 = AbstractC5869z3.b;
                LH0 lh03 = new LH0(null);
                lh03.f8415a = str2;
                hashMap.put(th03, lh03);
                TH0 th04 = AbstractC5869z3.d;
                LH0 lh04 = new LH0(null);
                lh04.f8415a = str3;
                hashMap.put(th04, lh04);
                TH0 th05 = AbstractC5869z3.c;
                LH0 lh05 = new LH0(null);
                lh05.f8415a = str4;
                hashMap.put(th05, lh05);
                QH0 qh0 = AbstractC5869z3.g;
                GH0 gh0 = new GH0(null);
                gh0.f8081a = true;
                hashMap.put(qh0, gh0);
                TH0 th06 = AbstractC5869z3.h;
                LH0 lh06 = new LH0(null);
                lh06.f8415a = a2;
                hashMap.put(th06, lh06);
                UH0 uh0 = new UH0(c, null);
                a2.f10800J = uh0;
                ZH0.a(uh0, ri0, new LI0());
                if (((C5638xj) a2.H).u(a2.I, true)) {
                    if (z) {
                        ((C5638xj) a2.H).m();
                    }
                    a2.M = new MI0(a2, webContents);
                }
            }
        }
    }
}
