package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.tracing.settings.TracingSettings;

/* renamed from: Rm1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Rm1 implements XE0 {
    public final TracingSettings F;

    public Rm1(TracingSettings tracingSettings) {
        this.F = tracingSettings;
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        return this.F.o1(obj);
    }
}
