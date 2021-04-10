package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;
import org.chromium.url.GURL;

/* renamed from: KZ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KZ {

    /* renamed from: a  reason: collision with root package name */
    public final Profile f8372a;
    public X60 b;

    public KZ(Profile profile) {
        this.f8372a = profile;
    }

    public void a(GURL gurl, int i, LargeIconBridge$LargeIconCallback largeIconBridge$LargeIconCallback) {
        if (this.b == null) {
            F31 a2 = F31.a();
            Profile profile = this.f8372a;
            Objects.requireNonNull(a2);
            this.b = new X60(profile);
        }
        this.b.b(gurl, i, largeIconBridge$LargeIconCallback);
    }
}
