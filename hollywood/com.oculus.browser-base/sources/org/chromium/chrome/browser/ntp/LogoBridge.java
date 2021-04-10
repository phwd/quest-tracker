package org.chromium.chrome.browser.ntp;

import android.graphics.Bitmap;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LogoBridge {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class Logo {

        /* renamed from: a  reason: collision with root package name */
        public final Bitmap f10710a;
        public final String b;

        public Logo(Bitmap bitmap, String str, String str2, String str3) {
            this.f10710a = bitmap;
            this.b = str2;
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface LogoObserver {
        void onCachedLogoRevalidated();

        void onLogoAvailable(Logo logo, boolean z);
    }

    public static Logo createLogo(Bitmap bitmap, String str, String str2, String str3) {
        return new Logo(bitmap, str, str2, str3);
    }
}
