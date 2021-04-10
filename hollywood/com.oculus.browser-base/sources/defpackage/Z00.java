package defpackage;

import J.N;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Z00  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Z00 {
    public static boolean a() {
        for (J00 j00 : K00.a().b) {
            if (j00.b()) {
                return true;
            }
        }
        return false;
    }

    public static Profile b(WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            return null;
        }
        C5859z.a(PB.F.e(windowAndroid.U));
        return null;
    }

    public static Profile c(WindowAndroid windowAndroid, boolean z) {
        if (!z) {
            return Profile.b();
        }
        b(windowAndroid);
        return Profile.b().c();
    }

    public static ProfileKey d(OTRProfileID oTRProfileID) {
        if (oTRProfileID == null) {
            return ProfileKey.a();
        }
        Profile b = Profile.b();
        return ((Profile) N.MIzCSj22(b.b, b, oTRProfileID)).d();
    }
}
