package defpackage;

import androidx.preference.Preference;

/* renamed from: n51  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3833n51 implements YE0 {
    public final AbstractC2324eF0 F;
    public final Runnable G;

    public C3833n51(AbstractC2324eF0 ef0, Runnable runnable) {
        this.F = ef0;
        this.G = runnable;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        AbstractC2324eF0 ef0 = this.F;
        Runnable runnable = this.G;
        if (!ef0.Z()) {
            return false;
        }
        runnable.run();
        return false;
    }
}
