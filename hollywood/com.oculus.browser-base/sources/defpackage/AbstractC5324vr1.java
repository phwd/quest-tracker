package defpackage;

import org.chromium.url.GURL;

/* renamed from: vr1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5324vr1 {
    public static boolean a(GURL gurl) {
        return gurl.b && !gurl.g().isEmpty() && ("https".equals(gurl.g()) || "http".equals(gurl.g()));
    }
}
