package org.chromium.chrome.browser.metrics;

import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PageLoadMetrics {

    /* renamed from: a  reason: collision with root package name */
    public static C1322Vq0 f10695a;

    public static void onFirstContentfulPaint(WebContents webContents, long j, long j2, long j3) {
        Object obj = ThreadUtils.f10596a;
        C1322Vq0 vq0 = f10695a;
        if (vq0 != null) {
            Iterator it = vq0.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC0601Jv0) uq0.next()).a(webContents, j, j2, j3);
                } else {
                    return;
                }
            }
        }
    }

    public static void onFirstInputDelay(WebContents webContents, long j, long j2) {
        Object obj = ThreadUtils.f10596a;
        C1322Vq0 vq0 = f10695a;
        if (vq0 != null) {
            Iterator it = vq0.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    Objects.requireNonNull((AbstractC0601Jv0) uq0.next());
                } else {
                    return;
                }
            }
        }
    }

    public static void onFirstMeaningfulPaint(WebContents webContents, long j, long j2, long j3) {
        Object obj = ThreadUtils.f10596a;
        C1322Vq0 vq0 = f10695a;
        if (vq0 != null) {
            Iterator it = vq0.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC0601Jv0) uq0.next()).b(webContents, j, j2, j3);
                } else {
                    return;
                }
            }
        }
    }

    public static void onLargestContentfulPaint(WebContents webContents, long j, long j2, long j3, long j4) {
        Object obj = ThreadUtils.f10596a;
        C1322Vq0 vq0 = f10695a;
        if (vq0 != null) {
            Iterator it = vq0.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    Objects.requireNonNull((AbstractC0601Jv0) uq0.next());
                } else {
                    return;
                }
            }
        }
    }

    public static void onLayoutShiftScore(WebContents webContents, long j, float f, float f2) {
        Object obj = ThreadUtils.f10596a;
        C1322Vq0 vq0 = f10695a;
        if (vq0 != null) {
            Iterator it = vq0.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    Objects.requireNonNull((AbstractC0601Jv0) uq0.next());
                } else {
                    return;
                }
            }
        }
    }

    public static void onLoadEventStart(WebContents webContents, long j, long j2, long j3) {
        Object obj = ThreadUtils.f10596a;
        C1322Vq0 vq0 = f10695a;
        if (vq0 != null) {
            Iterator it = vq0.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    Objects.requireNonNull((AbstractC0601Jv0) uq0.next());
                } else {
                    return;
                }
            }
        }
    }

    public static void onLoadedMainResource(WebContents webContents, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8) {
        Object obj = ThreadUtils.f10596a;
        C1322Vq0 vq0 = f10695a;
        if (vq0 != null) {
            Iterator it = vq0.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    Objects.requireNonNull((AbstractC0601Jv0) uq0.next());
                } else {
                    return;
                }
            }
        }
    }

    public static void onNetworkQualityEstimate(WebContents webContents, long j, int i, long j2, long j3) {
        Object obj = ThreadUtils.f10596a;
        C1322Vq0 vq0 = f10695a;
        if (vq0 != null) {
            Iterator it = vq0.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    Objects.requireNonNull((AbstractC0601Jv0) uq0.next());
                } else {
                    return;
                }
            }
        }
    }

    public static void onNewNavigation(WebContents webContents, long j, boolean z) {
        Object obj = ThreadUtils.f10596a;
        C1322Vq0 vq0 = f10695a;
        if (vq0 != null) {
            Iterator it = vq0.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC0601Jv0) uq0.next()).c(webContents, j, z);
                } else {
                    return;
                }
            }
        }
    }
}
