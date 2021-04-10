package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.tracing.settings.TracingSettings;

/* renamed from: Sm1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Sm1 implements YE0 {
    public final TracingSettings F;

    public Sm1(TracingSettings tracingSettings) {
        this.F = tracingSettings;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        return this.F.p1();
    }
}
