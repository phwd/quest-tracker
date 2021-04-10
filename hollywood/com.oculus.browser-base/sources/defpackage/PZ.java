package defpackage;

import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: PZ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PZ {

    /* renamed from: a  reason: collision with root package name */
    public Profile f8698a;

    public PZ(Profile profile) {
        this.f8698a = profile;
    }

    public static PZ a(Profile profile) {
        Object obj = ThreadUtils.f10596a;
        return new PZ(profile);
    }
}
