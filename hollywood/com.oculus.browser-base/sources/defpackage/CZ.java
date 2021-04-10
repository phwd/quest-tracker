package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.prefs.PrefService;

/* renamed from: CZ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CZ {

    /* renamed from: a  reason: collision with root package name */
    public static CZ f7819a;
    public BZ b = new BZ(this);

    public static PrefService a(CZ cz, Profile profile) {
        Objects.requireNonNull(cz);
        return Wr1.a(profile);
    }

    public static CZ b() {
        if (f7819a == null) {
            f7819a = new CZ();
        }
        return f7819a;
    }

    public boolean c() {
        return N.Mudil8Bg("ExperimentalAccessibilityLabels") && C0283Ep.h().i();
    }
}
