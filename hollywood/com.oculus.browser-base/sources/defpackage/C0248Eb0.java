package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.settings.MainSettings;

/* renamed from: Eb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0248Eb0 implements YE0 {
    public final MainSettings F;

    public C0248Eb0(MainSettings mainSettings) {
        this.F = mainSettings;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        return this.F.n1();
    }
}
