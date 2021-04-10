package org.chromium.chrome.browser.safe_browsing;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.oculus.os.Version;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SafeBrowsingReferringAppBridge {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class ReferringAppInfo {

        /* renamed from: a  reason: collision with root package name */
        public final int f10756a;
        public final String b;

        public ReferringAppInfo(int i, String str) {
            this.f10756a = i;
            this.b = str;
        }

        public String getName() {
            return this.b;
        }

        public int getSource() {
            return this.f10756a;
        }
    }

    public static ReferringAppInfo a() {
        return new ReferringAppInfo(0, "");
    }

    public static ReferringAppInfo getReferringAppInfo(WindowAndroid windowAndroid) {
        String str;
        Activity activity = (Activity) windowAndroid.s0().get();
        if (activity == null) {
            return a();
        }
        Intent intent = activity.getIntent();
        if (intent == null) {
            return a();
        }
        int c = S20.c(intent);
        if (c != 0) {
            switch (c) {
                case 0:
                    str = "other";
                    break;
                case 1:
                    str = "gmail";
                    break;
                case 2:
                    str = "facebook";
                    break;
                case 3:
                    str = "plus";
                    break;
                case 4:
                    str = "twitter";
                    break;
                case 5:
                    str = "chrome";
                    break;
                case 6:
                    str = "google.hangouts";
                    break;
                case Version.VERSION_7:
                    str = "android.messages";
                    break;
                case Version.VERSION_8:
                    str = "google.news";
                    break;
                case Version.VERSION_9:
                    str = "line";
                    break;
                case Version.VERSION_10:
                    str = "whatsapp";
                    break;
                case Version.VERSION_11:
                    str = "google.search.app";
                    break;
                case Version.VERSION_12:
                    str = "webapk";
                    break;
                case Version.VERSION_13:
                    str = "yahoo.mail";
                    break;
                case Version.VERSION_14:
                    str = "viber";
                    break;
                case Version.VERSION_15:
                    str = "youtube";
                    break;
                default:
                    str = "";
                    break;
            }
            return new ReferringAppInfo(1, str);
        }
        String n = U20.n(intent, "com.android.browser.application_id");
        if (n != null) {
            return new ReferringAppInfo(2, n);
        }
        Uri referrer = activity.getReferrer();
        if (referrer != null) {
            return new ReferringAppInfo(3, referrer.toString());
        }
        return a();
    }
}
