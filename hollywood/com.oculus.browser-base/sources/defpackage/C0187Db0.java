package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.settings.MainSettings;

/* renamed from: Db0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0187Db0 implements YE0 {
    public final MainSettings F;
    public final boolean G;
    public final String H;

    public C0187Db0(MainSettings mainSettings, boolean z, String str) {
        this.F = mainSettings;
        this.G = z;
        this.H = str;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        return this.F.m1(this.G, this.H);
    }
}
