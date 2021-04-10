package org.chromium.chrome.browser.feed.v2;

import android.util.DisplayMetrics;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.BundleUtils;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.AppHooks;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class FeedServiceBridge {

    /* renamed from: a  reason: collision with root package name */
    public static PG0 f10670a;

    public static void clearAll() {
        if (FeedStreamSurface.c != null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = FeedStreamSurface.c.iterator();
            while (it.hasNext()) {
                FeedStreamSurface feedStreamSurface = (FeedStreamSurface) it.next();
                if (feedStreamSurface.m) {
                    arrayList.add(feedStreamSurface);
                }
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ((FeedStreamSurface) it2.next()).b();
            }
            PG0 e = FeedStreamSurface.e();
            if (e != null) {
                e.c();
            }
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                ((FeedStreamSurface) it3.next()).d();
            }
        }
    }

    public static double[] getDisplayMetrics() {
        DisplayMetrics displayMetrics = ContextUtils.getApplicationContext().getResources().getDisplayMetrics();
        return new double[]{(double) displayMetrics.density, (double) displayMetrics.widthPixels, (double) displayMetrics.heightPixels};
    }

    public static String getLanguageTag() {
        return ContextUtils.getApplicationContext().getResources().getConfiguration().getLocales().get(0).toLanguageTag();
    }

    public static void prefetchImage(String str) {
        RZ a2;
        if (f10670a == null) {
            AppHooks appHooks = AppHooks.get();
            BundleUtils.c(AO.a(ContextUtils.getApplicationContext()), "feedv2");
            f10670a = appHooks.e();
        }
        PG0 pg0 = f10670a;
        if (pg0 != null && (a2 = pg0.a()) != null) {
            a2.a(str);
        }
    }
}
