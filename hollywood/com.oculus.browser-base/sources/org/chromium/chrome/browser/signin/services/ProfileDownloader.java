package org.chromium.chrome.browser.signin.services;

import android.graphics.Bitmap;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ProfileDownloader {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f10763a = new Object();
    public static ProfileDownloader b;
    public final C1322Vq0 c = new C1322Vq0();

    public static ProfileDownloader a() {
        synchronized (f10763a) {
            if (b == null) {
                b = new ProfileDownloader();
            }
        }
        return b;
    }

    public static void onProfileDownloadSuccess(String str, String str2, String str3, Bitmap bitmap) {
        Object obj = ThreadUtils.f10596a;
        Iterator it = a().c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                WG0 wg0 = (WG0) ((XG0) uq0.next());
                Objects.requireNonNull(wg0);
                Object obj2 = ThreadUtils.f10596a;
                wg0.a0(new C3522lG(str, wg0.X(bitmap, str), str2, str3));
            } else {
                return;
            }
        }
    }
}
